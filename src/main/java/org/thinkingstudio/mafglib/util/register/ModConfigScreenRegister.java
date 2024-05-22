package org.thinkingstudio.mafglib.util.register;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.thinkingstudio.mafglib.util.ModConfigScreenProvider;

@Deprecated
public interface ModConfigScreenRegister {
    @OnlyIn(Dist.CLIENT)
    void registerModConfigScreen(ModConfigScreenProvider configScreenProvider);
}
