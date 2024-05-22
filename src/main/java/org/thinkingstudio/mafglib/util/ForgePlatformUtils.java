package org.thinkingstudio.mafglib.util;

import net.minecraftforge.client.ConfigScreenHandler;
import org.thinkingstudio.mafglib.util.register.ModConfigScreenRegister;
import org.thinkingstudio.mafglib.util.register.impl.ModConfigScreenRegisterImpl;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgePlatformUtils {
    private static final Map<String, ModConfigScreenRegister> mods = new ConcurrentHashMap<>();
    private static final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    @Deprecated
    public ModConfigScreenRegister getMod(String id) {
        return mods.computeIfAbsent(id, ModConfigScreenRegisterImpl::new);
    }

    public void getClientModIgnoredServerOnly() {
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> configScreenProvider.provide(screen)));
    }
}
