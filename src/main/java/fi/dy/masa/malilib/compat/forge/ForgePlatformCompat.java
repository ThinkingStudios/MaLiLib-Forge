package fi.dy.masa.malilib.compat.forge;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;
import fi.dy.masa.malilib.compat.forge.register.impl.ModConfigScreenRegisterImpl;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgePlatformCompat {
    private static final ThreadLocal<ForgePlatformCompat> forgePlatform = ThreadLocal.withInitial(ForgePlatformCompat::new);
    private static final Map<String, ModConfigScreenRegister> mods = new ConcurrentHashMap<>();

    public static ForgePlatformCompat getInstance() {
        return forgePlatform.get();
    }

    public ModConfigScreenRegister getMod(String id) {
        return mods.computeIfAbsent(id, ModConfigScreenRegisterImpl::new);
    }

    public void getModClientExtensionPoint() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }
}
