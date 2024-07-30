package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;

@Deprecated(forRemoval = true)
public class ForgePlatformUtils {
    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow();
        NeoUtils.getInstance().registerModConfigScreen(modContainer, configScreenProvider::provide);
    }
}
