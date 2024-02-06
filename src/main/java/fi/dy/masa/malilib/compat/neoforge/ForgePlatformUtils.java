package fi.dy.masa.malilib.compat.neoforge;

import fi.dy.masa.malilib.compat.neoforge.register.ModConfigScreenRegister;
import fi.dy.masa.malilib.compat.neoforge.register.impl.ModConfigScreenRegisterImpl;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgePlatformUtils {
    private static final Map<String, ModConfigScreenRegister> mods = new ConcurrentHashMap<>();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public ModConfigScreenRegister getMod(String id) {
        return mods.computeIfAbsent(id, ModConfigScreenRegisterImpl::new);
    }

    public void getClientModIgnoredServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
    }
}
