package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;

public class Help extends ListenerAdapter {
    // Method for received commands
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        // Separates commands into a list
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // If user enters -help, bot outputs an Embed highlighting commands
        if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("☕Spilled Coffee: Bot Information");
            info.setDescription("Welcome, to Spilled Coffee's Server. Here you will be able " +
                    "to input different commands for your coffee needs, and our bot here will be at your service!");
            info.addField("Commands", "-help\n-recommend\n-purchase\n-pastorders\n-clear\n-cool\n", false);
            info.setColor(0x5afc03);
            info.setFooter("Welcome ", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping().queue();
            // Delay bot Response
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}
