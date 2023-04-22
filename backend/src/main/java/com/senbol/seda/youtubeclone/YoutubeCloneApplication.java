package com.senbol.seda.youtubeclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.senbol.seda" })
public class YoutubeCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutubeCloneApplication.class, args);
    }

}
