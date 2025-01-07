package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ChallengeLiteraluraApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);

        GutendexServiceAPI service = new GutendexServiceAPI();
        System.out.println(service.getByTitle("quijote"));
    }
}
