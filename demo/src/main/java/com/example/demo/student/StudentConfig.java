package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student karla = new Student(
                    1L,
                    "Karla",
                    "karla@fer.hr",
                    LocalDate.of(2002, Month.JUNE,19)
            );

            Student leo = new Student(
                    "Leo",
                    "leo@fer.hr",
                    LocalDate.of(2000, Month.APRIL,19)
            );

            repository.saveAll(
                    List.of(karla,leo)
            );

        };
    }
}
