package me.neiizun.lightdrop.exceptionhandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Mapped version of ExceptionHandler annotation
 */
public class MappedExceptionHandler {
    private final List<String> commands;
    private final Object instance;
    private final Method method;
    private final Class<? extends Exception> exceptionClass;

    /**
     * @param commands       List of commands handled
     * @param instance       Instance of mapped class
     * @param method         Method to call
     * @param exceptionClass Class of the handled exception
     */
    public MappedExceptionHandler(String[] commands, Object instance, Method method, Class<? extends Exception> exceptionClass) {
        this.commands = Arrays.asList(commands);
        this.instance = instance;
        this.method = method;
        this.exceptionClass = exceptionClass;
    }

    public Object getInstance() {
        return instance;
    }

    public List<String> getCommands() {
        return commands;
    }

    /**
     * @param commandName name of a command
     * @return true if ExceptionHandler can handle the command
     */
    public boolean canHandle(String commandName) {
        return this.commands.contains(commandName) || this.commands.contains("*");
    }

    public Method getMethod() {
        return method;
    }

    public Class<? extends Exception> getExceptionClass() {
        return exceptionClass;
    }
}
