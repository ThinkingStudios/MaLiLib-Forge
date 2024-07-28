package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.network.NetworkConstants;

import java.util.function.Function;

public class ForgeUtils {
    private static ForgeUtils INSTANCE;

    public void getClientModIgnoredServerOnly(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModContainer modContainer, Function<Screen, Screen> screenFunction) {
        modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> screenFunction.apply(screen)));
    }

    public static ForgeUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForgeUtils();
        }
        return INSTANCE;
    }
}
