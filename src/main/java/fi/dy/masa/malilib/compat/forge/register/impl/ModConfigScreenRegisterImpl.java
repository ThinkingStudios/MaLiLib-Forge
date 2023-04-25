package fi.dy.masa.malilib.compat.forge.register.impl;

import fi.dy.masa.malilib.compat.forge.register.ModConfigScreenRegister;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

import java.util.NoSuchElementException;

public class ModConfigScreenRegisterImpl implements ModConfigScreenRegister {
    private final ModContainer container;

    public ModConfigScreenRegisterImpl(String id) {
        this.container = ModList.get().getModContainerById(id).orElseThrow(() -> new NoSuchElementException("No value present"));
    }

    @Override
    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        container.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> configScreenProvider.provide(screen));
    }
}
