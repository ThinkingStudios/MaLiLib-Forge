package fi.dy.masa.malilib;

import fi.dy.masa.malilib.compat.forge.ForgePlatformUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fi.dy.masa.malilib.event.InitializationHandler;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        if (FMLLoader.getDist().isClient()) {
            // Make sure the mod being absent on the other network side does not cause
            // the client to display the server as incompatible
            ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
            InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());

            // Config Screen
            ForgePlatformUtils.getInstance().getMod(MaLiLibReference.MOD_ID).registerModConfigScreen((screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
