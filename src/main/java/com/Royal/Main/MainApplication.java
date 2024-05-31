package com.Royal.Main;

import com.Royal.Main.persistence.entity.Role;
import com.Royal.Main.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@AllArgsConstructor
//@EnableRetry
public class MainApplication {

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

//	@Bean
//	protected CommandLineRunner commandLineRunner(){
//		Role role = Role.builder()
//				.givenRole("ROLE_USER")
//				.
//				.build();
//		roleRepository.save()
//	}



}
