package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import ar.com.old.challenge_literalura.view.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ChallengeLiteraluraApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ChallengeLiteraluraApplication.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        UserInterface userInterface = new UserInterface(bookRepository,authorRepository);

        userInterface.start();
    }
}
