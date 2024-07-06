package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.NeoUtils;
import org.thinkingstudio.mafglib.util.NeoNetwork;

@Mod(value = MaLiLibReference.MOD_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(IEventBus modEventBus) {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            MaLiLib.onInitialize();
            NeoNetwork.setRegistrar(modEventBus, MaLiLibReference.MOD_ID);

            // Config Screen
            NeoUtils.getInstance().registerModConfigScreen(MaLiLibReference.MOD_ID, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
