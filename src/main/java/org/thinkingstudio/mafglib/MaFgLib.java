package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.event.RenderEventHandler;
import net.minecraft.client.MinecraftClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.thinkingstudio.mafglib.util.NeoUtils;

@Mod(value = MaLiLibReference.MOD_ID, dist = Dist.CLIENT)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            MaLiLib.onInitialize();

            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, RenderGuiLayerEvent.Post.class, event -> {
                ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayPost(
                        event.getGuiGraphics(), MinecraftClient.getInstance(), event.getPartialTick().getTickDelta(false)
                );
            });

            // Config Screen
            NeoUtils.getInstance().registerConfigScreen(modContainer, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
