package fi.dy.masa.malilib;

import fi.dy.masa.malilib.event.InitializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaLiLib {
    public static final Logger logger = LoggerFactory.getLogger(MaLiLibReference.MOD_ID);

    public static void onInitialize() {
        InitializationHandler.getInstance().registerInitializationHandler(new MaLiLibInitHandler());
    }
}
