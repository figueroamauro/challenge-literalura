package ar.com.old.challenge_literalura.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Test
    void shouldSaveAuthor() {
        AuthorRepository repository = mock(AuthorRepository.class);
        Author authorToSave = new Author(null,"test", 1900, 2000);
        Author authorSaved = new Author(1L, "test", 1900, 2000);
        when(repository.save(authorToSave)).thenReturn(authorSaved);
        AuthorService service = new AuthorService(repository);

        Author result = service.saveAuthor(authorToSave);
        assertNotNull(result.getId());
        assertEquals(1L,result.getId());
        assertEquals("test", result.getName());
    }
}
