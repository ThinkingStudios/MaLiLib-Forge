package fi.dy.masa.malilib.compat.forge.event;

import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeTickEventHandler {
    @SubscribeEvent
    public void onClientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == Phase.END) {
            KeybindMulti.reCheckPressedKeys();
            TickHandler.getInstance().onClientTick(MinecraftClient.getInstance());
        }
    }
}
