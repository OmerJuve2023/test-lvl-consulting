package omer.solutions.testlvlconsulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "omer.solutions.testlvlconsulting.repository")
public class TestLvlConsultingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLvlConsultingApplication.class, args);
    }

}
