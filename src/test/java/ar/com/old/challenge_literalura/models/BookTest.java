package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateBook_withValidParams() {
            Book book = new Book(1L, "test", 100);

            assertEquals(1L, book.getId());
            assertEquals("test", book.getTitle());
            assertEquals(100,book.getDownloadCount());
            assertNotNull(book.getAuthorList());
            assertNotNull(book.getLanguages());
            assertTrue(book.getAuthorList().isEmpty());
            assertTrue(book.getLanguages().isEmpty());
        }
    }

}
