package com.julioed.thebestbotoftheworld;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

/**
 * @author julioed
 */
public class TheBestBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid
     */

    public TheBestBot() throws LoginException {
        // config - loads the data from .env
        config = Dotenv.configure().load();
        //config.get - gets a specific data from the .env file. In this case, the token code
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Liga das Lendas"));
        shardManager = builder.build();
    }

    public Dotenv getConfig() {
        return config;
    }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for that bot.
     */

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            TheBestBot bot = new TheBestBot();
        } catch (LoginException e) {
            System.out.println("ERROR: provided bot token invalid");
        }

    }
}