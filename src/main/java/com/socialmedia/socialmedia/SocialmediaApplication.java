package com.socialmedia.socialmedia;

import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import com.socialmedia.socialmedia.user.role.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

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
//	CommandLineRunner run(IUserService userService) {
//		return args -> {
//			UserRole nUser = userService.saveRole(new UserRole("ROLE_USER"));
//			UserRole rootUser = userService.saveRole(new UserRole("ROLE_ROOT"));
//			UserRole adminUser = userService.saveRole(new UserRole("ROLE_ADMIN"));
//			UserRole superAdimUser = userService.saveRole(new UserRole("ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User("Sajib Modak", "sajib", "1", rootUser));
//			userService.saveUser(new User("Rimon Modak", "rimon", "1", adminUser));
//			userService.saveUser(new User("Rajesh Modak", "rajesh", "1", superAdimUser));
//			userService.saveUser(new User("Anik Modak", "anik", "1", nUser));
//		};
//	}
}
