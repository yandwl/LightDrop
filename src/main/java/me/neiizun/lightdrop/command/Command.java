package me.neiizun.lightdrop.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    String name();

    String permission() default "";

    String permissionMessage() default "Seems like you don't have the permission `{permission}`";
}