package fi.dy.masa.malilib;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.event.InitializationHandler;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());

        ModLoadingContext.get().registerExtensionPoint(
                ExtensionPoint.CONFIGGUIFACTORY,
                () -> (minecraftClient, screen) -> {
                    MaLiLibConfigGui gui = new MaLiLibConfigGui();
                    gui.setParent(screen);
                    return gui;
                }
        );
    }
}
