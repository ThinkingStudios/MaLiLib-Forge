package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.compat.modmenu.ModMenuImpl;
import fi.dy.masa.malilib.datagen.MaLiLibDataGen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.thinkingstudio.mafglib.loader.FoxifiedLoader;
import org.thinkingstudio.mafglib.loader.gui.ModConfigScreenInitializer;

import java.util.Optional;

@Mod(value = MaLiLibReference.MOD_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer, IEventBus modEventBus) {
        if (FMLLoader.getDist().isClient()) {
            FoxifiedLoader.registerExtensionPoint(modContainer, ModConfigScreenInitializer.class, new ModMenuImpl());
            MaLiLib.onInitialize();
            modEventBus.addListener(MaLiLibDataGen::onInitializeDataGenerator);

            modEventBus.addListener(FMLLoadCompleteEvent.class, event -> {
                ModList.get().forEachModContainer((modId, container) -> {
                    Optional<ModConfigScreenInitializer> extension = container.getCustomExtension(ModConfigScreenInitializer.class);
                    extension.ifPresent(init -> {
                        container.registerExtensionPoint(IConfigScreenFactory.class, init.getModConfigScreenFactory());
                    });
                });
            });
        }
    }
}
