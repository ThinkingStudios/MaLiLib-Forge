package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public interface ConfigScreenEntrypoint extends IExtensionPoint {
    IConfigScreenFactory getModConfigScreenFactory();
}
