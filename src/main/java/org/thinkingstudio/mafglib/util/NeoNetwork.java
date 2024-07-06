package org.thinkingstudio.mafglib.util;

import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NeoNetwork {
    public static PayloadRegistrar registrar;

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
            registrar = event.registrar(MaLiLibReference.MOD_ID).versioned("1").optional();
        });
    }
}
