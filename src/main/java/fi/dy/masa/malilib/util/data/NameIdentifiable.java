package fi.dy.masa.malilib.util.data;

import org.jetbrains.annotations.ApiStatus;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
public interface NameIdentifiable
{
    /**
     * @return the internal (config-savable) name of this object.
     */
    String getName();
}
