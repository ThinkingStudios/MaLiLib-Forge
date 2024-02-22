package fi.dy.masa.malilib;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.compat.neoforge.ForgePlatformUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            this.onInitializeClient();
        }
    }

    public void onInitializeClient() {
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
