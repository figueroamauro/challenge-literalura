package ar.com.old.challenge_literalura.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static ar.com.old.challenge_literalura.utils.TestUtils.*;

public class AuthorServiceTest {

    AuthorRepository repository;
    AuthorService service;
    Author authorToSave;
    Author authorSaved;
    List<Author> list = new ArrayList<>();

    @BeforeEach
    void init() {
        list = getCompleteList();
        repository = mock(AuthorRepository.class);
        service = new AuthorService(repository);
        authorToSave = new Author(null, "test", 1900, 2000);
        authorSaved = new Author(1L, "test", 1900, 2000);
        when(repository.save(authorToSave)).thenReturn(authorSaved);
    }


    @Test
    void shouldSaveAuthor() {
        Author result = service.saveAuthor(authorToSave);
        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
        assertEquals("test", result.getName());
        verify(repository).save(authorToSave);
    }

    @Test
    void shouldThrowException_withNullAuthor() {
        Executable executable = () -> service.saveAuthor(null);
        assertIllegalArgumentException(executable, "El autor no puede ser nulo");
    }

    @Test
    void shouldGetAMaximumOf10Authors() {
        when(repository.findAll(Pageable.ofSize(10)))
                .thenReturn(toPage(list, Pageable.ofSize(10)));
        List<Author> authorList = service.getAllAuthors();
        assertEquals(10, authorList.size());

    }


    private List<Author> getCompleteList() {
        List<Author> tmpList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            tmpList.add(new Author((long) i, "test", 1900, 2000));
        }
        return tmpList;
    }

    private Page<Author> toPage(List<Author> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<Author> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }

}
