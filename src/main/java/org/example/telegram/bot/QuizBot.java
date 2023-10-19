package org.example.telegram.bot;

import org.example.server.abstraction.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
public class QuizBot extends TelegramLongPollingBot {

    @Autowired
    QuestionService questionService;

    Map askedQuestions = new HashMap<Long, String>();
    Map score = new HashMap<Long, Long>();

    private static final Logger LOG = LoggerFactory.getLogger((QuizBot.class));

    private static final String START = "/start";

    private static final String SIGN_IN = "/signIn";

    private static final String SIGN_UP = "/signUp";

    private static final String ADD_QUESTION = "/addQuestion";

    private static final String ADD_TAG = "/addTag";

    private static final String BIND_TAG = "/bindTag";

    private static final String ANSWER_QUESTION = "/answerQuestion";

    private static final String GET_RANDOM_QUESTION = "/getRandomQuestion";

    private static final String GET_SCORE = "/getScore";

    private static final String DELETE_SCORE = "/deleteScore";

    private static final String YO = "йооо";

    public QuizBot(@Value("${bot.token}") String botToken){
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update){
        if (!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        String[] messageParts = message.split(" ");
        switch (messageParts[0]){
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case YO -> {
                sendMessage(chatId, String.format("Йоу киса это секретная команда!!!!"));
            }
            case GET_RANDOM_QUESTION -> {
                randomQCommand(chatId);
            }
            case GET_SCORE -> {
                sendMessage(chatId,"Your score is ".concat(score.get(chatId).toString()));
            }
            case ANSWER_QUESTION -> {
                try {
                    boolean res = answerQuestion(chatId, messageParts[1]);
                    if (res == true){
                        sendMessage(chatId,"Your answer is correct");
                        askedQuestions.remove(chatId);
                    }
                    else {
                        sendMessage(chatId,"Your answer is wrong");
                    }
                }
                catch (Exception e){
                    sendMessage(chatId, "Ask a question first");
                }

            }
        }
    }

    private void randomQCommand(Long chatId) {
        String question = questionService.getRandomQuestion();
        askedQuestions.remove(chatId);
        askedQuestions.put(chatId, questionService.getAnswerByQuestion(question));
        sendMessage(chatId, question);
        System.out.println(askedQuestions);
    }

    private boolean answerQuestion(Long chatId, String answer) throws Exception {
        System.out.println(answer);
        if (!askedQuestions.containsKey(chatId)){
            throw new Exception();
        }
        if (askedQuestions.get(chatId).equals(answer)){
            score.put(chatId, ((Long)score.get(chatId)) + 1);
            return true;
        }
        return false;
    }

    @Override
    public String getBotUsername() {
        return "hsai_quiz_bot";
    }

    public void signIn(Long chatId, String[] message){

    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Welcome to Quiz Bot, %s!
                """;
        var formattedText = String.format(text, userName);
        score.put(chatId, 0L);
        sendMessage(chatId, formattedText);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Sending message error", e);
        }
    }


}
