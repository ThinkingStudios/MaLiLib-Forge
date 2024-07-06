package org.thinkingstudio.mafglib.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.CustomPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Objects;

public class NeoNetwork {
    public static PayloadRegistrar registrar;

    public static void setRegistrar(IEventBus modEventBus, String version) {
        modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
            registrar = event.registrar(version).optional();
        });
    }

    public static PayloadRegistrar getRegistrar() {
        return registrar;
    }

    public static boolean canSend(CustomPayload.Id<?> type) {
        return Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).hasChannel(type);
    }

    public static void sendToServer(CustomPayload payload) {
        PacketDistributor.sendToServer(payload);
    }
}
