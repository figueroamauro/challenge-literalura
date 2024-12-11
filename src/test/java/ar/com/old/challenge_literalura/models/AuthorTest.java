package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class AuthorTest {

    @Nested
    class NameTest {

        @ParameterizedTest
        @NullAndEmptySource
        void whenConstructorIsCalled_withNullOrEmptyArgs_thenThrowIllegalArgumentException( String value) {
            assertThrows(IllegalArgumentException.class, ()->{
                Author author = new Author(value,1900, 1990);
            });
        }
        
        @ParameterizedTest
        @CsvSource({
                "'test  ', 'test'",
                "'  test', 'test'",
                "'  testing  ', 'testing'"
        })
        void whenConstructorIsCalled_withWhiteSpace_thenRemoveThese(String values, String expected){
            Author author = new Author(values,1900, 1990);

            assertEquals(expected, author.getName());
        }
        
        @ParameterizedTest
        @ValueSource(strings = {" 123123123123123123123123123123123",
        " test test test test test test test test test"})
        void whenConstructorIsCalled_withMoreThan30Characters_thenThrowIllegalArgumentException(String value){
            assertThrows(IllegalArgumentException.class, ()->{
                Author author = new Author(value,1900, 1990);
            });
        }
    }
}
