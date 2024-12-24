package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BookTest {
    Book book;

    @BeforeEach
    void init() {
        book = new Book(1L, "test", 100);
    }

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateBook_withValidParams() {
            assertEquals(1L, book.getId());
            assertEquals("test", book.getTitle());
            assertEquals(100, book.getDownloadCount());
            assertNotNull(book.getAuthorList());
            assertNotNull(book.getLanguages());
            assertTrue(book.getAuthorList().isEmpty());
            assertTrue(book.getLanguages().isEmpty());
        }
    }

    @Nested
    class SetterAndGetterTest {

        @Test
        void shouldSetId() {
            book.setId(4L);
            assertEquals(4L, book.getId());
        }

        @Test
        void shouldSetTitle() {
            book.setTitle("newTest");
            assertEquals("newTest", book.getTitle());
        }

        @Test
        void shouldSetDownloadCount() {
            book.setDownloadCount(200);
            assertEquals(200, book.getDownloadCount());
        }
    }

    @Nested
    class AuthorListTest {

        @Test
        void shouldAddAuthorToList() {
            Author author = new Author("test", 1990, 2010);
            book.addAuthor(author);
            assertEquals(1, book.getAuthorList().size());
        }
    }

    @Nested
    class LanguageListTest {

        @Test
        void shouldAddLanguageToList(){
            book.addLanguage("ESP");
            assertEquals(1, book.getLanguages().size());
        }
    }

}
