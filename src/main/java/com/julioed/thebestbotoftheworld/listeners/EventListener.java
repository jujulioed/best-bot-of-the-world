package com.julioed.thebestbotoftheworld.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.nio.channels.Channel;


/**
 * Listens for events from discord and responds with our custom code.
 *
 * @author jujulioed
 */
public class EventListener extends ListenerAdapter {

    /**
     * Event activates when an emoji reaction is added to a message.
     */

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        // Example: ADM reacted to a message with "thumbs up" in the #testing channel
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();

        String message = user.getName() + " reacted to a message with " + emoji + " in the " + channelMention + " channel!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }

    /**
     * Event activates when some user texts "ping".
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

    /**
     * Event activates when a new user arrives.
     */
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String user = event.getUser().getName();
        String message = "Welcome! " + user + ", you are very welcome in our test server!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }
}
