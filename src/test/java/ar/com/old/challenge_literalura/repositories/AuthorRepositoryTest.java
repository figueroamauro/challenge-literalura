package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;
    static MySQLContainer<?> mysql;

    @BeforeAll
    static void setup() {
        mysql = new MySQLContainer<>("mysql:8.0.33")
                        .withDatabaseName("authors");
        mysql.start();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
    }


    @Test
    @Transactional
    void shouldSaveAuthorInDDBB_save() {
        Author expectedAuthor = new Author("test", 1900, 2000);
        System.out.println(mysql.getJdbcUrl());
        Author author = repository.save(expectedAuthor);
        assertEquals(expectedAuthor, author);
    }

    @Test
    @Transactional
    void shouldReturnAEmptyList_findAll() {
        List<Author> list = repository.findAll();
        assertTrue(list.isEmpty());
    }
}
