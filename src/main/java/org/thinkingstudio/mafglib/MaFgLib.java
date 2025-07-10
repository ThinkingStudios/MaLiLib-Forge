package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.compat.modmenu.ModMenuImpl;
import fi.dy.masa.malilib.datagen.MaLiLibDataGen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

import org.thinkingstudio.mafglib.loader.entrypoints.ConfigScreenEntrypoint;
import org.thinkingstudio.mafglib.loader.entrypoints.EntrypointHandler;

@Mod(value = MaLiLibReference.PORT_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer, IEventBus modEventBus) {
        EntrypointHandler.init(modEventBus);
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerExtensionPoint(ConfigScreenEntrypoint.class, new ModMenuImpl());
            MaLiLib.onInitialize();
            modEventBus.addListener(MaLiLibDataGen::onInitializeDataGenerator);
        }
    }
}
