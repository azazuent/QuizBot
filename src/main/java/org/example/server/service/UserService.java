package org.example.server.abstraction.service;

import org.example.server.repository.User;

public interface UserService {
	UserDto getById(Long id);

	Long addUser(AddUserDto addUserDto);

	UserDto signIn(SignInDto dto);

	record SignInDto(
			Long id,
			String password
	){}

	record AddUserDto(
			String name,
			String password
	){
		public static User toDbEntity(AddUserDto addUserDto){
			return new User(
					null,
					addUserDto.name(),
					addUserDto.password()
			);
		}
	}


	record UserDto(
		Long id,
		String name,
		String password

	){
		public static UserDto fromDbEntity(User user){
			return new UserDto(
				user.getId(),
				user.getName(),
				user.getPassword()
			);
		}
	}
}
