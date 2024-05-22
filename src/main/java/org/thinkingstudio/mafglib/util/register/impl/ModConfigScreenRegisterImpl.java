package org.thinkingstudio.mafglib.util.register.impl;

import net.minecraftforge.client.ConfigGuiHandler;
import org.thinkingstudio.mafglib.util.ModConfigScreenProvider;
import org.thinkingstudio.mafglib.util.register.ModConfigScreenRegister;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

@Deprecated
public class ModConfigScreenRegisterImpl implements ModConfigScreenRegister {
    private final ModContainer container;

    public ModConfigScreenRegisterImpl(String id) {
        this.container = ModList.get().getModContainerById(id).orElseThrow();
    }

    @Override
    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        container.registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((minecraft, screen) -> configScreenProvider.provide(screen)));
    }
}
