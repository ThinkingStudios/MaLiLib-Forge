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
import org.thinkingstudio.mafglib.loader.entrypoints.ConfigScreenEntrypoint;
import org.thinkingstudio.mafglib.loader.entrypoints.DataGeneratorEntrypoint;
import org.thinkingstudio.mafglib.loader.entrypoints.ModInitializer;


import java.util.Optional;

@Mod(value = MaLiLibReference.MOD_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer, IEventBus modEventBus) {
        modEventBus.addListener(FMLLoadCompleteEvent.class, event -> {
            ModList.get().forEachModContainer((modId, container) -> {
                Optional<ModInitializer> modInitializer = container.getCustomExtension(ModInitializer.class);
                Optional<DataGeneratorEntrypoint> dataGenerator = container.getCustomExtension(DataGeneratorEntrypoint.class);
                modInitializer.ifPresent(ModInitializer::onInitialize);
                dataGenerator.ifPresent(entrypoint -> {
                    modEventBus.addListener(entrypoint::onInitializeDataGenerator);
                });
                if (FMLLoader.getDist().isClient()) {
                    Optional<ConfigScreenEntrypoint> configScreen = container.getCustomExtension(ConfigScreenEntrypoint.class);
                    configScreen.ifPresent(entrypoint -> {
                        container.registerExtensionPoint(IConfigScreenFactory.class, entrypoint.getModConfigScreenFactory());
                    });
                }
            });
        });
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerExtensionPoint(ConfigScreenEntrypoint.class, new ModMenuImpl());
            modContainer.registerExtensionPoint(ModInitializer.class, new MaLiLib());
            modContainer.registerExtensionPoint(DataGeneratorEntrypoint.class, new MaLiLibDataGen());
        }
    }
}
