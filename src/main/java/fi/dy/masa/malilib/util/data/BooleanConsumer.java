package fi.dy.masa.malilib.util.data;

import org.jetbrains.annotations.ApiStatus;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
@FunctionalInterface
public interface BooleanConsumer
{
    void accept(boolean value);
}
