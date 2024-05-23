package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class ForgePlatformUtils {
    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModList.get().getModContainerById(modid).orElseThrow().registerExtensionPoint(IConfigScreenFactory.class, (client, screen) -> configScreenProvider.provide(screen));
    }
}
