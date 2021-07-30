package me.neiizun.lightdrop.registion;

import net.dv8tion.jda.api.JDA;

public class BotController {
    String botName;
    JDA jda;

    public BotController(String botName) {
        this.botName = botName;
    }

    public BotController(String botName, JDA jda) {
        this.botName = botName;
        this.jda = jda;
    }

    public String getBotName() {
        return botName;
    }


    public JDA getJda() {
        return jda;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }
}
