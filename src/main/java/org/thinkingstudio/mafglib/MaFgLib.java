package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.event.RenderEventHandler;
import net.minecraft.client.MinecraftClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.thinkingstudio.mafglib.util.NeoUtils;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public MaFgLib(ModContainer modContainer) {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            // Make sure the mod being absent on the other network side does not cause
            // the client to display the server as incompatible
            NeoUtils.getInstance().getClientModIgnoredServerOnly(modContainer);
            MaLiLib.onInitialize();

            // Config Screen
            NeoUtils.getInstance().registerModConfigScreen(modContainer, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });

            NeoForge.EVENT_BUS.addListener(RenderGuiEvent.Post.class, event -> {
                ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayPost(event.getGuiGraphics(), MinecraftClient.getInstance(), event.getPartialTick());
            });
        }
    }
}
