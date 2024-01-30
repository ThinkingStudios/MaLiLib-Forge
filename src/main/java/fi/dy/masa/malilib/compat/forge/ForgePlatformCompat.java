package fi.dy.masa.malilib.compat.forge;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;

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
