package org.thinkingstudio.mafglib;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.MaLiLibConfigGui;
import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thinkingstudio.mafglib.util.ForgeUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(MaLiLibReference.MOD_ID)
public class MaFgLib {
    public static final Logger LOGGER = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public MaFgLib() {
        if (FMLLoader.getDist().isClient()) {
            ModContainer modContainer = ModLoadingContext.get().getActiveContainer();

            // Make sure the mod being absent on the other network side does not cause
            // the client to display the server as incompatible
            ForgeUtils.getInstance().getClientModIgnoredServerOnly(modContainer);
            MaLiLib.onInitialize();

            // Config Screen
            ForgeUtils.getInstance().registerModConfigScreen(modContainer, (screen) -> {
                MaLiLibConfigGui gui = new MaLiLibConfigGui();
                gui.setParent(screen);
                return gui;
            });

            if (ModList.get().isLoaded("key_binding_patch")) {
                LOGGER.warn("MaFgLib has detected that Key Binding Parch is loaded, this mod is reported by issue to be potentially incompatible with MaFgLib.");
                LOGGER.warn("MaFgLib is not compatible with Key Binding Parch related issue: https://github.com/ThinkingStudios/MaLiLib-Forge/issues/76");
            }
        }
    }
}
