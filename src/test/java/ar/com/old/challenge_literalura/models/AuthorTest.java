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
    class ConstructorTest {

        @Nested
        class NameTest {

            @ParameterizedTest
            @NullAndEmptySource
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithEmptyOrNullName(String value) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author(value, 1900, 1990);
                });
                assertEquals("El nombre no puede estar vacío ni ser nulo", exception.getMessage());
            }

            @ParameterizedTest
            @CsvSource({
                    "'test  ', 'test'",
                    "'  test', 'test'",
                    "'  testing  ', 'testing'"
            })
            void shouldTrimWhiteSpacesWhenConstructorIsCalledWithNameContainSpaces(String values, String expected) {
                Author author = new Author(values, 1900, 1990);

                assertEquals(expected, author.getName());
            }

            @ParameterizedTest
            @ValueSource(strings = {" 123123123123123123123123123123123",
                    " test test test test test test test test test"})
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNameLongerThan30Characters(String value) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author(value, 1900, 1990);
                });

                assertEquals("El nombre no puede superar los 30 caracteres", exception.getMessage());
            }
        }

        @Nested
        class BirthYearTest {

            @ParameterizedTest
            @ValueSource(ints = {-1, -10, -1000})
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNegativeBirthYear(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", values, 1990);
                });

                assertEquals("La fecha de nacimiento no puede ser menor a 0", exception.getMessage());
            }

            @ParameterizedTest
            @ValueSource(ints = {2050, 3000, 100000})
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithBirthYearLongerThanCurrentYear(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", values, 1990);
                });

                assertEquals("La fecha de nacimiento no puede ser mayor que el año actual", exception.getMessage());
            }
        }

        @Nested
        class DeathYearTest {

            @ParameterizedTest
            @ValueSource(ints = {-1, -10, -1000})
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNegativeDeathYear(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", 1900, values);
                });

                assertEquals("La fecha de fallecimiento no puede ser menor a 0", exception.getMessage());
            }

            @ParameterizedTest
            @ValueSource(ints = {2050, 3000, 100000})
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithDeathYearLongerThanCurrentYear(int values) {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", 1990, values);
                });

                assertEquals("La fecha de fallecimiento no puede ser mayor que el año actual", exception.getMessage());
            }

            @Test
            void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithDeathYearIsLessThanBirthYear() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    Author author = new Author("test", 1990, 1980);
                });

                assertEquals("La fecha de fallecimiento no puede ser menor que la fecha de nacimiento. Use 0 si el autor aún vive", exception.getMessage());
            }

        }

    }
}
