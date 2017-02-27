package org.security_awareness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="org.security_awareness.model")
@EnableJpaRepositories(basePackages="org.security_awareness.repository")
public class SecurityAwarenessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityAwarenessApplication.class, args);
	}
}
