package me.neiizun.lightdrop.exceptionhandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Handler for exceptions thrown inside commands
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionHandler {
    /**
     * @return List of the commands to handle (put "*" for all)
     */
    String[] commands();

    /**
     * @return Handled exception (default Exception.class, so all)
     */
    Class<? extends Exception> exception() default Exception.class;
}
