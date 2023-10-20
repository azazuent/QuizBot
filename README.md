# QuizBot
QuizBot using Java Spring JPA

Работа сделана без реализации клиента, запросы проверялись через Postman.

Выполнены пункты 1,2,3,5. В пункте 4 не выполнена автоматическая отправка сообщения так как не реализована клиентская часть.

# Функционал

## Пункт 1
Post /signUp - регистрация.

## Пункт 2
Post /signIn - авторизация, получение куки с id юзера.

## Пункт 3
Post /addQuestion - добавление вопроса и ответа к нему в бд,

Post /addTag - добавление тега в бд,

Post /bindTag - привязка тега к вопросу,

Post /answerQuestion - ответ на вопрос, в случае правильного ответа, добавление сведений об ответе в бд.

## Пункт 4
Get /getRandomQuestion - получение случайного вопроса,

Get /getRandomQuestion/{tag} - получение случайного вопроса по тегу.

## Пункт 5
Get /getScore - получение счета пользователя по его куки,

Get /getScore/{tag} - получение счета пользователя по его куки и тегу,

Post /deleteScore - обнуление счета пользователя по куки.

Полоумный код и рефакторинг, пока не удалось реализовать получение вопроса или счета сразу по нескольким тегам, но корректно работает привязка нескольких тегов к одному вопросу и все с этим связанное. Также, привязывать теги к вопросам нужно поочередно и отдельными запросами.
Есть над чем работать, было интересно.

![Alt text](image.png)
![Alt text](image-1.png)