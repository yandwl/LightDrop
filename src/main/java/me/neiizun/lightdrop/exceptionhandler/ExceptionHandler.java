package me.neiizun.lightdrop.exceptionhandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Handler for exceptions thrown inside commands.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionHandler {
    /**
     * List of the commands to handle (you can put a "*" for all).
     *
     * @return An array of commands names.
     */
    String[] commands();

    /**
     * Handled exception (default Exception.class).
     *
     * @return A class of ? extends Exception.
     */
    Class<? extends Exception> exception() default Exception.class;
}
