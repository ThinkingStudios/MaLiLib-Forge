package org.thinkingstudio.mafglib.loader.gui;

import net.neoforged.fml.IExtensionPoint;

public interface ModConfigScreenInitializer extends IExtensionPoint {
    ModConfigScreenFactory getModConfigScreenFactory();
}
