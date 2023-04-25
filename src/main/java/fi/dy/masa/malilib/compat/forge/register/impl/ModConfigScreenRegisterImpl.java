package fi.dy.masa.malilib.compat.forge.register.impl;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

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
