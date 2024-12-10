package org.thinkingstudio.mafglib.loader.entrypoints.exceptions;

public class EntrypointLoadingException extends RuntimeException {
    public EntrypointLoadingException() {
        super();
    }

    public EntrypointLoadingException(String message) {
        super(message);
    }

    public EntrypointLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
