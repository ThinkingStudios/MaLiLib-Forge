package org.thinkingstudio.mafglib.util;

import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

public class ForgePlatformUtils {
    private static final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void getClientModIgnoredServerOnly() {
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((client, screen) -> configScreenProvider.provide(screen)));
    }
}
