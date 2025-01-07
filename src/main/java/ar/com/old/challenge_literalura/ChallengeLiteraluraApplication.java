package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.view.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ChallengeLiteraluraApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);

        UserInterface userInterface = new UserInterface();
        userInterface.start();
    }
}
