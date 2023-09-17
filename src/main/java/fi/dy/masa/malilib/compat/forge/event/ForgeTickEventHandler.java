package fi.dy.masa.malilib.compat.forge.event;

import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;

import net.minecraft.client.MinecraftClient;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeTickEventHandler {
    private final MinecraftClient client = MinecraftClient.getInstance();
    @SubscribeEvent
    public void onClientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            KeybindMulti.reCheckPressedKeys();
            TickHandler.getInstance().onClientTick(this.client);
        }
    }
}
