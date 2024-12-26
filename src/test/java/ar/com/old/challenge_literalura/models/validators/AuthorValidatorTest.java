package ar.com.old.challenge_literalura.models.validators;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static ar.com.old.challenge_literalura.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthorValidatorTest {

    @Nested
    class idTest {

        @ParameterizedTest
        @ValueSource(longs = {-1L, -100L, -1000L})
        void shouldThrowException_whenIdIsNegative(Long id) {
            assertIllegalArgumentException(getValidateIdExecutable(id), "El id no puede ser negativo");
        }
    }

    @Nested
    class NameTest {

        @ParameterizedTest
        @ValueSource(strings = {"test", "VALUETEST", "AndMore"})
        void shouldReturnSameName_whenParamsAreCorrect(String values) {
            String result = AuthorValidator.validateName(values);
            assertEquals(values,result);
        }

        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowException_whenNameIsEmptyOrNull(String name) {
            assertIllegalArgumentException(getValidateName(name), "El nombre no puede estar vacío ni ser nulo");
        }

        @ParameterizedTest
        @ValueSource(strings = {" 123123123123123123123123123123123",
                " test test test test test test test test test"})
        void shouldThrowException_whenNameIsLongerThan30Characters(String name) {
            assertIllegalArgumentException(getValidateName(name), "El nombre no puede superar los 30 caracteres");
        }

        @ParameterizedTest
        @CsvSource({
                "'test  ', 'test'",
                "'  test', 'test'",
                "'  testing  ', 'testing'"
        })
        void shouldTrimWhiteSpaces_whenNameContainSpaces(String values, String expected) {
            assertEquals(expected, AuthorValidator.validateName(values));
        }
    }

    @Nested
    class BirthYearTest {

        @ParameterizedTest
        @ValueSource(ints = {1990,2000,1800})
        void shouldReturnSameBirthYear_whenParamsAreCorrect(int values) {
            int result = AuthorValidator.validateBirthYear(values);
            assertEquals(values,result);
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -10, -1000})
        void shouldThrowException_whenBirthYearIsNegative(int values) {
            assertIllegalArgumentException(getValidateBirthYear(values), "La fecha de nacimiento no puede ser menor a 0");
        }

        @ParameterizedTest
        @ValueSource(ints = {2050, 3000, 100000})
        void shouldThrowException_whenBirthYearIsLongerThanCurrentYear(int values) {
            assertIllegalArgumentException(getValidateBirthYear(values), "La fecha de nacimiento no puede ser mayor que el año actual");
        }
    }

    @Nested
    class DeathYearTest {

        @ParameterizedTest
        @ValueSource(ints = {1990,2000,1800})
        void shouldReturnSameDeathYear_whenParamsAreCorrect(int values) {
            int result = AuthorValidator.validateDeathYear(values,values -10);
            assertEquals(values,result);
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -10, -1000})
        void shouldThrowException_whenDeathYearIsNegative(int values) {
            assertIllegalArgumentException(getValidateDeathYear(values, 1990),
                    "La fecha de fallecimiento no puede ser menor a 0");
        }

        @ParameterizedTest
        @ValueSource(ints = {2030,2050,3000})
        void shouldThrowException_whenDeathYearIsLongerThanCurrentYear(int values) {
            assertIllegalArgumentException(getValidateDeathYear(values, 1990),
                    "La fecha de fallecimiento no puede ser mayor que el año actual");
        }

        @Test
        void shouldThrowException_whenDeathYearIsLessThanBirthYear() {
            assertIllegalArgumentException(getValidateDeathYear(1960, 1970),
                    "La fecha de fallecimiento no puede ser menor que la fecha de nacimiento. Use 0 si el autor aún vive");
        }
    }


    private static Executable getValidateIdExecutable(Long id) {
        return () -> AuthorValidator.validateId(id);
    }
    private static Executable getValidateName(String name) {
        return () -> AuthorValidator.validateName(name);
    }

    private static Executable getValidateBirthYear(int birthYear) {
        return () -> AuthorValidator.validateBirthYear(birthYear);
    }

    private static Executable getValidateDeathYear(int deathYear, int birthYear) {
        return () -> AuthorValidator.validateDeathYear(deathYear, birthYear);
    }
}
