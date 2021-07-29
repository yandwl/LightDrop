package me.neiizun.lightdrop.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandContext {
    private final MappedCommand command;
    private final TextChannel channel;
    private final User author;
    private final Message message;
    private final String[] args;

    public CommandContext(MappedCommand command, TextChannel channel, User author, Message message, String[] args) {
        this.command = command;
        this.channel = channel;
        this.author = author;
        this.message = message;
        this.args = args;
    }

    public MappedCommand getCommand() {
        return command;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public User getAuthor() {
        return author;
    }

    public Message getMessage() {
        return message;
    }

    public String[] getArgs() {
        return args;
    }
}
