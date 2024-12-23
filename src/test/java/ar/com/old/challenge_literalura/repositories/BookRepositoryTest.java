package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.utils.TestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestContainerConfig.class)
public class BookRepositoryTest {
    @Autowired
    BookRepository repository;

    @Test
    void shouldSaveBook() {
        Book expectedBook = new Book(null, "test", 100);
        Book result = repository.save(expectedBook);
        assertEquals(expectedBook, result);
    }

}
