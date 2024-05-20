package com.task_management_system;

import com.task_management_system.security.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

	@Bean
	AuditorAware<String> auditorAware() {

		return new SpringSecurityAuditorAware();
	}

}
