package fi.dy.masa.malilib.network;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * The Client Network Play handler
 * @param <T> (Payload)
 */
public class ClientPlayHandler<T extends CustomPayload> implements IClientPlayHandler
{
    private static final ClientPlayHandler<CustomPayload> INSTANCE = new ClientPlayHandler<>();
    private final ArrayListMultimap<Identifier, IPluginClientPlayHandler<T>> handlers = ArrayListMultimap.create();
    public static IClientPlayHandler getInstance()
    {
        return INSTANCE;
    }

    private ClientPlayHandler() {}

    @Override
    @SuppressWarnings("unchecked")
    public <P extends CustomPayload> void registerClientPlayHandler(IPluginClientPlayHandler<P> handler)
    {
        Identifier channel = handler.getPayloadChannel();

        if (this.handlers.containsEntry(channel, handler) == false)
        {
            this.handlers.put(channel, (IPluginClientPlayHandler<T>) handler);
        }
    }

    @Override
    public <P extends CustomPayload> void unregisterClientPlayHandler(IPluginClientPlayHandler<P> handler)
    {
        Identifier channel = handler.getPayloadChannel();

        if (this.handlers.remove(channel, handler))
        {
            handler.reset(channel);
            handler.unregisterPlayReceiver();
        }
    }

    /**
     * API CALLS DO NOT USE ANYWHERE ELSE (DANGEROUS!)
     */
    public void reset(Identifier channel)
    {
        if (this.handlers.isEmpty() == false)
        {
            for (IPluginClientPlayHandler<T> handler : this.handlers.get(channel))
            {
                handler.reset(channel);
            }
        }
    }

    /**
     * API CALLS DO NOT USE ANYWHERE ELSE (DANGEROUS!)
     */
    public void decodeNbtCompound(Identifier channel, NbtCompound data)
    {
        if (!this.handlers.isEmpty())
        {
            for (IPluginClientPlayHandler<T> handler : this.handlers.get(channel))
            {
                handler.decodeNbtCompound(channel, data);
            }
        }
    }

    /**
     * API CALLS DO NOT USE ANYWHERE ELSE (DANGEROUS!)
     */
    public void decodeByteBuf(Identifier channel, MaLiLibBuf data)
    {
        if (!this.handlers.isEmpty())
        {
            for (IPluginClientPlayHandler<T> handler : this.handlers.get(channel))
            {
                handler.decodeByteBuf(channel, data);
            }
        }
    }

    /**
     * API CALLS DO NOT USE ANYWHERE ELSE (DANGEROUS!)
     */
    public <D> void decodeObject(Identifier channel, D data1)
    {
        if (!this.handlers.isEmpty())
        {
            for (IPluginClientPlayHandler<T> handler : this.handlers.get(channel))
            {
                handler.decodeObject(channel, data1);
            }
        }
    }
}
