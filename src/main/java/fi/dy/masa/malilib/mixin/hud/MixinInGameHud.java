package fi.dy.masa.malilib.mixin.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.neoforged.neoforge.client.gui.GuiLayerManager;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fi.dy.masa.malilib.event.RenderEventHandler;
import fi.dy.masa.malilib.util.game.IGameHud;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud implements IGameHud
{
    @Shadow @Final private MinecraftClient client;
    //@Shadow @Final private LayeredDrawer layeredDrawer;
    @Shadow private int overlayRemaining;

    @Shadow @Final private GuiLayerManager layerManager;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo info)
    {
        this.layerManager.add(VanillaGuiLayers.SUBTITLE_OVERLAY, this::malilib_renderGameOverlayLastDrawer);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void malilib_onGameOverlayPost(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci)
    {
        ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayPost(context, this.client, tickCounter.getTickProgress(false));
    }

    @Unique
    private void malilib_renderGameOverlayLastDrawer(DrawContext context, RenderTickCounter tickCounter)
    {
        ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayLastDrawer(context, this.client, tickCounter.getTickProgress(false));
    }

    @Override
    public void malilib$setOverlayRemaining(int ticks)
    {
        this.overlayRemaining = ticks;
    }
}
