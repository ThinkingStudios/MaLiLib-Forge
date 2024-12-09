package org.thinkingstudio.mafglib.loader.entrypoints;

import net.neoforged.neoforgespi.locating.IModFile;

/**
 * @param entrypoint entrypoint of the container
 * @param mod        which mod hold the container
 * @author DustW
 */
public record EntrypointContainer<T>(T entrypoint, IModFile mod) {
}
