package com.raonhaje.memorymap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemoryMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryMapApplication.class, args);
    }

}
