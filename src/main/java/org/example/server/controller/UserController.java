package org.example.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.abstraction.service.QuestionService;
import org.example.server.abstraction.service.TagService;
import org.example.server.abstraction.service.UserService;
import org.example.server.repository.*;
import org.springframework.web.bind.annotation.*;

@RestController
public record UserController(
	UserService userService,
	QuestionService questionService,
	TagService tagService,

	ScoreRepo scoreRepo,

	QuestionTagRepo questionTagRepo
) {

	@GetMapping("/getUser/{id}")
	public UserService.UserDto findById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@GetMapping("/getQuestion/{id}")
	public String getQuestionById(@PathVariable Long id) {
		return questionService.getById(id).question();
	}

	@GetMapping("/getRandomQuestion")
	public String getRandomQuestion() {

		return questionService.getRandomQuestion();
	}

	@GetMapping("/getRandomQuestion/{tag}")
	public String getRandomQuestionByTag(@PathVariable String tag) {
		return questionService.getRandomQuestionByTag(tag);
	}

	@GetMapping("/getScore")
	public Integer getScore(@CookieValue(name = "userId") Long userId) {
		return scoreRepo.getScoreById(userId);
	}

	@GetMapping("/getScore/{tag}")
	public Integer getScoreByTag(@CookieValue(name = "userId") Long userId,
								 @PathVariable String tag) {
		return scoreRepo.getScoreByIdAndTag(userId, tag);
	}

	@PostMapping("/deleteScore")
	public Integer deleteScore(@CookieValue(name = "userId") Long userId) {
		return scoreRepo.deleteScoreById(userId);
	}

	@PostMapping("/signUp")
	public Long signUp(@RequestBody UserService.AddUserDto addUserDto) {return userService.addUser(addUserDto);}

	@PostMapping("/signIn")
	public Long signIn(@RequestBody UserService.SignInDto dto, HttpServletResponse response) {
		UserService.UserDto user = userService.signIn(dto);

		if (user != null) {
			// Создайте Cookie с ID пользователя
			Cookie userCookie = new Cookie("userId", String.valueOf(user.id()));

			// Установите дополнительные параметры куки
			userCookie.setMaxAge(3600); // Срок действия куки в секундах (здесь 1 час)
			userCookie.setPath("/");   // Путь, на котором кука доступна

			// Добавьте куку в HTTP-ответ
			response.addCookie(userCookie);
		}

		return user.id();
	}

	@PostMapping("/addQuestion")
	public Long addQuestion(@RequestBody QuestionService.AddQuestionDto addQuestionDto){
		return questionService.addQuestion(addQuestionDto);
	}

	@PostMapping("/bindTag")
	public Long bindTag(@RequestBody TagService.BindTagDto bindTagDto){
		QuestionTag questionTag = new QuestionTag(null, bindTagDto.questionId(), bindTagDto.tagId());
		return questionTagRepo.save(questionTag).getId();
	}

	@PostMapping("/addTag")
	public Long addTag(@RequestBody TagService.AddTagDto addTagDto){
		return tagService.addTag(addTagDto);
	}

	@PostMapping("/answerQuestion")
	public Long answerQuestion(@RequestBody QuestionService.AnswerQuestionDto answerQuestionDto,
							   @CookieValue(name = "userId") Long userId) {
		if (questionService.getById(answerQuestionDto.questionId()).answer()
				.equals(answerQuestionDto.answer())) {
			Score score = new Score(null, answerQuestionDto.questionId(), userId);
			return scoreRepo.save(score).getId();
		}
		return (long) (-1);
	}
}
