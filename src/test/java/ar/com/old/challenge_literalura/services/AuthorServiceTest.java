package ar.com.old.challenge_literalura.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    AuthorRepository repository;
    AuthorService service;
    Author authorToSave;
    Author authorSaved;

    @BeforeEach
    void init() {
        repository = mock(AuthorRepository.class);
        service = new AuthorService(repository);
        authorToSave = new Author(null,"test", 1900, 2000);
        authorSaved = new Author(1L, "test", 1900, 2000);
    }

    @Test
    void shouldSaveAuthor() {
        when(repository.save(authorToSave)).thenReturn(authorSaved);

        Author result = service.saveAuthor(authorToSave);
        assertNotNull(result.getId());
        assertEquals(1L,result.getId());
        assertEquals("test", result.getName());
        verify(repository).save(authorToSave);
    }
}
