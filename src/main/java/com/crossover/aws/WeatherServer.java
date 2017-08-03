package com.crossover.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main Spring application
 *
 * @author asaeles@gmail.com
 */
@SpringBootApplication
public class WeatherServer {

    public static void main(String[] args) {
        SpringApplication.run(WeatherServer.class, args);
    }
}
