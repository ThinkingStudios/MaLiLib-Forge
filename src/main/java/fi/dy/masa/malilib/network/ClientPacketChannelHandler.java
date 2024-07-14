package fi.dy.masa.malilib.network;

import com.google.common.collect.ArrayListMultimap;
//import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import lol.bai.badpackets.api.play.PlayPackets;
import lol.bai.badpackets.impl.registry.ChannelRegistry;
import net.minecraft.util.Identifier;

public class ClientPacketChannelHandler implements IClientPacketChannelHandler
{
    private static final ClientPacketChannelHandler INSTANCE = new ClientPacketChannelHandler();

    private final ArrayListMultimap<Identifier, IPluginChannelHandler> handlers = ArrayListMultimap.create();

    public static IClientPacketChannelHandler getInstance()
    {
        return INSTANCE;
    }

    private ClientPacketChannelHandler()
    {
    }

    @Override
    public void registerClientChannelHandler(IPluginChannelHandler handler)
    {
        Identifier channel = handler.getChannel();

        if (this.handlers.containsEntry(channel, handler) == false)
        {
            this.handlers.put(channel, handler);

            if (handler.registerToServer())
            {
                PlayPackets.registerClientReceiver(channel, handler.getClientPacketHandler());
                //ClientPlayNetworking.registerGlobalReceiver(channel, handler.getClientPacketHandler());
            }
        }
    }

    @Override
    public void unregisterClientChannelHandler(IPluginChannelHandler handler)
    {
        Identifier channel = handler.getChannel();

        if (this.handlers.remove(channel, handler) && handler.registerToServer())
        {
            ChannelRegistry.PLAY_C2S.getChannels().remove(channel);
            //ClientPlayNetworking.unregisterGlobalReceiver(channel);
        }
    }
}
