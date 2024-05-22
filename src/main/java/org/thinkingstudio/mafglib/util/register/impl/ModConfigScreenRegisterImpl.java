package org.thinkingstudio.mafglib.util.register.impl;

import org.thinkingstudio.mafglib.util.ModConfigScreenProvider;
import org.thinkingstudio.mafglib.util.register.ModConfigScreenRegister;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.ConfigScreenHandler;

@Deprecated
public class ModConfigScreenRegisterImpl implements ModConfigScreenRegister {
    private final ModContainer container;

    public ModConfigScreenRegisterImpl(String id) {
        this.container = ModList.get().getModContainerById(id).orElseThrow();
    }

    @Override
    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> configScreenProvider.provide(screen)));
    }
}
