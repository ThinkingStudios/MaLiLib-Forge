package org.thinkingstudio.mafglib.special;

import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = MaLiLibReference.MOD_ID + "_special", dist = Dist.CLIENT)
public class MaFgLibSpecial {
    public MaFgLibSpecial(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerConfig(ModConfig.Type.CLIENT, SpecialConfig.configPair.getValue());
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
    }

    public static SpecialConfig getConfig() {
        return SpecialConfig.configPair.getLeft();
    }
}
