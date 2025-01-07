package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.repositories.BookRepository;
import ar.com.old.challenge_literalura.view.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ChallengeLiteraluraApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ChallengeLiteraluraApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);
        UserInterface userInterface = new UserInterface(repository);

        userInterface.start();
    }
}
