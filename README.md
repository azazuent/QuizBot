# QuizBot
QuizBot using Java Spring JPA

Квиз-бот Telegram. Полностью реализован требуемый функционал. Ну, кроме таймера(.

# Функционал

## Пункт 1
/start - регистрация.

## Пункт 2
Авторизация производится по ID чата.

## Пункт 3
/add - добавление вопроса.

После первого использования /add, используются /addBody, /addAnswer, /addTag для формирования вопроса.

После того, как вопрос сформирован, команда /add добавляет его в систему.

Ответ обрабатывается если была отправлена строка без команды, всегда считается, что пользователь отвечает на последний запрошенный вопрос, на который еще не ответил.

## Пункт 4
Get /question <tag> - получение случайного вопроса, при указании тега - вопрос, относящийся к нему.

## Пункт 5
Get /score <tag> - получение счета пользователя, при указании тега - только по вопросам из указанной категории.

Post /reset - обнуление счета пользователя.

Было интересно.

Ниже скриншоты работы бота.

![image](https://github.com/azazuent/QuizBot/assets/101038113/d3676f26-a6a2-48fa-ab4c-2a6ab4829d46)

![image](https://github.com/azazuent/QuizBot/assets/101038113/5dcc0224-afa6-4d65-bc87-028e5f4d2adb)

![image](https://github.com/azazuent/QuizBot/assets/101038113/9e9586b1-d741-4de0-addd-3a1b2e8c5313)

![image](https://github.com/azazuent/QuizBot/assets/101038113/112a4843-30e4-4c3f-86cb-8cd8df6e0fb8)

![image](https://github.com/azazuent/QuizBot/assets/101038113/fb5d7070-7645-4a5f-960d-c6eeb6b76f26)




