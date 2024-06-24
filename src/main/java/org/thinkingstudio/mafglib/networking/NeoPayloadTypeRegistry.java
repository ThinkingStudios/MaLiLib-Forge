package org.thinkingstudio.mafglib.networking;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.transformers.PacketTransformer;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

import static dev.architectury.impl.NetworkAggregator.*;

public class NeoPayloadTypeRegistry {
    public static <T extends CustomPayload> void registerS2CPayloadType(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec) {
        NetworkManager.registerS2CPayloadType(type, codec);
    }

    public static <T extends CustomPayload> void registerC2SPayloadType(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec) {
        registerC2SType(type, codec, List.of());
    }

    public static void registerC2SType(Identifier id, List<PacketTransformer> packetTransformers) {
        CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload> type = new CustomPayload.Id<>(id);
        C2S_TYPE.put(id, type);
        registerS2CType(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), packetTransformers);
    }

    private static <T extends CustomPayload> void registerC2SType(CustomPayload.Id<T> type, PacketCodec<? super RegistryByteBuf, T> codec, List<PacketTransformer> packetTransformers) {
        Objects.requireNonNull(type, "Cannot register a null type!");
        packetTransformers = Objects.requireNonNullElse(packetTransformers, List.of());
        S2C_CODECS.put(type.id(), (PacketCodec<ByteBuf, ?>) codec);
        S2C_TRANSFORMERS.put(type.id(), PacketTransformer.concat(packetTransformers));
        ADAPTOR.get().registerS2CType((CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload>) type, NetworkAggregator.BufCustomPacketPayload.streamCodec((CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload>) type));
    }
}
