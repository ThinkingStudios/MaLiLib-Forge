package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.NeoNetwork;
import org.thinkingstudio.mafglib.util.NeoUtils;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public MaFgLib(IEventBus modEventBus) {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            MaLiLib.onInitialize();
            NeoNetwork.init(modEventBus);

            // Config Screen
            NeoUtils.getInstance().registerModConfigScreen(MaLiLibReference.MOD_ID, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
