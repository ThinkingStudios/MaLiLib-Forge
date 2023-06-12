package fi.dy.masa.malilib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fi.dy.masa.malilib.compat.forge.ForgePlatformCompat;
import fi.dy.masa.malilib.event.InitializationHandler;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onInitialize);
        modEventBus.addListener(this::onInitializeClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onInitialize(FMLCommonSetupEvent event) {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
    }

    public void onInitializeClient(FMLClientSetupEvent event) {
        ForgePlatformCompat.getInstance().getMod(MaLiLibReference.MOD_ID).registerModConfigScreen((screen) -> {
            MaLiLibConfigGui gui = new MaLiLibConfigGui();
            gui.setParent(screen);
            return gui;
        });
    }
}
