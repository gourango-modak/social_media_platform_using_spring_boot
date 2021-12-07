package com.socialmedia.socialmedia;

import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import com.socialmedia.socialmedia.user.role.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
//
//			userService.saveUser(new User("Sajib Modak", "sajib", "1","modaksajib708@gmail.com", rootUser));
//			userService.saveUser(new User("Rimon Modak", "rimon", "1", "gourango.modak.2@gmail.com", nUser));
//		};
//	}
}
