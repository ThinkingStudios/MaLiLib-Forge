package org.thinkingstudio.mafglib.util;

import org.thinkingstudio.mafglib.util.register.ModConfigScreenRegister;
import org.thinkingstudio.mafglib.util.register.impl.ModConfigScreenRegisterImpl;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgePlatformUtils {
    private static final Map<String, ModConfigScreenRegister> mods = new ConcurrentHashMap<>();
    private final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    @Deprecated
    public ModConfigScreenRegister getMod(String id) {
        return mods.computeIfAbsent(id, ModConfigScreenRegisterImpl::new);
    }

    public void getClientModIgnoredServerOnly() {
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> configScreenProvider.provide(screen)));
    }
}
