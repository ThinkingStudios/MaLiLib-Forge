package org.thinkingstudio.mafglib.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Objects;

public class NeoNetwork {
    private static PayloadRegistrar registrar = null;

    public static void setRegistrar(IEventBus modEventBus, String version) {
        modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
            registrar = event.registrar(version).optional();
        });
    }

    public static  <T extends CustomPayload> void playToClient(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec, IPayloadHandler<T> handler) {
        registrar.playToClient(type, codec, handler);
    }

    public static <T extends CustomPayload> void playToServer(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec, IPayloadHandler<T> handler) {
        registrar.playToServer(type, codec, handler);
    }

    public static <T extends CustomPayload> void playBidirectional(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec, IPayloadHandler<T> handler) {
        registrar.playBidirectional(type, codec, handler);
    }

    public static boolean canSend(CustomPayload.Id<?> type) {
        return Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).hasChannel(type);
    }

    public static void sendToServer(CustomPayload payload) {
        PacketDistributor.sendToServer(payload);
    }
}
