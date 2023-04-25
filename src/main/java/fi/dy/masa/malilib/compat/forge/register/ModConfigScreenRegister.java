package fi.dy.masa.malilib.compat.forge.register;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ModConfigScreenRegister {
    @OnlyIn(Dist.CLIENT)
    void registerModConfigScreen(ModConfigScreenProvider configScreenProvider);

    @OnlyIn(Dist.CLIENT)
    @FunctionalInterface
    interface ModConfigScreenProvider {
        Screen provide(Screen parent);
    }
}
