package fi.dy.masa.malilib;

import fi.dy.masa.malilib.compat.forge.ForgePlatformCompat;
import fi.dy.masa.malilib.event.InitializationHandler;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onInitializeClient);
    }

    public void onInitializeClient(FMLClientSetupEvent event) {
        ForgePlatformCompat.getInstance().modClientSide();
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
        ForgePlatformCompat.getInstance().getMod(MaLiLibReference.MOD_ID).registerModConfigScreen((screen) -> {
            MaLiLibConfigGui gui = new MaLiLibConfigGui();
            gui.setParent(screen);
            return gui;
        });
    }
}
