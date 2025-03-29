package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public interface DataGeneratorEntrypoint extends IExtensionPoint {
    void onInitializeDataGenerator(GatherDataEvent event);
}
