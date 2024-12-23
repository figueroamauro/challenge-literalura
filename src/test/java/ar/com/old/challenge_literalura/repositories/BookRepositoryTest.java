package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.utils.TestContainerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestContainerConfig.class)
@Transactional
public class BookRepositoryTest {
    @Autowired
    BookRepository repository;
    Book testBook;

    @BeforeEach
    void init() {
        testBook = new Book(null, "test", 100);
    }

    @Test
    void shouldSaveBook() {
        Book result = repository.save(testBook);

        assertEquals(testBook, result);
    }

    @Test
    void shouldGetBookById() {
        Book book = repository.save(testBook);

        Optional<Book> result = repository.findById(book.getId());

        assertTrue(result.isPresent());
        assertEquals(book.getId(), result.get().getId());
    }

    @Test
    void shouldGetAllBooks() {
        saveBooks(5);
        assertEquals(5, repository.findAll().size());
    }

    private void saveBooks(int count) {
        for (int i = 0; i < count; i++) {
            repository.save(new Book(null, "test", 100));
        }
    }
}
