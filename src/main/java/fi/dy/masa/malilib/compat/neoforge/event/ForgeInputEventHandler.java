package fi.dy.masa.malilib.compat.neoforge.event;

import fi.dy.masa.malilib.event.InputEventHandler;

import net.minecraft.client.MinecraftClient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class ForgeInputEventHandler {
    private final MinecraftClient client = MinecraftClient.getInstance();
    @SubscribeEvent
    public void onKeyboardInput(InputEvent.Key event) {
        if (client.currentScreen == null) {
            // This event isn't cancellable, and is fired after vanilla key handling >_>
            // So this one needs to be handled with a Mixin
            ((InputEventHandler) InputEventHandler.getInputManager()).onKeyInput(event.getKey(), event.getScanCode(), event.getModifiers(), event.getAction());
        }
    }

    @SubscribeEvent
    public void onMouseInputEvent(InputEvent.MouseButton.Pre event) {
        int mouseX = 0;
        int mouseY = 0;

        if (client.currentScreen == null && ((InputEventHandler) InputEventHandler.getInputManager()).onMouseClick(mouseX, mouseY, event.getButton(), event.getAction())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onMouseScrollEvent(InputEvent.MouseScrollingEvent event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onMouseScroll((int) event.getMouseX(), (int) event.getMouseY(), 0, event.getScrollDeltaY())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiKeyboardKeyPressPre(ScreenEvent.KeyPressed.Pre event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onKeyInput(event.getKeyCode(), event.getScanCode(), event.getModifiers(), event.getResult().ordinal())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiKeyboardKeyReleasePre(ScreenEvent.KeyReleased.Pre event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onKeyInput(event.getKeyCode(), event.getScanCode(), event.getModifiers(), event.getResult().ordinal())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiMouseClickPre(ScreenEvent.MouseButtonPressed.Pre event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onMouseClick((int) event.getMouseX(), (int) event.getMouseY(), event.getButton(), event.getResult().ordinal())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiMouseReleasePre(ScreenEvent.MouseButtonReleased.Pre event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onMouseClick((int) event.getMouseX(), (int) event.getMouseY(), event.getButton(), event.getResult().ordinal())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiMouseScrolledPre(ScreenEvent.MouseScrolled.Pre event) {
        if (((InputEventHandler) InputEventHandler.getInputManager()).onMouseScroll((int) event.getMouseX(), (int) event.getMouseY(), event.getScrollDeltaX(), 0)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiMouseMovedPre(ScreenEvent.MouseDragged.Pre event) {
        ((InputEventHandler) InputEventHandler.getInputManager()).onMouseMove((int) event.getMouseX(), (int) event.getMouseY());
    }
}