package fi.dy.masa.malilib.compat.forge;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;
import fi.dy.masa.malilib.compat.forge.register.impl.ModConfigScreenRegisterImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgePlatformCompat {
    private static ThreadLocal<ForgePlatformCompat> forgePlatform = ThreadLocal.withInitial(ForgePlatformCompat::new);
    private static final Map<String, ModConfigScreenRegister> mods = new ConcurrentHashMap<>();

    public static ForgePlatformCompat getInstance() {
        return forgePlatform.get();
    }

    public ModConfigScreenRegister getMod(String id) {
        return mods.computeIfAbsent(id, ModConfigScreenRegisterImpl::new);
    }
}
