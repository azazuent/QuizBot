package org.example.telegram.configuration;

import com.squareup.okhttp.OkHttpClient;
import org.example.telegram.bot.QuizBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Configuration
public class QuizBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(QuizBot bot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }

    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient();
    }
}
