package fi.dy.masa.malilib.mixin;

import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import fi.dy.masa.malilib.event.RenderEventHandler;

@Mixin(ExtendedGui.class)
public abstract class MixinExtendedGui
{
    @Inject(method = "render", at = @At("RETURN"))
    private void onGameOverlayPost(DrawContext drawContext, float partialTicks, CallbackInfo ci)
    {
        ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayPost(drawContext, MinecraftClient.getInstance(), partialTicks);
    }
}
