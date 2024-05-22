package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler;

public class ForgePlatformUtils {
    private final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void getClientModIgnoredServerOnly() {
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> configScreenProvider.provide(screen)));
    }
}
