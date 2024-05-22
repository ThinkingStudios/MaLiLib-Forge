package org.thinkingstudio.mafglib.util.register;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.thinkingstudio.mafglib.util.ModConfigScreenProvider;

@Deprecated
public interface ModConfigScreenRegister {
    @OnlyIn(Dist.CLIENT)
    void registerModConfigScreen(ModConfigScreenProvider configScreenProvider);
}
