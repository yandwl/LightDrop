package me.neiizun.lightdrop.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command annotation for creating commands.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    /**
     * Name of the command (use "." to specify sub commands).
     *
     * @return String of the command's name.
     */
    String name();

    /**
     * Permission for command executing (default none).
     *
     * @return String of the permission.
     */
    String permission() default "";

    /**
     * Message when don't have the permission.
     *
     * @return String of the message.
     */
    String permissionMessage() default "Seems like you don't have the permission `{permission}`";
}