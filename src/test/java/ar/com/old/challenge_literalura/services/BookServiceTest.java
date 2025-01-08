package ar.com.old.challenge_literalura.services;

import static ar.com.old.challenge_literalura.utils.TestUtils.*;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {
    @Autowired
    BookRepository repository;
    Book bookSaved;
    Book bookToSave;
    BookService service;
    List<Book> list = new ArrayList<>();

    @BeforeEach
    void init() {
        bookToSave = new Book(null, "test", 100);
        bookSaved = new Book(1L, "test", 100);
        repository = mock(BookRepository.class);
        service = new BookService(repository, new GutendexServiceAPI());
        list = getCompleteList();
    }

    @Test
    void shouldSaveBook() {
        when(repository.save(bookToSave)).thenReturn(bookSaved);
        Book result = service.saveBook(bookToSave);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test", result.getTitle());
        verify(repository).save(bookToSave);
    }

    @Test
    void shouldFailSavingBookAndThrowException_withNullBook() {
        Executable executable = () -> service.saveBook(null);
        assertIllegalArgumentException(executable, "El libro no puede ser nulo");
        verify(repository, never()).save(null);
    }

    @Test
    void shouldFailSavingBookAndThrowException_whenBookAlreadyExists() {
        when(repository.findByTitle("test")).thenReturn(Optional.of(new Book(3L, "test", 200)));
        Executable executable = () -> service.saveBook(bookToSave);
        assertIllegalArgumentException(executable, "El libro ya se encuentra registrado");
        verify(repository, never()).save(bookToSave);
        verify(repository).findByTitle("test");
    }

    @Test
    void shouldGetBookById() {
        when(repository.findById(1L)).thenReturn(Optional.of(bookSaved));
        Book result = service.getBookById(1L);
        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    void shouldFailGettingBookAndThrowException_withInvalidId() {
        long id = 10L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        Executable executable = () -> service.getBookById(id);
        assertIllegalArgumentException(executable, "El libro con id " + id + " no se encuentra registrado");
        verify(repository).findById(10L);
    }

    @Test
    void shouldGetAMaximumOf10Books() {
        when(repository.findAll(Pageable.ofSize(10)))
                .thenReturn(toPage(list, Pageable.ofSize(10)));
        List<Book> authorList = service.getAllBooks();
        assertEquals(10, authorList.size());
        verify(repository).findAll(Pageable.ofSize(10));
    }


    //--------- UTILITY METHODS ----------
    private Page<Book> toPage(List<Book> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<Book> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }

    private List<Book> getCompleteList() {
        List<Book> tmpList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            tmpList.add(new Book((long) i, "test",100));
        }
        return tmpList;
    }

}
