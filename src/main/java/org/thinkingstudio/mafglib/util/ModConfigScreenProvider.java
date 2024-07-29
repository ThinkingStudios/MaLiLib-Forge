package org.thinkingstudio.mafglib.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Deprecated
@OnlyIn(Dist.CLIENT)
@FunctionalInterface
public interface ModConfigScreenProvider {
    Screen provide(Screen parent);
}