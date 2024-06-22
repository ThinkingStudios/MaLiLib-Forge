package fi.dy.masa.malilib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fi.dy.masa.malilib.event.InitializationHandler;

public class MaLiLib
{
    public static final Logger logger = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public static void onInitialize()
    {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
    }

    public static void printDebug(String key, Object... args)
    {
        if (MaLiLibConfigs.Debug.DEBUG_MESSAGES.getBooleanValue())
        {
            logger.info(key, args);
        }
    }
}
