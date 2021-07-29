package me.neiizun.lightdrop.command;

import java.lang.reflect.Method;

public class MappedCommand {
    private final Object instance;
    private final String name;
    private final String permission;
    private final String permissionMessage;
    private final Method method;

    public MappedCommand(Object instance, String name, String permission, String permissionMessage, Method method) {
        this.instance = instance;
        this.name = name;
        this.permission = permission;
        this.permissionMessage = permissionMessage;
        this.method = method;
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
}
