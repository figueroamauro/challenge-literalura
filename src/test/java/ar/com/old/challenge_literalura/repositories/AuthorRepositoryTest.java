package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.utils.TestContainerConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestContainerConfig.class)
@Transactional
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;
    Author expectedAuthor;

    @BeforeEach
    void init() {
        expectedAuthor = new Author("test", 1900, 2000);
    }


    @Test
    void shouldSaveAuthor() {
        Author author = repository.save(expectedAuthor);
        assertEquals(expectedAuthor, author);
    }

    @Test
    void shouldGetAuthorById() {
        Author author = repository.save(expectedAuthor);
        Optional<Author> authorOpt = repository.findById(author.getId());
        assertEquals(author.getId(), authorOpt.get().getId());
    }

    @Test
    void shouldReturnAEmptyList() {
        List<Author> list = repository.findAll();
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldDeleteAuthorById() {
        Author author = repository.save(expectedAuthor);
        Long id = author.getId();

        repository.deleteById(id);

        Optional<Author> authorOpt = repository.findById(id);
        assertFalse(authorOpt.isPresent());
    }
}
