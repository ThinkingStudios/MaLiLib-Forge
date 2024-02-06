package fi.dy.masa.malilib.compat.neoforge.register;

import net.minecraft.client.gui.screen.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public interface ModConfigScreenRegister {
    @OnlyIn(Dist.CLIENT)
    void registerModConfigScreen(ModConfigScreenProvider configScreenProvider);

    @OnlyIn(Dist.CLIENT)
    @FunctionalInterface
    interface ModConfigScreenProvider {
        Screen provide(Screen parent);
    }
}
