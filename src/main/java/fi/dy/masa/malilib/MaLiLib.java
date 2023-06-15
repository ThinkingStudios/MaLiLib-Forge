package fi.dy.masa.malilib;

import fi.dy.masa.malilib.compat.forge.event.ForgeInputEventHandler;
import fi.dy.masa.malilib.compat.forge.event.ForgeTickEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import fi.dy.masa.malilib.compat.forge.ForgePlatformCompat;
import fi.dy.masa.malilib.event.InitializationHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public MaLiLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onInitializeClient);
        modEventBus.addListener(this::onInterModProcess);
    }

    public void onInitializeClient(FMLClientSetupEvent event) {
        ForgePlatformCompat.getInstance().getModClientExtensionPoint();
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
        ForgePlatformCompat.getInstance().getMod(MaLiLibReference.MOD_ID).registerModConfigScreen((screen) -> {
            MaLiLibConfigGui gui = new MaLiLibConfigGui();
            gui.setParent(screen);
            return gui;
        });

        MinecraftForge.EVENT_BUS.register(new ForgeInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ForgeTickEventHandler());
    }

    public void onInterModProcess(InterModProcessEvent event) {
        ((InitializationHandler) InitializationHandler.getInstance()).onGameInitDone();
    }
}
