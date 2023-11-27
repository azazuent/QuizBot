package org.example.telegram.bot;

import org.example.server.repository.entity.Question;
import org.example.server.repository.entity.Tag;
import org.example.server.service.QuestionService;
import org.example.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;


@Component
public class QuizBot extends TelegramLongPollingBot {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    HashMap questions = new HashMap<Long, Question>();

    HashMap newQuestions = new HashMap<Long, NewQuestion>();

    private class NewQuestion{
        NewQuestion(){
            question = new Question(null, null, null);
            tags = new ArrayList<String>();
        };

        public Question question;

        public ArrayList<String> tags;
    }

    private static final Logger LOG = LoggerFactory.getLogger((QuizBot.class));

    private static final String START = "/start";

    private static final String ADD_QUESTION = "/add";

    private static final String ADD_BODY = "/addBody";

    private static final String ADD_ANSWER = "/addAnswer";

    private static final String ADD_TAG = "/addTag";

    private static final String GET_RANDOM_QUESTION = "/question";

    private static final String GET_SCORE = "/score";

    private static final String RESET_SCORE = "/reset";

    private static final String YO = "йооо";

    public QuizBot(@Value("${bot.token}") String botToken){
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return "hsai_quiz_bot";
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

    @Override
    public void onUpdateReceived(Update update){
        if (!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();

        if (message.equals(YO)) {
            sendMessage(chatId, "ЙООО это же секретная команда!!!");
            return;
        }

        String command = new String();
        String adata = new String();

        if (message.startsWith("/")){
            String[] messageParts = message.split(" ", 2);
            command = messageParts[0];
            if (messageParts.length > 1) {
                adata = messageParts[1];
            }
        }

        if (command.isEmpty()){
            answerQuestion(chatId, message);
            return;
        }

        switch (command) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case ADD_QUESTION -> {
                try {
                    addQuestion(chatId);
                } catch (DataIntegrityViolationException e) {
                    sendMessage(chatId, "This question already exists!");
                }

            }
            case ADD_BODY -> {
                if (adata.isEmpty()){
                    sendMessage(chatId, "Question`s body can`t be blank!");
                    return;
                }
                addBody(chatId, adata);
            }
            case ADD_ANSWER -> {
                if (adata.isEmpty()){
                    sendMessage(chatId, "Question`s answer can`t be blank!");
                    return;
                }
                addAnswer(chatId, adata);
            }
            case ADD_TAG -> {
                if (adata.isEmpty()){
                    sendMessage(chatId, "Tag can`t be blank!");
                    return;
                }
                bindTag(chatId, adata);
            }
            case GET_RANDOM_QUESTION -> {
                if (adata.isEmpty()){
                    getRandomQuestion(chatId);
                    return;
                }
                getRandomQuestionByTag(chatId, adata);
            }
            case GET_SCORE -> {
                if (adata.isEmpty()){
                    getScore(chatId);
                    return;
                }
                getScoreByTag(chatId, adata);
            }
            case RESET_SCORE -> {
                resetScore(chatId);
            }
        }
    }

    private void startCommand(Long chatId, String username) {
        var text = """
                Welcome to Quiz Bot, %s!
                Available commands:
                /question <tag>
                /score <tag>
                /reset
                /add
                """;
        userService.addUser(chatId, username);
        var formattedText = String.format(text, username);
        sendMessage(chatId, formattedText);
    }

    private void getRandomQuestion(Long chatId){
        Question question = questionService.getRandomQuestion();
        if (question == null){
            sendMessage(chatId, "Couldn`t find a question for you. Sowwy.");
            return;
        }
        questions.put(chatId, question);
        sendMessage(chatId, question.getQuestion());
    }

    private void getRandomQuestionByTag(Long chatId, String tag){
        Question question = questionService.getRandomQuestionByTag(tag);
        if (question == null){
            sendMessage(chatId, "Couldn`t find a question tagged with " + tag + " for you. Sowwy.");
            return;
        }
        questions.put(chatId, question);
        sendMessage(chatId, question.getQuestion());
    }

    private void answerQuestion(Long chatId, String answer){
        if (!questions.containsKey(chatId)){
            sendMessage(chatId,"Ask a question with /question command!");
            return;
        }
        Question question = (Question) questions.get(chatId);
        if (!question.getAnswer().equals(answer)){
            sendMessage(chatId,"Your answer is incorrect.");
        }
        else{
            userService.addAnswer(chatId, question.getId());
            questions.remove(chatId);
            sendMessage(chatId,"Your answer is correct!");
        }
    }

    private void getScore(Long chatId){
        Integer score = userService.getScore(chatId);
        sendMessage(chatId, "Your score is " + score.toString()+ ".");
    }

    private void getScoreByTag(Long chatId, String tag){
        Integer score = userService.getScoreByTag(chatId, tag);
        sendMessage(chatId, "Your score in " + tag + " questions is " + score.toString() + ".");
    }

    private void resetScore(Long chatId){
        userService.resetScore(chatId);
        sendMessage(chatId,"Your score has been reset.");
    }

    private void addQuestion(Long chatId) throws DataIntegrityViolationException {
        if (!newQuestions.containsKey(chatId)){
            newQuestions.put(chatId, new NewQuestion());
            sendMessage(chatId,
                    "You have created a new blank question!\n"+
                    "Use /addBody, /addAnswer, /addTag commands to fill it up!\n" +
                    "When you`re done, use /add command again to add the question!");
            return;
        }
        NewQuestion question = (NewQuestion)newQuestions.get(chatId);
        String message = new String();
        if (question.question.getQuestion() == null){
            message += "You have to define the question`s body with the /addBody command!\n";
        }
        if (question.question.getAnswer() == null){
            message += "You have to define the question`s answer with the /addAnswer command!\n";
        }
        if (!message.isEmpty()){
            sendMessage(chatId, message);
            return;
        }
        questionService.addQuestion(question.question);

        Iterator<String> it = question.tags.iterator();
        while (it.hasNext()) {
            String tag = it.next();
            questionService.bindTag(tag, question.question.getQuestion());
        }


        sendMessage(chatId,"Your question has been successfully added!\n"+
                "Here it is:\n"+
                "   Question: " + question.question.getQuestion() + "\n" +
                "   Answer: " + question.question.getAnswer() + "\n" +
                "   Tags: " + String.join(", ", question.tags));
        newQuestions.remove(chatId);
    }

    private void addBody(Long chatId, String body){
        if (!newQuestions.containsKey(chatId)) {
            sendMessage(chatId, "To add a body, you must first initialize a blank question!\n" +
                    "Do so using the /add command");
            return;
        }
        NewQuestion question = (NewQuestion) newQuestions.get(chatId);
        question.question.setQuestion(body);
        sendMessage(chatId, "You have added a body to your question!\n" +
                "Here it is:\n"+
                "   Question: " + question.question.getQuestion() + "\n" +
                "   Answer: " + question.question.getAnswer() + "\n" +
                "   Tags: " + String.join(", ", question.tags));
        newQuestions.put(chatId, question);
    }

    private void addAnswer(Long chatId, String answer){
        if (!newQuestions.containsKey(chatId)) {
            sendMessage(chatId, "To add an answer, you must first initialize a blank question!\n" +
                    "Do so using the /add command");
            return;
        }
        NewQuestion question = (NewQuestion) newQuestions.get(chatId);
        question.question.setAnswer(answer);
        sendMessage(chatId, "You have added an answer to your question!\n" +
                "Here it is:\n"+
                "   Question: " + question.question.getQuestion() + "\n" +
                "   Answer: " + question.question.getAnswer() + "\n" +
                "   Tags: " + String.join(", ", question.tags));
        newQuestions.put(chatId, question);
    }

    private void bindTag(Long chatId, String tag){
        if (!newQuestions.containsKey(chatId)) {
            sendMessage(chatId, "To add tags, you must first initialize a blank question!\n" +
                    "Do so using the /add command");
            return;
        }
        NewQuestion question = (NewQuestion) newQuestions.get(chatId);
        question.tags.add(tag);
        sendMessage(chatId, "You have added a tag to your question!\n" +
                "Here it is:\n"+
                "   Question: " + question.question.getQuestion() + "\n" +
                "   Answer: " + question.question.getAnswer() + "\n" +
                "   Tags: " + String.join(", ", question.tags));
        newQuestions.put(chatId, question);
    }

}
