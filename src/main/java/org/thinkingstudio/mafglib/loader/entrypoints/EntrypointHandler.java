package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.Optional;

public class EntrypointHandler {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(FMLClientSetupEvent.class, event -> {
            ModList.get().forEachModContainer((modId, container) -> {
                if (FMLLoader.getDist().isClient()) {
                    Optional<ConfigScreenEntrypoint> configScreen = container.getCustomExtension(ConfigScreenEntrypoint.class);
                    configScreen.ifPresent(entrypoint -> {
                        container.registerExtensionPoint(IConfigScreenFactory.class, entrypoint.getModConfigScreenFactory());
                    });
                }
            });
        });
    }
}
