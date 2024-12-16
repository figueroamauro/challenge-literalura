package ar.com.old.challenge_literalura.models.validators;

import ar.com.old.challenge_literalura.models.Author;

import static org.junit.jupiter.api.Assertions.*;
import static ar.com.old.challenge_literalura.models.TestUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class BookValidatorTest {


//    private String title;
//    private final List<Author> authorList;
//    private final List<String> languages;
//    private int downloadCount;


    @Nested
    class idTest {

        @ParameterizedTest
        @NullSource
        void shouldThrowException_whenIdIsNull(Long id) {
            assertIllegalArgumentException(getValidateIdExecutable(id), "El id no puede ser nulo");
        }

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

    private static Executable getValidateIdExecutable(Long id) {
        return () -> BookValidator.validateId(id);
    }

    private static Executable getValidateTitleExecutable(String title) {
        return () -> BookValidator.validateTitle(title);
    }
}
