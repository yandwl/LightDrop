package me.neiizun.lightdrop.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command annotation for creating commands
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    /**
     *
     * @return Name of the command (use "." to specify sub commands)
     */
    String name();

    /**
     * @return Permission for command execution (default none)
     */
    String permission() default "";

    /**
     * @return Permission message
     */
    String permissionMessage() default "Seems like you don't have the permission `{permission}`";
}