package com.jeyofdev.shopping_krist;

import com.jeyofdev.shopping_krist.config.DatabaseConfig;
import com.jeyofdev.shopping_krist.util.DatabaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		/*ApplicationContext context = SpringApplication.run(Application.class, args);
		DatabaseConfig config = context.getBean(DatabaseConfig.class);

		// Check if database exist
		DatabaseInitializer.initializeDatabase(
				"jdbc:postgresql://localhost:5432/postgres",
				config.getDbUser(),
				config.getDbPassword(),
				config.getDbName()
		);

		System.out.println("hello world");*/

		SpringApplication.run(Application.class, args);
	}

}
