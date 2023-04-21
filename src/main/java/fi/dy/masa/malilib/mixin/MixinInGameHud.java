package fi.dy.masa.malilib.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fi.dy.masa.malilib.event.RenderEventHandler;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(ForgeIngameGui.class)
public abstract class MixinInGameHud extends InGameHud {

    public MixinInGameHud(MinecraftClient client) {
        super(client);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void onGameOverlayPost(MatrixStack matrixStack, float partialTicks, CallbackInfo ci) {
        ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderGameOverlayPost(MinecraftClient.getInstance(), partialTicks, matrixStack);
    }
}
