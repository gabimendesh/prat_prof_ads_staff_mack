package br.com.escolaoctogono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class EscolaOctogonoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscolaOctogonoApplication.class, args);
    }
}
