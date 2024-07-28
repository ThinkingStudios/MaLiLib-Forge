package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

public class ForgeUtils {
    private static ForgeUtils INSTANCE;

    public void getClientModIgnoredServerOnly(ModContainer modContainer) {
        modContainer.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModContainer modContainer, Function<Screen, Screen> screenFunction) {
        modContainer.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, screen) -> screenFunction.apply(screen));
    }

    public static ForgeUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForgeUtils();
        }
        return INSTANCE;
    }
}
