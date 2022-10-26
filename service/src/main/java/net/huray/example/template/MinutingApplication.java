package net.huray.example.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MinutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinutingApplication.class, args);
    }

}

