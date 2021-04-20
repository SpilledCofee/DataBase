package net.spilledcoffee.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GuildMemberJoin extends ListenerAdapter {
        String[] intros = {
                "It's bird! It's a plane! NO, it's [member] arriving!",
                "Here's [member]!",
                "We've been expecting you [member]!",
                "You shall not pass! Except for [member].",
                "“Keep your friends close, but [member] closer.” ",
                "[member], I have a feeling we're not in Kansas anymore.",
                "[member] your landing was successful!",
                "[member] joined the pizza party!",
                "A mysterious individual named [member] has arrived.",
                "Wherefore art thou [member]?"
        };

    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(intros.length);

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0xc160eb);
        join.setDescription(intros[number].replace("[member]", event.getMember().getAsMention()));

        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();

        // Add member roles
        //event.getGuild().addRoleToMember(event.getMember(), (Role) event.getGuild().getRolesByName("Member", true)).complete();
    }
}
