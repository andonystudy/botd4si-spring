package com.bot.dasi.core;

import com.bot.dasi.category.CoffeeCategory;
import com.bot.dasi.category.ExtraCategory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class Start extends ListenerAdapter {

    private static final String BOT_TOKEN = "NzE0MjYzMTQxNzY4NDk1MTk0.Xs3YmA.psf_8Ox_jgUZwZRn-cskEQvofdg";
    private static final long TEST_CHANNEL = 702274131416317962L;

    @Autowired
    ExtraCategory extraCategory;

    @Autowired
    CoffeeCategory coffeeCategory;

    private JDA jda;

    @PostConstruct
    public void init() throws LoginException {
        jda = new JDABuilder(BOT_TOKEN).build();
        jda.addEventListener(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
//        extraCategory.ping(event, TEST_CHANNEL);
        extraCategory.codeCat(event, TEST_CHANNEL);
        extraCategory.mentionBot(event);
        extraCategory.sendMsgWithBot(event, TEST_CHANNEL);

        coffeeCategory.coffeeService(event, TEST_CHANNEL);
    }

    @Override
    public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {
        extraCategory.messageUpdate(event, TEST_CHANNEL);
    }
}
