package me.neiizun.lightdrop.command;

import me.neiizun.lightdrop.LightDrop;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * Context of the command, passed when called.
 */
public class CommandContext {
    /**
     * Mapped version of {@link Command}.
     */
    private final MappedCommand command;

    /**
     * {@link TextChannel} where command was sent.
     */
    private final TextChannel channel;

    /**
     * Author of the command.
     */
    private final User author;

    /**
     * Message where command was executed.
     */
    private final Message message;

    /**
     * Command's arguments (split by " ").
     */
    private final String[] args;

    /**
     * Current {@link LightDrop} instance.
     */
    private final LightDrop lightdrop;

    /**
     * Create a new CommandContext
     *
     * @param command   The {@link MappedCommand}.
     * @param channel   The channel where command was executed.
     * @param author    The author of the command.
     * @param message   The message.
     * @param args      Arguments of the command.
     * @param lightdrop {@link LightDrop} instance.
     */
    public CommandContext(MappedCommand command, TextChannel channel, User author, Message message, String[] args, LightDrop lightdrop) {
        this.command = command;
        this.channel = channel;
        this.author = author;
        this.message = message;
        this.args = args;
        this.lightdrop = lightdrop;
    }

    /**
     * {@inheritDoc}
     */
    public MappedCommand getCommand() {
        return command;
    }

    /**
     * {@inheritDoc}
     */
    public TextChannel getChannel() {
        return channel;
    }

    /**
     * {@inheritDoc}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * {@inheritDoc}
     */
    public Message getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * {@inheritDoc}
     */
    public LightDrop getLightdrop() {
        return lightdrop;
    }
}
