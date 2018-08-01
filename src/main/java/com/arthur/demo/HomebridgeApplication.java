package com.arthur.demo;

import com.arthur.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class HomebridgeApplication implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    public static void main(String[] args) {

        SpringApplication.run(HomebridgeApplication.class, args);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
