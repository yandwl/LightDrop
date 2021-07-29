package me.neiizun.lightdrop.listeners;

import me.neiizun.lightdrop.LightDrop;
import me.neiizun.lightdrop.command.CommandContext;
import me.neiizun.lightdrop.command.MappedCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CommandListener extends ListenerAdapter {
    private final LightDrop lightdrop;

    public CommandListener(LightDrop lightdrop) {
        this.lightdrop = lightdrop;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if(!message.startsWith(lightdrop.getPrefix())) return;

        String[] args = message.split(" ");

        MappedCommand command = this.lightdrop.getMappedCommand(args[0].toLowerCase().replace(lightdrop.getPrefix(), ""));

        if(command == null) return;

        CommandContext commandContext = new CommandContext(command, event.getTextChannel(), event.getAuthor(),
                event.getMessage(), ArrayUtils.remove(args, 0));

        if(lightdrop.getCommandFilters().stream().anyMatch(filter -> filter.test(commandContext)))
            return;

        String permission = command.getPermission();

        if(!permission.isEmpty() && !event.getMember().hasPermission(Permission.valueOf(permission))) {
            event.getChannel().sendMessage(command.getPermissionMessage().replace("{permission}", permission)).queue();
            return;
        }

        try {
            command.getMethod().invoke(command.getInstance(), commandContext);
        } catch (InvocationTargetException | IllegalAccessException e) {
            lightdrop.getMappedExceptionHandlers().stream().filter(mappedHandler ->
                    (Arrays.asList(mappedHandler.getCommands()).contains(command.getName())
                            || Arrays.asList(mappedHandler.getCommands()).contains("*"))
                    && mappedHandler.getExceptionClass().isAssignableFrom(e.getCause().getClass()))
                    .forEach(mappedHandler -> {
                        try {
                            mappedHandler.getMethod().invoke(mappedHandler.getInstance(), e, commandContext);
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
    }
}
