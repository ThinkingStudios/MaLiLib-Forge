package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.function.Function;

public class NeoUtils {
    private static NeoUtils INSTANCE;

    @Deprecated
    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow();
        registerModConfigScreen(modContainer, configScreenProvider::provide);
    }

    @Deprecated
    public void registerModConfigScreen(String modid, Function<Screen, Screen> screenFunction) {
        ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow();
        registerModConfigScreen(modContainer, screenFunction);
    }

    public void registerModConfigScreen(ModContainer modContainer, Function<Screen, Screen> screenFunction) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (client, screen) -> screenFunction.apply(screen));
    }

    public ArtifactVersion getModVersion(String modId) {
        for(ModInfo modInfo: FMLLoader.getLoadingModList().getMods()) {
            if(modInfo.getModId().equals(modId)) {
                return modInfo.getVersion();
            }
        }

        return new DefaultArtifactVersion("?");
    }

    public static NeoUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NeoUtils();
        }
        return INSTANCE;
    }
}
