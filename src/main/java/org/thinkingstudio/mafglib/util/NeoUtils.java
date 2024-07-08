package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class NeoUtils {
    public static NeoUtils getInstance() {
        return new NeoUtils();
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModList.get().getModContainerById(modid).orElseThrow().registerExtensionPoint(IConfigScreenFactory.class, (client, screen) -> configScreenProvider.provide(screen));
    }
}
