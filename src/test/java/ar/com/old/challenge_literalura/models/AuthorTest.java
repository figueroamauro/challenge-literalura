package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class AuthorTest {

    @Nested
    class ConstructorTest {

        @ParameterizedTest
        @NullAndEmptySource
        void whenConstructorIsCalled_withNullOrEmptyArgs_thenThrowIllegalArgumentException( String value) {
            assertThrows(IllegalArgumentException.class, ()->{
                Author author = new Author(value,1900, 1990);
            });
        }
    }
}
