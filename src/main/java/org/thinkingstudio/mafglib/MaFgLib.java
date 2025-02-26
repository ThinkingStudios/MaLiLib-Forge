package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.compat.modmenu.ModMenuImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = MaLiLibReference.MOD_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, new ModMenuImpl().getModConfigScreenFactory());

            MaLiLib.onInitialize();
        }
    }
}
