package me.neiizun.lightdrop.exceptionhandler;

import java.lang.reflect.Method;

public class MappedExceptionHandler {
    private final String[] commands;
    private final Object instance;
    private final Method method;
    private final Class<? extends Exception> exceptionClass;

    public MappedExceptionHandler(String[] commands, Object instance, Method method, Class<? extends Exception> exceptionClass) {
        this.commands = commands;
        this.instance = instance;
        this.method = method;
        this.exceptionClass = exceptionClass;
    }

    public Object getInstance() {
        return instance;
    }

    public String[] getCommands() {
        return commands;
    }

    public Method getMethod() {
        return method;
    }

    public Class<? extends Exception> getExceptionClass() {
        return exceptionClass;
    }
}
