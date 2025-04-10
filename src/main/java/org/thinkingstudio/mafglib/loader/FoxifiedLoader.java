package org.thinkingstudio.mafglib.loader;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.List;

public final class FoxifiedLoader {
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

    public static List<IModInfo> getAllMods() {
        return ModList.get().getMods();
    }
}
