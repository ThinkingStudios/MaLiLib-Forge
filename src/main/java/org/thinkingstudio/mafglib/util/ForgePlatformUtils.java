package org.thinkingstudio.mafglib.util;

@Deprecated(forRemoval = true)
public class ForgePlatformUtils {
    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        NeoUtils.getInstance().registerModConfigScreen(modid, configScreenProvider);
    }
}
