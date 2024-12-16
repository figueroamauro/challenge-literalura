package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class AuthorTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateAuthor() {
            Author author = new Author("test", 1990, 2010);
            assertEquals("test", author.getName());
            assertEquals(1990, author.getBirthYear());
            assertEquals(2010, author.getDeathYear());
        }
    }

    @Nested
    class SetterAndGetterTest {
        Author author;

        @BeforeEach
        void init() {
            author = new Author("test", 1900, 2000);
        }

        @Test
        void shouldSetName() {
            author.setName("newTest");

            assertEquals("newTest", author.getName());
        }

        @Test
        void shouldSetBirthYear() {
            author.setBirthYear(1995);

            assertEquals(1995, author.getBirthYear());
        }

        @Test
        void shouldSetDeathYear() {
            author.setDeathYear(2010);

            assertEquals(2010, author.getDeathYear());
        }
    }


}
