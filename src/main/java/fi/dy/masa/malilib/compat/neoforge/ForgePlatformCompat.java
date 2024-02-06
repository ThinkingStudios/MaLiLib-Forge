package fi.dy.masa.malilib.compat.neoforge;

import fi.dy.masa.malilib.compat.neoforge.register.ModConfigScreenRegister;

@Deprecated
public class ForgePlatformCompat {
    public static ForgePlatformCompat getInstance() {
        return new ForgePlatformCompat();
    }

    public ModConfigScreenRegister getMod(String id) {
        return ForgePlatformUtils.getInstance().getMod(id);
    }

    public void getModClientExtensionPoint() {
        ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
    }
}
