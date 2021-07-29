package me.neiizun.lightdrop;

import me.neiizun.lightdrop.command.Command;
import me.neiizun.lightdrop.command.CommandContext;
import me.neiizun.lightdrop.command.MappedCommand;
import me.neiizun.lightdrop.exceptionhandler.ExceptionHandler;
import me.neiizun.lightdrop.exceptionhandler.MappedExceptionHandler;
import me.neiizun.lightdrop.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;

public class LightDrop {
    private String prefix = "!";
    private final Map<String, MappedCommand> mappedCommandMap = new HashMap<>();
    private final List<MappedExceptionHandler> mappedExceptionHandlers = new ArrayList<>();
    private final List<Predicate<CommandContext>> commandFilters = new ArrayList<>();

    public LightDrop hook(JDA jdaHook) {
        jdaHook.addEventListener(new CommandListener(this));
        LoggerFactory.getLogger(LightDrop.class).info("Hooked into " + jdaHook.getSelfUser().getName());
        return this;
    }

    public LightDrop map(Object... objects) {
        Arrays.stream(objects).forEach(object -> {
            Class<?> clazz = object.getClass();

            for (Method method : clazz.getMethods()) {
                Command command = method.getAnnotation(Command.class);

                if (command != null) {
                    if(!mappedCommandMap.containsKey(command.name())) {

                        mappedCommandMap.put(command.name(), new MappedCommand(object, command.name(),
                                command.permission(), command.permissionMessage(), method));

                        LoggerFactory.getLogger(LightDrop.class).info("Command '" + command.name() + "' mapped from " + clazz.getName());
                    } else {
                        LoggerFactory.getLogger(LightDrop.class).error("Command '" + command.name() + "' is already mapped");
                    }
                } else {
                    ExceptionHandler exceptionhandler = method.getAnnotation(ExceptionHandler.class);

                    if(exceptionhandler != null) {
                        mappedExceptionHandlers.add(new MappedExceptionHandler(exceptionhandler.commands(), object, method, exceptionhandler.exception()));
                        LoggerFactory.getLogger(LightDrop.class).info("New ExceptionHandler mapped from " + clazz.getName());
                    }
                }
            }
        });

        return this;
    }

    public MappedCommand getMappedCommand(String commandName) {
        return this.mappedCommandMap.get(commandName);
    }

    public Optional<MappedCommand> findMappedCommand(String commandName) {
        return Optional.ofNullable(this.getMappedCommand(commandName));
    }

    public LightDrop filter(Predicate<CommandContext>... predicate) {
        this.commandFilters.addAll(Arrays.asList(predicate));
        return this;
    }

    public List<Predicate<CommandContext>> getCommandFilters() {
        return commandFilters;
    }

    public List<MappedExceptionHandler> getMappedExceptionHandlers() {
        return mappedExceptionHandlers;
    }

    public String getPrefix() {
        return prefix;
    }

    public LightDrop setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
