package org.example.server.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.server.repository.entity.Score;
import org.example.server.repository.repo.ScoreRepo;
import org.example.server.repository.entity.User;
import org.example.server.repository.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ScoreRepo scoreRepo;

	public User getById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
	}

	public void addUser(Long id, String username) {
		userRepo.save(new User(id, username));
	}

	public void addAnswer(Long userId, Long questionId){
		scoreRepo.save(new Score(null, questionId, userId));
	}
	public Integer getScore(Long id){
		return scoreRepo.getScoreById(id);
	}

	public Integer getScoreByTag(Long id, String tag){
		return scoreRepo.getScoreByIdAndTag(id, tag);
	}

	public void resetScore(Long id){
		scoreRepo.resetScoreById(id);
	}

}
