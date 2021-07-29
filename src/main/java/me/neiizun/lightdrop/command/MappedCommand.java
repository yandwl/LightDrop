package me.neiizun.lightdrop.command;

import java.lang.reflect.Method;

/**
 * Mapped command from annotation
 */
public class MappedCommand {
    private final Object instance;
    private final String name;
    private final String permission;
    private final String permissionMessage;
    private final Method method;
    private final int nameLength;

    /**
     *
     * @param instance The instance of the mapped class
     * @param name name of the command
     * @param permission permission of the command
     * @param permissionMessage permission message
     * @param method method of the command
     */
    public MappedCommand(Object instance, String name, String permission, String permissionMessage, Method method) {
        this.instance = instance;
        this.name = name;
        this.permission = permission;
        this.permissionMessage = permissionMessage;
        this.method = method;
        this.nameLength = name.split("\\.").length;
    }

    public Object getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermissionMessage() {
        return permissionMessage;
    }

    public Method getMethod() {
        return method;
    }

    public int getNameLength() {
        return nameLength;
    }
}
