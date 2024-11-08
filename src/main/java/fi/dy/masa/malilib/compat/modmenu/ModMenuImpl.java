package fi.dy.masa.malilib.compat.modmenu;

import fi.dy.masa.malilib.MaLiLibConfigGui;
import org.thinkingstudio.mafglib.loader.gui.ModConfigScreenFactory;
import org.thinkingstudio.mafglib.loader.gui.ModConfigScreenInitializer;

public class ModMenuImpl implements ModConfigScreenInitializer
{
    @Override
    public ModConfigScreenFactory getModConfigScreenFactory()
    {
        return (modContainer, screen) -> {
            MaLiLibConfigGui gui = new MaLiLibConfigGui();
            gui.setParent(screen);
            return gui;
        };
    }
}
