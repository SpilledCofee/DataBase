package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Clear extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        // Separates commands into a list
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(CoffeeBot.prefix+"clear")){
            if(args.length < 2){
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(0xff3923);
                usage.setTitle("How to Use the Delete Command: ");
                usage.setDescription("Format: `" + CoffeeBot.prefix + "clear[# of messages]`");
                event.getChannel().sendMessage(usage.build()).queue();
            }
            else{
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();

                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0x03fcd7);
                    success.setTitle("✔ Successfully Deleted " + args[1] + " Messages ✔");
                    event.getChannel().sendMessage(success.build()).queue();
                }
                // Catching errors from clear command
                catch (IllegalArgumentException e){
                    // To many Messages
                    if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval limit is between 1 and 100 messages. No more, no less.")){
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("⚠🔴Error: To many messages selected!");
                        error.setDescription("Only 1-100 messages can be deleted at one time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                    // Messages are to old
                    else{
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("⚠🔴Error: Selected messages are older than 2 weeks!");
                        error.setDescription("Messages older than 2 weeks cannot be deleted.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }
            }
        }

    }
}
