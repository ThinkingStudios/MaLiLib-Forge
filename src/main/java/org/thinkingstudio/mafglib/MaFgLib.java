package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.ForgeUtils;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public MaFgLib() {
        if (FMLLoader.getDist().isClient()) {
            ModContainer modContainer = ModLoadingContext.get().getActiveContainer();

            // Make sure the mod being absent on the other network side does not cause
            // the client to display the server as incompatible
            ForgeUtils.getInstance().getClientModIgnoredServerOnly(modContainer);
            MaLiLib.onInitialize();

            // Config Screen
            ForgeUtils.getInstance().registerModConfigScreen(modContainer, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}