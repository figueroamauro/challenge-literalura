package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {
        @Autowired
        AuthorRepository repository;

    @Test
    void shouldSaveAuthorInDDBB() {
        Author expectedAuthor = new Author("test", 1900, 2000);

        Author author = repository.save(expectedAuthor);
        assertEquals(expectedAuthor, author);
    }
}
