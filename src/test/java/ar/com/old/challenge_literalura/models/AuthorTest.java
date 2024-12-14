package ar.com.old.challenge_literalura.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class AuthorTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateAuthor() {
            Author author = new Author("test", 1990, 2010);
            assertEquals("test", author.getName());
            assertEquals(1990, author.getBirthYear());
            assertEquals(2010, author.getDeathYear());
        }

        @Nested
        class NameTest {

            @ParameterizedTest
            @NullAndEmptySource
            void shouldThrowExceptionCreatingAuthorWithEmptyOrNullName(String value) {
                assertIllegalArgumentException(() -> new Author(value, 1900, 1990),
                        "El nombre no puede estar vacío ni ser nulo");
            }

            @ParameterizedTest
            @CsvSource({
                    "'test  ', 'test'",
                    "'  test', 'test'",
                    "'  testing  ', 'testing'"
            })
            void shouldTrimWhiteSpacesCreatingAuthorWithNameContainSpaces(String values, String expected) {
                Author author = new Author(values, 1900, 1990);

                assertEquals(expected, author.getName());
            }

            @ParameterizedTest
            @ValueSource(strings = {" 123123123123123123123123123123123",
                    " test test test test test test test test test"})
            void shouldThrowExceptionCreatingAuthorWithNameLongerThan30Characters(String value) {
                assertIllegalArgumentException(() -> new Author(value, 1900, 1990),
                        "El nombre no puede superar los 30 caracteres");
            }
        }

        @Nested
        class BirthYearTest {

            @ParameterizedTest
            @ValueSource(ints = {-1, -10, -1000})
            void shouldThrowExceptionCreatingAuthorWithNegativeBirthYear(int values) {
                assertIllegalArgumentException(() -> new Author("test", values, 1990),
                        "La fecha de nacimiento no puede ser menor a 0");
            }

            @ParameterizedTest
            @ValueSource(ints = {2050, 3000, 100000})
            void shouldThrowExceptionCreatingAuthorWithBirthYearLongerThanCurrentYear(int values) {
                assertIllegalArgumentException(() -> new Author("test", values, 1990),
                        "La fecha de nacimiento no puede ser mayor que el año actual");
            }
        }

        @Nested
        class DeathYearTest {

            @ParameterizedTest
            @ValueSource(ints = {-1, -10, -1000})
            void shouldThrowExceptionCreatingAuthorWithNegativeDeathYear(int values) {
                assertIllegalArgumentException(() -> new Author("test", 1900, values),
                        "La fecha de fallecimiento no puede ser menor a 0");
            }

            @ParameterizedTest
            @ValueSource(ints = {2050, 3000, 100000})
            void shouldThrowExceptionCreatingAuthorWithDeathYearLongerThanCurrentYear(int values) {
                assertIllegalArgumentException(() -> new Author("test", 1990, values),
                        "La fecha de fallecimiento no puede ser mayor que el año actual");
            }

            @Test
            void shouldThrowExceptionCreatingAuthorWithDeathYearIsLessThanBirthYear() {
                assertIllegalArgumentException(() -> new Author("test", 1990, 1980),
                        "La fecha de fallecimiento no puede ser menor que la fecha de nacimiento. Use 0 si el autor aún vive");
            }

        }

    }

    @Nested
    class SetterAndGetterTest {

        Author author;

        @BeforeEach
        void init() {
            author = new Author("test", 1900, 2000);
        }

        @Test
        void shouldSetName() {
            author.setName("newTest");

            assertEquals("newTest", author.getName());
        }

        @Test
        void shouldSetBirthYear() {
            author.setBirthYear(1995);

            assertEquals(1995, author.getBirthYear());
        }

        @Test
        void shouldSetDeathYear() {
            author.setDeathYear(2010);

            assertEquals(2010, author.getDeathYear());
        }
    }

    private static void assertIllegalArgumentException(Executable executable, String message) {
        assertEquals(message,
                assertThrows(IllegalArgumentException.class, executable).getMessage()
        )
        ;
    }
}
