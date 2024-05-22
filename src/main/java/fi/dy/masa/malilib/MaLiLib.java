package fi.dy.masa.malilib;

import net.minecraftforge.fml.common.Mod;
import fi.dy.masa.malilib.event.InitializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MaLiLibReference.MOD_ID)
public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public static void onInitialize() {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
    }
}
