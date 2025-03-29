package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.fml.IExtensionPoint;

public interface ModInitializer extends IExtensionPoint {
    void onInitialize();
}
