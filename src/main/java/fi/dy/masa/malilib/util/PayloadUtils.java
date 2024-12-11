package fi.dy.masa.malilib.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtSizeTracker;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.DynamicRegistryManager;
import fi.dy.masa.malilib.network.MaLiLibBuf;

@Deprecated(forRemoval = true)
public class PayloadUtils
{
    @Nullable
    public static PacketByteBuf toPacketByteBuf(@Nonnull MaLiLibBuf in)
    {
        if (in.isReadable())
        {
            return new PacketByteBuf(in.asByteBuf());
        }

        return null;
    }

    @Nullable
    public static RegistryByteBuf toRegistryByteBuf(@Nonnull MaLiLibBuf in,
                                                    @Nonnull DynamicRegistryManager registryManager)
    {
        if (in.isReadable() && registryManager.equals(DynamicRegistryManager.EMPTY) == false)
        {
            return new RegistryByteBuf(in.asByteBuf(), registryManager);
        }

        return null;
    }

    @Nullable
    public static MaLiLibBuf fromPacketByteBuf(@Nonnull PacketByteBuf in)
    {
        if (in.isReadable())
        {
            return new MaLiLibBuf(in.asByteBuf());
        }

        return null;
    }

    @Nullable
    public static MaLiLibBuf fromRegistryByteBuf(@Nonnull RegistryByteBuf in)
    {
        if (in.isReadable())
        {
            return new MaLiLibBuf(in.asByteBuf());
        }

        return null;
    }

    @Nullable
    public static NbtElement toNbtElement(@Nonnull MaLiLibBuf in)
    {
        if (in.isReadable())
        {
            return in.readNbt(NbtSizeTracker.of(in.readableBytes()));
        }

        return null;
    }

    @Nullable
    public static NbtCompound toNbtCompound(@Nonnull MaLiLibBuf in)
    {
        if (in.isReadable())
        {
            return in.readNbt();
        }

        return null;
    }

    @Nullable
    public static MaLiLibBuf fromNbtElement(@Nonnull NbtElement in)
    {
        if (in.getSizeInBytes() > 0)
        {
            MaLiLibBuf buf = new MaLiLibBuf(Unpooled.buffer());
            buf.writeNbt(in);

            return buf;
        }

        return null;
    }

    @Nullable
    public static MaLiLibBuf fromNbtCompound(@Nonnull NbtCompound in)
    {
        if (in.getSizeInBytes() > 0)
        {
            MaLiLibBuf buf = new MaLiLibBuf(Unpooled.buffer());
            buf.writeNbt(in);

            return buf;
        }

        return null;
    }
}
