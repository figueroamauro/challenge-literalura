package ar.com.old.challenge_literalura.models.validators;

import ar.com.old.challenge_literalura.models.Author;

import static org.junit.jupiter.api.Assertions.*;
import static ar.com.old.challenge_literalura.utils.TestUtils.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


public class BookValidatorTest {

    @Nested
    class idTest {

        @ParameterizedTest
        @ValueSource(longs = {-1L, -100L, -1000L})
        void shouldThrowException_whenIdIsNegative(Long id) {
            assertIllegalArgumentException(getValidateIdExecutable(id), "El id no puede ser negativo");
        }
    }

    @Nested
    class TitleTest {

        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowException_whenTitleIsNullOrEmpty(String title) {
            assertIllegalArgumentException(getValidateTitleExecutable(title), "El título no puede estar vacío ni ser nulo");
        }

        @ParameterizedTest
        @ValueSource(strings = {" 123123123123123123123123123123123123123123123123123123",
                " test test test test test test test test test test test test test test test test test"})
        void shouldThrowException_whenTitleIsLongerThan50Characters(String title) {
            assertIllegalArgumentException(getValidateTitleExecutable(title), "El título no puede superar los 50 caracteres");
        }

        @ParameterizedTest
        @CsvSource({
                "'test  ', 'test'",
                "'  test', 'test'",
                "'  testing  ', 'testing'"
        })
        void shouldTrimWhiteSpaces_whenNameContainSpaces(String values, String expected) {
            assertEquals(expected, BookValidator.validateTitle(values));
        }
    }

    @Nested
    class AuthorListTest {
        @ParameterizedTest
        @NullSource
        void shouldThrowException_whenAuthorIsNull(Author author) {
            assertIllegalArgumentException(getValidateAuthorExecutable(author),"El autor no puede ser nulo");
        }
    }

    @Nested
    class LanguagesListTest {
        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowException_whenLanguageIsNullOrEmpty(String title) {
            assertIllegalArgumentException(getValidateLanguageExecutable(title), "El lenguaje no puede estar vacío ni ser nulo");
        }

        @ParameterizedTest
        @ValueSource(strings = {" 3123123123123123123123123",
                " test test test test test test test"})
        void shouldThrowException_whenLanguageIsLongerThan20Characters(String title) {
            assertIllegalArgumentException(getValidateLanguageExecutable(title), "El lenguaje no puede superar los 20 caracteres");
        }

        @ParameterizedTest
        @CsvSource({
                "'test  ', 'test'",
                "'  test', 'test'",
                "'  testing  ', 'testing'"
        })
        void shouldTrimWhiteSpaces_whenLanguageContainSpaces(String values, String expected) {
            assertEquals(expected, BookValidator.validateLanguage(values));
        }

        @Nested
        class DownloadCountTest {

            @ParameterizedTest
            @NullSource
            void shouldThrowException_whenDownloadCountIsNull(Integer value) {
                assertIllegalArgumentException(getValidateDownloadCountExecutable(value),"La cantidad de descargas no puede ser nula");
            }

            @ParameterizedTest
            @ValueSource(ints = {-1, -100, -1000})
            void shouldThrowException_whenDownloadCountIsNegative(Integer value) {
                assertIllegalArgumentException(getValidateDownloadCountExecutable(value), "La cantidad de descargas no puede ser menor a 0");
            }
        }
    }

    //--------- UTILITY METHODS ----------
    private static Executable getValidateIdExecutable(Long id) {
        return () -> BookValidator.validateId(id);
    }

    private static Executable getValidateTitleExecutable(String title) {
        return () -> BookValidator.validateTitle(title);
    }

    private static Executable getValidateAuthorExecutable(Author author) {
        return () -> BookValidator.validateAuthor(author);
    }

    private static Executable getValidateLanguageExecutable(String language) {
        return () -> BookValidator.validateLanguage(language);
    }

    private static Executable getValidateDownloadCountExecutable(Integer downloadCount) {
        return () -> BookValidator.validateDownloadCount(downloadCount);
    }
}
