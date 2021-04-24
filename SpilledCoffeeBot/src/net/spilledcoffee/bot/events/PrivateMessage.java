package net.spilledcoffee.bot.events;

import gnu.trove.impl.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class PrivateMessage extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        List<GatewayIntent> gatewayIntents = new ArrayList<>();
        gatewayIntents.add(GatewayIntent.GUILD_MEMBERS);
        JDABuilder jdaBuilder = JDABuilder.createDefault(CoffeeBot.TOKEN);
        jdaBuilder.enableIntents(gatewayIntents);

        event.getMember().getUser().openPrivateChannel().queue(channel -> {
            EmbedBuilder welcome = new EmbedBuilder();
            welcome.setTitle("☕Spilled Coffee: Bot Information");
            welcome.setDescription("Welcome, to Spilled Coffee's Server. Here you will be able " +
                    "to input different commands for your coffee needs, and our bot here will be at your service!" +
                    "\nIf you need further help or command explanations please enter '-help' in your chat.");
            welcome.addField("Commands", "-help\n-recommend\n-purchase\n-pastorders\n-clear\n-cool\n", false);
            welcome.setColor(0xeff542);
            welcome.setFooter("Visit us at 'spilledcoffee.net'");
            channel.sendMessage(welcome.build()).queue();
        });

    }

}
