package fi.dy.masa.malilib.gui;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.client.gui.screen.Screen;

/**
 * Wrapper class for Post-Rewrite Compatibility
 */
@ApiStatus.Experimental
public abstract class BaseScreen extends GuiBase
{
    public BaseScreen() {}

    public void initGui()
    {
        super.initGui();
    }

    public static void openScreen(Screen screen)
    {
        GuiBase.openGui(screen);
    }
}
