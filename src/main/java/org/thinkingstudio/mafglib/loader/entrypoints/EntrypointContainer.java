package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.neoforgespi.locating.IModFile;

/**
 * @param entrypoint entrypoint of the container
 * @param modFile   which mod hold the container
 * @author DustW
 */
public record EntrypointContainer<T>(T entrypoint, IModFile modFile) {
    public T getEntrypoint() {
        return entrypoint;
    }

    public IModFile getModFile() {
        return modFile;
    }
}
