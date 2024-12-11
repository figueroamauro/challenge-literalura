package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class AuthorTest {

    @Nested
    class ConstructorTest {

        @Nested
        class NameTest {

            @ParameterizedTest
            @NullAndEmptySource
            void whenConstructorIsCalled_withNullOrEmptyArgs_thenThrowIllegalArgumentException(String value) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author(value, 1900, 1990);
                });
                assertEquals("El atributo name no puede estar vacio ni ser nulo",exception.getMessage());
            }

            @ParameterizedTest
            @CsvSource({
                    "'test  ', 'test'",
                    "'  test', 'test'",
                    "'  testing  ', 'testing'"
            })
            void whenConstructorIsCalled_withWhiteSpaceArgs_thenRemoveThese(String values, String expected) {
                Author author = new Author(values, 1900, 1990);

                assertEquals(expected, author.getName());
            }

            @ParameterizedTest
            @ValueSource(strings = {" 123123123123123123123123123123123",
                    " test test test test test test test test test"})
            void whenConstructorIsCalled_withMoreThan30CharactersArgs_thenThrowIllegalArgumentException(String value) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author(value, 1900, 1990);
                });
                assertEquals("El atributo name no puede superar los 30 caracteres", exception.getMessage());
            }
        }

        @Nested
        class BirthYearTest {

            @ParameterizedTest
            @ValueSource(ints = {-1, -10, -1000})
            void whenConstructorIsCalled_withNegativeArgs_thenThrowIllegalArgumentException(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", values, 1990);
                });

                assertEquals("la fecha de nacimiento no puede ser menor a 0",exception.getMessage());
            }

            @ParameterizedTest
            @ValueSource(ints = {2050, 3000, 100000})
            void whenConstructorIsCalled_withBirthYearMoreThanCurrentYear_thenThrowIllegalArgumentException(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", values, 1990);
                });

                assertEquals("la fecha de nacimiento no puede ser mayor que el año actual",exception.getMessage());
            }
        }
    }
}
