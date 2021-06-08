package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            student s1= new student("sid",
                    "sid@gmail.com",
                    LocalDate.of(2000,2,4));
            student s2= new student("Vyavahare",
                    "vyavahare@gmail.com",
                    LocalDate.of(2004,4,4));
            repository.saveAll(List.of(s1,s2));
        };
    }
}
