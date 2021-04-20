package net.spilledcoffee.bot;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.spilledcoffee.bot.commands.*;
import net.spilledcoffee.bot.events.GuildMemberJoin;


import javax.security.auth.login.LoginException;

public class CoffeeBot {
    public static JDA jda;
    public static String prefix = "-";

    //Main method
    public static void main(String[] args)throws LoginException{
        // Removed key
        JDA jda = JDABuilder.createDefault("").build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("Humans Do Stuff."));

        jda.addEventListener(new Clear());
        // jda.addEventListener(new Commands());
        // jda.addEventListener(new GuildMemberJoin());
        jda.addEventListener(new Help());
        jda.addEventListener(new Cool());
        jda.addEventListener(new Recommend());
        jda.addEventListener(new Purchase());
        jda.addEventListener(new PastOrders());
    }
}
