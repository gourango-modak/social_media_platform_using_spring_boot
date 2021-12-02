package com.socialmedia.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SocialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialmediaApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new UserRole("ROLE_USER"));
//			userService.saveRole(new UserRole("ROLE_ROOT"));
//			userService.saveRole(new UserRole("ROLE_ADMIN"));
//			userService.saveRole(new UserRole("ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User("Sajib Modak", "sajib", "1", new ArrayList<>()));
//			userService.saveUser(new User("Rimon Modak", "rimon", "1", new ArrayList<>()));
//			userService.saveUser(new User("Rajesh Modak", "rajesh", "1", new ArrayList<>()));
//			userService.saveUser(new User("Anik Modak", "anik", "1", new ArrayList<>()));
//
//			userService.addRoleToUser("sajib", "ROLE_USER");
//			userService.addRoleToUser("rimon", "ROLE_ADMIN");
//			userService.addRoleToUser("anik", "ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("anik", "ROLE_ROOT");
//		};
//	}
}
