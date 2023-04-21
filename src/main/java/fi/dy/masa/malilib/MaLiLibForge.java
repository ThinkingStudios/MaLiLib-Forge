package fi.dy.masa.malilib;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmlclient.ConfigGuiHandler;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLibForge {
    public MaLiLibForge() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigGuiHandler.ConfigGuiFactory.class,
                () -> new ConfigGuiHandler.ConfigGuiFactory((minecraftClient, screen) -> {
                    MaLiLibConfigGui gui = new MaLiLibConfigGui();
                    gui.setParent(screen);
                    return gui;
                })
        );
    }
}