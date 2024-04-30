package fi.dy.masa.malilib;

import fi.dy.masa.malilib.compat.forge.ForgePlatformUtils;
import net.minecraftforge.fml.common.Mod;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

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
