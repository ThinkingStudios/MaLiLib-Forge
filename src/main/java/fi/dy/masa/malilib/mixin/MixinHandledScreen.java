package fi.dy.masa.malilib.mixin;

import net.minecraft.screen.ScreenHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;

import fi.dy.masa.malilib.event.RenderEventHandler;

@Mixin(HandledScreen.class)
public abstract class MixinHandledScreen<T extends ScreenHandler> {
    @Shadow @Nullable protected Slot focusedSlot;

    @Shadow @Final protected T handler;

    @Inject(method = "drawMouseoverTooltip", at = @At("TAIL"))
    private void onRenderTooltip(DrawContext drawContext, int x, int y, CallbackInfo ci) {
        if (this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderTooltipLast(drawContext, this.focusedSlot.getStack(), x, y);
        }
    }
}
