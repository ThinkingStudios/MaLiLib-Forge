package org.thinkingstudio.mafglib.loader.gui;

import net.neoforged.fml.IExtensionPoint;

@Deprecated(forRemoval = true, since = "1.21.5")
public interface ModConfigScreenInitializer extends IExtensionPoint {
    ModConfigScreenFactory getModConfigScreenFactory();
}
