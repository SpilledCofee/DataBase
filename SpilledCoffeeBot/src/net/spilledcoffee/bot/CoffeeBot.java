package net.spilledcoffee.bot;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.spilledcoffee.bot.commands.*;
import net.spilledcoffee.bot.events.PrivateMessage;
import net.spilledcoffee.bot.events.WelcomeMessages;
import javax.security.auth.login.LoginException;

// Main Class Driver
public class CoffeeBot {
    public static JDA jda;
    public static String prefix = "-"; // Command prefix (indicating that it's a command)

    // TOKEN-REMOVE
    public static final String TOKEN = "Token Here";

    // Database Credentials-REMOVE
    public static String url = "credentials";
    public static String username = "credentials";
    public static String password = "credentials";

    //Main method
    public static void main(String[] args)throws LoginException{
        // JDA Builder
        JDA jda = JDABuilder.createDefault(TOKEN).build();

        // Set status and Activity
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("Humans Do Stuff."));

        // Communicating with classes to run them.
        // jda object listening to appropriate events
        jda.addEventListener(new WelcomeMessages());
        jda.addEventListener(new PrivateMessage());
        jda.addEventListener(new Clear());
        jda.addEventListener(new Help());
        jda.addEventListener(new Recommend());
        jda.addEventListener(new PastOrders());
        jda.addEventListener(new ProductsAvailable());

    }
}
