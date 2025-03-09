package fi.dy.masa.malilib;

import fi.dy.masa.malilib.data.MaLiLibTag;
import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.event.InitializationHandler;

public class MaLiLib
{
    public static final Logger LOGGER = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public static void onInitialize() {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
        Registry.CONFIG_SCREEN.registerConfigScreenFactory(
                new ModInfo(MaLiLibReference.MOD_ID, MaLiLibReference.MOD_NAME, MaLiLibConfigGui::new)
        );

        MaLiLibTag.register();
    }

    public static void debugLog(String key, Object... args)
    {
        if (MaLiLibReference.DEBUG_MODE || MaLiLibConfigs.Debug.DEBUG_MESSAGES.getBooleanValue())
        {
            LOGGER.info(key, args);
        }
    }
}
