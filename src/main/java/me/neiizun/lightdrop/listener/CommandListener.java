package me.neiizun.lightdrop.listener;

import me.neiizun.lightdrop.LightDrop;
import me.neiizun.lightdrop.command.CommandContext;
import me.neiizun.lightdrop.command.MappedCommand;
import me.neiizun.lightdrop.exceptionhandler.MappedExceptionHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.IntStream;

/**
 * Middleware listener for parsing
 */
public class CommandListener extends ListenerAdapter {
    /**
     * Instance of LightDrop.
     */
    private final LightDrop lightdrop;

    /**
     * Create new CommandListener.
     *
     * @param lightdrop Instance of LightDrop.
     */
    public CommandListener(LightDrop lightdrop) {
        this.lightdrop = lightdrop;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getAuthor().equals(lightdrop.getJda().getSelfUser())) {
            return;
        }

        String message = event.getMessage().getContentRaw();

        if (!message.startsWith(lightdrop.getPrefix())) {
            return;
        }

        String[] args = message.split(" ");

        String commandName = args[0].toLowerCase().replace(lightdrop.getPrefix(), "");

        MappedCommand command = lightdrop.getMappedCommand(commandName);

        for (int i = 1; i < args.length; i++) {
            commandName = commandName + "." + args[i];
            MappedCommand foundSubcommand = lightdrop.getMappedCommand(commandName);

            if (foundSubcommand != null) {
                command = foundSubcommand;
            }
        }

        if (command == null) {
            return;
        }

        CommandContext commandContext = new CommandContext(command, event.getTextChannel(), event.getAuthor(),
                event.getMessage(), ArrayUtils.removeAll(args, IntStream.range(0, command.getNameLength()).toArray()), lightdrop);

        if (lightdrop.getCommandFilters().stream().anyMatch(filter -> filter.test(commandContext))) {
            return;
        }

        String permission = command.getPermission();

        if(!permission.isEmpty() && !event.getMember().hasPermission(Permission.valueOf(permission))) {
            event.getChannel().sendMessage(command.getPermissionMessage().replace("{permission}", permission)).queue();
            return;
        }

        try {
            command.getMethod().invoke(command.getInstance(), commandContext);
        } catch (InvocationTargetException | IllegalAccessException e) {
            for (MappedExceptionHandler handler : lightdrop.getMappedExceptionHandlers()) {
                if (!handler.canHandle(command.getName()) || !handler.getExceptionClass().isAssignableFrom(e.getCause().getClass())) {
                    continue;
                }

                try {
                    handler.getMethod().invoke(command.getInstance(), e.getCause(), commandContext);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
