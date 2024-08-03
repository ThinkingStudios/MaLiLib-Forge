package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Function;

public class NeoUtils {
    private static NeoUtils INSTANCE;

    @Deprecated
    public void registerModConfigScreen(ModContainer modContainer, ConfigScreenProvider configScreenProvider) {
        registerConfigScreen(modContainer, configScreenProvider::provide);
    }

    public void registerConfigScreen(ModContainer modContainer, Function<Screen, Screen> screenFunction) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, screen) -> screenFunction.apply(screen));
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
