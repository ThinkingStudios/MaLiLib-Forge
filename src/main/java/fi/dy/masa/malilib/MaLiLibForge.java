package fi.dy.masa.malilib;

import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModLoadingContext;

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
