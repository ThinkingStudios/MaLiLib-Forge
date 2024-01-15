package fi.dy.masa.malilib.compat.forge;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;

@Deprecated
public class ForgePlatformCompat {
    private static final ThreadLocal<ForgePlatformCompat> forgePlatform = ThreadLocal.withInitial(ForgePlatformCompat::new);

    public static ForgePlatformCompat getInstance() {
        return forgePlatform.get();
    }

    public ModConfigScreenRegister getMod(String id) {
        return ForgePlatformUtils.getInstance().getMod(id);
    }

    public void getModClientExtensionPoint() {
        ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
    }
}
