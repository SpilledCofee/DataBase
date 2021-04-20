package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;

public class PastOrders extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // If user enters -cool, bot outputs silly message
        if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "pastorders")){
            event.getChannel().sendTyping().queue();
            // Delay bot Response
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("These are your past orders:\n").queue();
        }
    }
}
