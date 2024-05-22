package org.thinkingstudio.mafglib.util;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

public class ForgePlatformUtils {
    private static final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void getClientModIgnoredServerOnly() {
        context.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, screen) -> configScreenProvider.provide(screen));
    }
}
