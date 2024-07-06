package org.thinkingstudio.mafglib.util;

import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.CustomPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Objects;

public class NeoNetwork {
    private static PayloadRegistrar registrar;

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
            registrar = event.registrar(MaLiLibReference.MOD_ID).versioned("1").optional();
        });
    }

    public static PayloadRegistrar getPayloadRegistrar() {
        return registrar;
    }

    public static boolean canSend(CustomPayload.Id<?> type) {
        return Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).hasChannel(type);
    }

    public static void sendToServer(CustomPayload payload) {
        PacketDistributor.sendToServer(payload);
    }
}
