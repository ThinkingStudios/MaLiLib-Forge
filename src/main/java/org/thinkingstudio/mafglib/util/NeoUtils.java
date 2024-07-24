package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class NeoUtils {
    private static NeoUtils INSTANCE;

    public void registerModConfigScreen(ModContainer modContainer, ConfigScreenProvider configScreenProvider) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, screen) -> configScreenProvider.provide(screen));
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
