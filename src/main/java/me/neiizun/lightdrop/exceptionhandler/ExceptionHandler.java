package me.neiizun.lightdrop.exceptionhandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionHandler {
    String[] commands();
    Class<? extends Exception> exception() default Exception.class;
}
