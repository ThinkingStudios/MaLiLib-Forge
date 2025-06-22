package org.thinkingstudio.mafglib.helper;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.google.common.hash.HashCode;
import com.mojang.logging.LogUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.SharedConstants;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.resource.*;
import net.minecraft.resource.ResourcePackProfile.Metadata;
import net.minecraft.resource.ResourcePackProfile.PackFactory;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.resource.ResourcePackLoader;
import net.neoforged.neoforgespi.locating.IModFile;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

/**
 * <p>
 * RuntimePackHelper is a helper for creating runtime resource packs.
 * Architectury Loom does not support runtime resource packs, so this is a workaround.
 * </p>
 *
 * code from <a href="https://github.com/DragonsPlusMinecraft/CreateDragonsPlus/blob/main/src/main/java/plus/dragons/createdragonsplus/data/runtime/RuntimePackResources.java">CreateDragonsPlus-RuntimePackResources</a>
 * under <a href="https://github.com/DragonsPlusMinecraft/CreateDragonsPlus/blob/main/LICENSE.txt">LGPL-v3</a>
 */
public final class RuntimePackHelper implements ResourcePack, ResourcePackProvider, PackFactory, DataWriter {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Joiner PATH_JOINER = Joiner.on("/");
    private final IModFile file;
    private final ResourceType type;
    private final ResourcePackProfile.InsertionPosition position;
    private final PackResourceMetadata metadata;
    private final ResourcePackInfo info;
    private final DataOutput output;
    private final ExistingFileHelper existingFileHelper;
    private final Map<Path, InputSupplier<InputStream>> resources = new HashMap<>();

    private RuntimePackHelper(String name, ModContainer modContainer, ResourceType type, ResourcePackProfile.InsertionPosition position, Text title, Text description) {
        var modInfo = modContainer.getModInfo();
        var modId = modInfo.getModId();
        var packId = Identifier.of(modId, name);
        this.file = modInfo.getOwningFile().getFile();
        this.type = type;
        this.position = position;
        this.metadata = new PackResourceMetadata(description, SharedConstants.getGameVersion().getResourceVersion(type));
        this.info = new ResourcePackInfo(
                packId.toString(),
                title,
                ResourcePackSource.BUILTIN,
                Optional.empty());
        this.output = new DataOutput(file.findResource(""));
        this.existingFileHelper = new ExistingFileHelper(Set.of(), Set.of(), false, null, null);
        var logoFile = modInfo.getLogoFile();
        var modResources = ResourcePackLoader.getPackFor(modInfo.getModId());
        if (logoFile.isPresent() && modResources.isPresent()) {
            var packInfo = new ResourcePackInfo("mod/" + modId, Text.empty(), ResourcePackSource.BUILTIN, Optional.empty());
            try (ResourcePack packResources = modResources.get().open(packInfo)) {
                InputSupplier<InputStream> logoResource = packResources.openRoot(logoFile.get().split("[/\\\\]"));
                resources.put(file.findResource("pack.png"), logoResource);
            }
        }
    }

    public static RuntimePackHelper simpleRuntimePack(ModContainer modContainer, ResourceType type) {
        var modId = modContainer.getModId();
        var title = Text.translatable("pack." + modId + ".runtime");
        var description = Text.translatable("pack." + modId + ".runtime.description");
        return createRuntimePack("runtime", modContainer, type, ResourcePackProfile.InsertionPosition.TOP, title, description);
    }

    public static RuntimePackHelper createRuntimePack(String name, ModContainer modContainer, ResourceType type, ResourcePackProfile.InsertionPosition position, Text title, Text description) {
        return new RuntimePackHelper(name, modContainer, type, position, title, description);
    }

    public ExistingFileHelper getExistingFileHelper() {
        return existingFileHelper;
    }

    public DataOutput getPackOutput() {
        return output;
    }

    public void addDataProvider(DataProvider provider) {
        LOGGER.info("Starting provider [{}] for runtime resource [{}]", provider, info.id());
        Stopwatch stopwatch = Stopwatch.createStarted();
        provider.run(this).join();
        LOGGER.info("{} finished after {} ms", provider, stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public ResourcePackInfo getInfo() {
        return info;
    }

    @Override
    public ResourcePack open(ResourcePackInfo info) {
        return this;
    }

    @Override
    public ResourcePack openWithOverlays(ResourcePackInfo info, Metadata metadata) {
        return this;
    }

    @Override
    public void register(Consumer<ResourcePackProfile> loader) {
        loader.accept(ResourcePackProfile.create(
                info,
                this,
                type,
                new ResourcePackPosition(true, position, false)));
    }

    @Nullable
    @Override
    public InputSupplier<InputStream> openRoot(String... pathName) {
        Path path = file.findResource(pathName);
        return resources.get(path);
    }

    @Nullable
    @Override
    public InputSupplier<InputStream> open(ResourceType type, Identifier location) {
        Path path = file.findResource(type.getDirectory(), location.getNamespace(), location.getPath());
        return resources.get(path);
    }

    @Override
    public void findResources(ResourceType type, String namespace, String directory, ResourcePack.ResultConsumer output) {
        if (this.type != type)
            return;
        Path namespacePath = file.findResource(type.getDirectory(), namespace);
        Path directoryPath = namespacePath.resolve(directory);
        resources.forEach((path, resource) -> {
            if (path.startsWith(directoryPath)) {
                String file = PATH_JOINER.join(namespacePath.relativize(path));
                Identifier location = Identifier.tryParse(namespace, file);
                if (location == null)
                    LOGGER.warn("Invalid path in pack: {}:{}, ignoring", namespace, file);
                else
                    output.accept(location, resource);
            }
        });
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        Path directoryPath = file.findResource(type.getDirectory());
        return resources.keySet().stream()
                .map(path -> directoryPath.relativize(path).getName(0).toString())
                .distinct()
                .filter(namespace -> {
                    if (Identifier.isNamespaceValid(namespace))
                        return true;
                    LOGGER.warn("Non [a-z0-9_.-] character in namespace {} in pack {}, ignoring", namespace, this.info.id());
                    return false;
                })
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> deserializer) {
        if (deserializer == PackResourceMetadata.SERIALIZER)
            return (T) metadata;
        return null;
    }

    @Override
    public void close() {}

    @Override
    public void write(Path filePath, byte[] data, HashCode hashCode) {
        resources.put(filePath, () -> new ByteArrayInputStream(data));
    }
}
