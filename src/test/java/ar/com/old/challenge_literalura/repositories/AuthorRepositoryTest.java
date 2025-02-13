package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.utils.TestContainerConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        expectedAuthor = new Author(null,"test", 1900, 2000);
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
    void shouldGetAllAuthors() {
        saveAuthors(5);
        List<Author> list = repository.findAll();
        assertEquals(5, list.size());
    }


    @Test
    void shouldGetAllAuthors_withPageable() {
        saveAuthors(5);
        Page<Author> pageList = repository.findAll(getPageable(3));
        assertEquals(3, pageList.getContent().size());
    }


    @Test
    void shouldDeleteAuthorById() {
        Author author = repository.save(expectedAuthor);
        Long id = author.getId();
        repository.deleteById(id);
        Optional<Author> authorOpt = repository.findById(id);
        assertFalse(authorOpt.isPresent());
    }

    @Test
    void shouldDeleteAuthor() {
        Author author = repository.save(expectedAuthor);
        long id = author.getId();
        repository.deleteById(id);
        Optional<Author> result = repository.findById(id);
        assertTrue(result.isEmpty());
    }


    //--------- UTILITY METHODS ----------
    private  void saveAuthors(int count) {
        for (int i = 0; i < count; i++) {
            repository.save(new Author(1L,"test", 1990, 2000));
        }
    }
    private  Pageable getPageable(int size) {
        return PageRequest.of(0, size);
    }


}


