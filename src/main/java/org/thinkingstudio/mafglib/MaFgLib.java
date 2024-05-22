package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import org.thinkingstudio.mafglib.util.ForgePlatformUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public MaFgLib() {
        if (FMLLoader.getDist().isClient()) {
            // Make sure the mod being absent on the other network side does not cause
            // the client to display the server as incompatible
            ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
            MaLiLib.onInitialize();

            // Config Screen
            ForgePlatformUtils.getInstance().registerModConfigScreen((screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
