package fi.dy.masa.malilib.compat.modmenu;

import fi.dy.masa.malilib.MaLiLibConfigGui;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.thinkingstudio.mafglib.loader.entrypoints.ConfigScreenEntrypoint;

public class ModMenuImpl implements ConfigScreenEntrypoint
{
    @Override
    public IConfigScreenFactory getModConfigScreenFactory()
    {
        return (modContainer, screen) -> {
            MaLiLibConfigGui gui = new MaLiLibConfigGui();
            gui.setParent(screen);
            return gui;
        };
    }
}
