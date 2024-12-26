package ar.com.old.challenge_literalura.repositories;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.utils.TestContainerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void shouldSaveBook_withAuthorInList() {
        testBook.getAuthorList().add(new Author(null,"test", 1900, 2000));
        Book result = repository.save(testBook);
        assertEquals(testBook, result);
        System.out.println(result.getAuthorList());
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

    @Test
    void shouldGetAllBooks_withPageable() {
        Pageable pageable = PageRequest.of(0, 3);
        saveBooks(5);
        Page<Book> list = repository.findAll(pageable);
        assertEquals(3, list.getContent().size());
    }

    @Test
    void shouldGetBooksByName() {
        saveBooks(3);
        repository.save(new Book(null, "new title", 100));
        Optional<Book> result = repository.findByTitle("new title");
        assertNotNull(result);
        assertNotNull(result.get().getId());
        assertEquals("new title", result.get().getTitle());
    }

    @Test
    void shouldGetAllBooksByLanguage() {
        saveBooksWithLanguages(5);
        List<Book> result = repository.findAllByLanguages("lang1");
        assertEquals("lang1", result.get(0).getLanguages().get(0));
        assertEquals(1, result.size());
        assertNotNull(result);
    }

    @Test
    void shouldDeleteBook() {
        Book book = repository.save(testBook);
        long id = book.getId();
        repository.deleteById(id);
        Optional<Book> result = repository.findById(id);
       assertTrue(result.isEmpty());
    }

    //--------- UTILITY METHODS ----------
    private void saveBooks(int count) {
        for (int i = 0; i < count; i++) {
            repository.save(new Book(null, "test", 100));
        }
    }

    private void saveBooksWithLanguages(int count) {
        for (int i = 0; i < count; i++) {
            Book book =new Book(null, "test", 100);
            book.addLanguage("lang"+i);
            repository.save(book);
        }
    }
}
