package org.thinkingstudio.mafglib.loader;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.nio.file.Path;
import java.util.function.Consumer;

public final class FoxifiedLoader {
    @Deprecated
    public static <T extends Event> void registerEvent(IEventBus eventBus, Class<T> eventType, Consumer<T> consumer) {
        eventBus.addListener(EventPriority.HIGHEST, eventType, consumer);
    }

    @Deprecated
    public static <T extends IExtensionPoint> void registerExtensionPoint(ModContainer modContainer, Class<T> point, T extension) {
        modContainer.registerExtensionPoint(point, extension);
    }

    public static ModContainer getModContainers() {
        return ModLoadingContext.get().getActiveContainer();
    }

    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static String getModVersion(String modId) {
        for (ModInfo modInfo: FMLLoader.getLoadingModList().getMods()) {
            if (modInfo.getModId().equals(modId)) {
                return modInfo.getVersion().toString();
            }
        }

        return "?";
    }
}
