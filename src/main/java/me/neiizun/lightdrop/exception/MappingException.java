package me.neiizun.lightdrop.exception;

/**
 * Exception thrown when there is an error while mapping.
 *
 * @since 1.0.0
 */
public class MappingException extends RuntimeException {
    /**
     * Create new MappingException.
     *
     * @param message Message of the exception.
     */
    public MappingException(String message) {
        super(message);
    }
}
