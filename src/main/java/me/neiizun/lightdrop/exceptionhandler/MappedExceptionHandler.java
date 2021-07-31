package me.neiizun.lightdrop.exceptionhandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Mapped version of ExceptionHandler annotation.
 *
 * @since 1.0.0
 */
public class MappedExceptionHandler {
    /**
     * List of handled commands.
     */
    private final List<String> commands;

    /**
     * Instance of mapped object.
     */
    private final Object instance;

    /**
     * Mapped method.
     */
    private final Method method;

    /**
     * Class of the handled exception.
     */
    private final Class<? extends Exception> exceptionClass;

    /**
     * Create a new MappedExceptionHandler.
     *
     * @param commands       List of commands handled.
     * @param instance       Instance of mapped class.
     * @param method         Method to call.
     * @param exceptionClass Class of the handled exception.
     */
    public MappedExceptionHandler(String[] commands, Object instance, Method method, Class<? extends Exception> exceptionClass) {
        this.commands = Arrays.asList(commands);
        this.instance = instance;
        this.method = method;
        this.exceptionClass = exceptionClass;
    }

    /**
     * {@inheritDoc}
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Check if a command can be handled.
     *
     * @param commandName name of a command.
     * @return true if ExceptionHandler can handle the command.
     */
    public boolean canHandle(String commandName) {
        return this.commands.contains(commandName) || this.commands.contains("*");
    }

    /**
     * {@inheritDoc}
     */
    public Method getMethod() {
        return method;
    }

    /**
     * {@inheritDoc}
     */
    public Class<? extends Exception> getExceptionClass() {
        return exceptionClass;
    }
}
