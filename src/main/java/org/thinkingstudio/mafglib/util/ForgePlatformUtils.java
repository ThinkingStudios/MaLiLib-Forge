package org.thinkingstudio.mafglib.util;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;

@Deprecated
public class ForgePlatformUtils {
    private static final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void getClientModIgnoredServerOnly() {
        ForgeUtils.getInstance().getClientModIgnoredServerOnly(context.getActiveContainer());
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow();
        ForgeUtils.getInstance().registerModConfigScreen(modContainer, configScreenProvider::provide);
    }
}