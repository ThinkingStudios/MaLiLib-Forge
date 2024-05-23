package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.ForgePlatformUtils;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public MaFgLib() {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            MaLiLib.onInitialize();

            // Config Screen
            ForgePlatformUtils.getInstance().registerModConfigScreen(MaLiLibReference.MOD_ID, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
