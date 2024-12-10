package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforgespi.language.IModInfo;
import net.neoforged.neoforgespi.locating.IModFile;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.function.Function;

public class NeoUtils {
    private static NeoUtils INSTANCE;

    @Deprecated
    public void registerModConfigScreen(ModContainer modContainer, ConfigScreenProvider configScreenProvider) {
        registerConfigScreen(modContainer, configScreenProvider::provide);
    }

    @Deprecated
    public void registerConfigScreen(ModContainer modContainer, Function<Screen, Screen> screenFunction) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, screen) -> screenFunction.apply(screen));
    }

    public ArtifactVersion getModArtifactVersion(String modId) {
        for (ModInfo modInfo: FMLLoader.getLoadingModList().getMods()) {
            if(modInfo.getModId().equals(modId)) {
                return modInfo.getVersion();
            }
        }

        return new DefaultArtifactVersion("?");
    }

    public static IModInfo getModInfo(IModFile modFile) {
        for (IModInfo modInfo: modFile.getModInfos()) {
            if (modInfo.getOwningFile().getFile().equals(modFile)) {
                return modInfo;
            }
        }

        return modFile.getModInfos().getFirst();
    }

    public static NeoUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NeoUtils();
        }
        return INSTANCE;
    }

    @OnlyIn(Dist.CLIENT)
    @FunctionalInterface
    public interface ConfigScreenProvider {
        Screen provide(Screen parent);
    }
}
