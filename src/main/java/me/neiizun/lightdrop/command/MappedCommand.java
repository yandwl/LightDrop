package me.neiizun.lightdrop.command;

import java.lang.reflect.Method;

/**
 * Mapped command from annotation
 */
public class MappedCommand {
    /**
     * Instance of mapped object.
     */
    private final Object instance;

    /**
     * Name of command.
     */
    private final String name;

    /**
     * Permission of command.
     */
    private final String permission;

    /**
     * Permission message when don't have permission.
     */
    private final String permissionMessage;

    /**
     * Mapped method.
     */
    private final Method method;

    /**
     * Length of the name's subcommands.
     */
    private final int nameLength;

    /**
     * Create new MappedCommand.
     *
     * @param instance          The instance of the mapped class.
     * @param name              name of the command.
     * @param permission        permission of the command.
     * @param permissionMessage permission message.
     * @param method            method of the command.
     */
    public MappedCommand(Object instance, String name, String permission, String permissionMessage, Method method) {
        this.instance = instance;
        this.name = name;
        this.permission = permission;
        this.permissionMessage = permissionMessage;
        this.method = method;
        this.nameLength = name.split("\\.").length;
    }

    /**
     * Get the instance of mapped object.
     *
     * @return Object instance.
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public String getPermission() {
        return permission;
    }

    /**
     * {@inheritDoc}
     */
    public String getPermissionMessage() {
        return permissionMessage;
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
    public int getNameLength() {
        return nameLength;
    }
}
