package fi.dy.masa.malilib;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.compat.neoforge.ForgePlatformUtils;
import fi.dy.masa.malilib.compat.neoforge.event.ForgeInputEventHandler;
import fi.dy.masa.malilib.compat.neoforge.event.ForgeTickEventHandler;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            this.onInitializeClient();

        }
    }

    public void onInitializeClient() {
        NeoForge.EVENT_BUS.addListener(this::onInterModProcess);
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

        // Mixin doesn't work, maybe?
        NeoForge.EVENT_BUS.register(new ForgeTickEventHandler());
        NeoForge.EVENT_BUS.register(new ForgeInputEventHandler());
    }

    public void onInterModProcess(InterModProcessEvent event) {
        ((InitializationHandler) InitializationHandler.getInstance()).onGameInitDone();
    }
}
