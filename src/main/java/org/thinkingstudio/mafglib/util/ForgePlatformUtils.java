package org.thinkingstudio.mafglib.util;

import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforgespi.language.IModInfo;

import java.util.List;
import java.util.Objects;

public class ForgePlatformUtils {
    private final ModLoadingContext context = ModLoadingContext.get();
    private final List<IModInfo> modInfo = ModList.get().getMods();

    public static ForgePlatformUtils getInstance() {
        return new ForgePlatformUtils();
    }

    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        context.registerExtensionPoint(IConfigScreenFactory.class, () -> (client, screen) -> configScreenProvider.provide(screen));
    }

    public String getModVersion(String modId) {
        return modInfo.stream().filter(modInfo -> {
            return Objects.equals(modInfo.getModId(), modId);
        }).findAny().orElseThrow().getVersion().toString();
    }
}
