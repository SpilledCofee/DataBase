package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;

public class Help extends ListenerAdapter {
    // Method for received commands

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        // Separates commands into a list
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // If user enters -help, bot outputs an Embed highlighting commands
        if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help") && (args.length ==1)) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("☕Spilled Coffee: Bot Help Desk");
            info.setDescription("Joining this server allows you to enter known commands into your chat box, then our bot will help you " +
                    "execute some of those commands. This server is dedicated to fulfill some of our customers needs at the convenience of Discord." +
                    "\nBelow are accepted commands: ");
            info.addField("Commands", "-help\n-recommend\n-pastorders\n-purchase\n-clear\n-cool\n", false);
            info.addField("Joining", "When you join the server our bot will immediately send you a private direct message, that is where you will enter your desired commands!\n", false);
            info.addField("Extra Notes: ", "For the bot to recognize a command you must enter a '-' in front of the command!\n\nIf you need specific information on how to use a specific command, " +
                    "enter in your chat the help command along with the desired command. \nFor example: '-help [-Command]' (without the brackets) ", false);
            info.setColor(0x5afc03);
            info.setFooter("Helping");

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

        // If user enters -help, along with another Command (EX: Clear),
        // it will output information on how to use that specific command
        else if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help")
                && args[1].equalsIgnoreCase(CoffeeBot.prefix + "clear")) {
            EmbedBuilder helpClear = new EmbedBuilder();
            helpClear.setTitle("Clear Command Information:");
            helpClear.setDescription("The clear command allows users to delete a specified number of message lines. " +
                    "To apply the command, the user must enter the clear command and the desired number of lines wanting " +
                    "to delete. ");
            helpClear.addField("Format:", "-clear [# of messages wanting to delete]\n(Without brackets)", false);
            helpClear.addField("Note:", "You cannot delete more than 100 messages at one time, as well " +
                    "as messages older than 2 weeks.", false);
            helpClear.setColor(0xffa640);
            event.getChannel().sendMessage(helpClear.build()).queue();
            helpClear.clear();
        }

        // If user enters -help, along with another Command (EX: pastorders),
        // it will output information on how to use that specific command
        else if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help")
                && args[1].equalsIgnoreCase(CoffeeBot.prefix + "pastorders")) {
            EmbedBuilder helpPastOrders = new EmbedBuilder();
            helpPastOrders.setTitle("Past Orders Command Information:");
            helpPastOrders.setDescription("The pastorders command allows users to retrieve a list of their past submitted orders" +
                    " from Spilled Coffee. " +
                    "To apply the command, simply enter the pastorders command.");
            helpPastOrders.addField("Format:", "-pastorders", false);
            helpPastOrders.setColor(0xffa640);
            event.getChannel().sendMessage(helpPastOrders.build()).queue();
            helpPastOrders.clear();
        }

        // If user enters -help, along with another Command (EX: purchase),
        // it will output information on how to use that specific command
        else if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help")
                && args[1].equalsIgnoreCase(CoffeeBot.prefix + "purchase")) {
            EmbedBuilder helpPurchase = new EmbedBuilder();
            helpPurchase.setTitle("Purchase Command Information:");
            helpPurchase.setDescription("The purchase command allows users to purchase Spilled Coffee products." +
                    "To apply the command, simply enter the purchase command.");
            helpPurchase.addField("Format:", "-purchase", false);
            helpPurchase.addField("Note:", "As of right now, the user can only purchase 1 product at a time!", false);
            helpPurchase.setColor(0xffa640);
            event.getChannel().sendMessage(helpPurchase.build()).queue();
            helpPurchase.clear();
        }

        // If user enters -help, along with another Command (EX: recommend),
        // it will output information on how to use that specific command
        else if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "help")
                && args[1].equalsIgnoreCase(CoffeeBot.prefix + "recommend")) {
            EmbedBuilder helpRecommend = new EmbedBuilder();
            helpRecommend.setTitle("Recommend Command Information:");
            helpRecommend.setDescription("The recommend command simply asks the Spilled coffee bot " +
                    "to recommend a few Spilled Coffee products to the user." +
                    "To apply the command, simply enter the recommend command.");
            helpRecommend.addField("Format:", "-recommend", false);
            helpRecommend.addField("Note:", "As of right now, the bot recommends 4 random products.", false);
            helpRecommend.setColor(0xffa640);
            event.getChannel().sendMessage(helpRecommend.build()).queue();
            helpRecommend.clear();
        }

    }
}
