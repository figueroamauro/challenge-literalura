package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Book;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookRepositoryTest {
    @Autowired
    BookRepository repository;
    static MySQLContainer<?> mysql;


    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
    }

    @BeforeAll
    static void setup() {
        mysql = new MySQLContainer<>("mysql:8.0.33")
                        .withDatabaseName("books");
        mysql.start();
    }

    @AfterAll
    static void clean() {
        mysql.close();
    }

    @Test
    void shouldSaveBook() {
        Book expectedBook = new Book(null, "test", 100);
        Book result = repository.save(expectedBook);
        assertEquals(expectedBook, result);
    }

}
