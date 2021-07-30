package me.neiizun.lightdrop;

import me.neiizun.lightdrop.command.Command;
import me.neiizun.lightdrop.command.CommandContext;
import me.neiizun.lightdrop.command.MappedCommand;
import me.neiizun.lightdrop.exceptionhandler.ExceptionHandler;
import me.neiizun.lightdrop.exceptionhandler.MappedExceptionHandler;
import me.neiizun.lightdrop.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;

/**
 * Main class of LightDrop.
 */
public class LightDrop {
    /**
     * JDA instance.
     */

    private JDA jda;
    /**
     * The command's prefix.
     */
    private String prefix = "!";

    /**
     * The map of all mapped commands by name / instance.
     */
    private final Map<String, MappedCommand> mappedCommandMap = new HashMap<>();

    /**
     * The list of all ExceptionHandlers.
     */
    private final List<MappedExceptionHandler> mappedExceptionHandlers = new ArrayList<>();

    /**
     * The list of all filters.
     */
    private final List<Predicate<CommandContext>> commandFilters = new ArrayList<>();

    /**
     * Hook LightDrop to your JDA instance.
     *
     * @param jda JDA instance.
     * @return Current LightDrop instance for chaining.
     */
    public LightDrop hook(JDA jda) {
        jda.addEventListener(new CommandListener(this));
        this.jda = jda;
        LoggerFactory.getLogger(LightDrop.class).info("Hooked into " + jda.getSelfUser().getName());
        return this;
    }

    /**
     * Map an object to load commands and exception handlers.
     *
     * @param objects Objects to map.
     * @return Current LightDrop instance for chaining.
     */
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

    /**
     * Get a {@link MappedCommand} by his name from registered mapped commands.
     *
     * @param commandName The name of the command.
     * @return Instance of {@link MappedCommand} or null if not found.
     */
    public MappedCommand getMappedCommand(String commandName) {
        return this.mappedCommandMap.get(commandName);
    }

    /**
     * Put global filters for commands, if filter return true, the command won't be executed.
     *
     * @param predicate A predicate of the filter.
     * @return Current LightDrop instance for chaining.
     */
    public LightDrop filter(Predicate<CommandContext>... predicate) {
        this.commandFilters.addAll(Arrays.asList(predicate));
        return this;
    }

    public List<Predicate<CommandContext>> getCommandFilters() {
        return commandFilters;
    }

    /**
     * {@inheritDoc}
     */
    public List<MappedExceptionHandler> getMappedExceptionHandlers() {
        return mappedExceptionHandlers;
    }

    /**
     * {@inheritDoc}
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * {@inheritDoc}
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * Set the prefix of the commands (default !).
     *
     * @param prefix The prefix.
     * @return Current LightDrop instance for chaining.
     */
    public LightDrop setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
