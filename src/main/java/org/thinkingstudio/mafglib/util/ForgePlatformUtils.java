package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;

@Deprecated(forRemoval = true)
public class ForgePlatformUtils {
    private final ModLoadingContext context = ModLoadingContext.get();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void getClientModIgnoredServerOnly() {
        NeoUtils.getInstance().getClientModIgnoredServerOnly(context.getActiveContainer());
    }

    public void registerModConfigScreen(String modid, ModConfigScreenProvider configScreenProvider) {
        ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow();
        NeoUtils.getInstance().registerModConfigScreen(modContainer, configScreenProvider::provide);
    }
}
