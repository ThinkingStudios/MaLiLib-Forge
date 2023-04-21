package fi.dy.masa.malilib;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLibForge {
    public MaLiLibForge() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> {
                    MaLiLibConfigGui gui = new MaLiLibConfigGui();
                    gui.setParent(screen);
                    return gui;
                })
        );
    }
}
