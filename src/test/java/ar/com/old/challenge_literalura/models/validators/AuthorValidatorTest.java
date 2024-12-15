package ar.com.old.challenge_literalura.models.validators;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static ar.com.old.challenge_literalura.models.TestUtils.*;
import static ar.com.old.challenge_literalura.models.validators.AuthorValidator.validateName;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthorValidatorTest {

    @Nested
    class NameTest {

        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowException_emptyOrNullName(String name){
            assertIllegalArgumentException(getValidateNameExecutable(name),"El nombre no puede estar vacío ni ser nulo");
        }

        @ParameterizedTest
        @ValueSource(strings = {" 123123123123123123123123123123123",
                " test test test test test test test test test"})
        void shouldThrowException_nameLongerThan30Characters(String name){
            assertIllegalArgumentException(getValidateNameExecutable(name),"El nombre no puede superar los 30 caracteres");
        }

        @ParameterizedTest
        @CsvSource({
                "'test  ', 'test'",
                "'  test', 'test'",
                "'  testing  ', 'testing'"
        })
        void shouldTrimWhiteSpaces_NameContainSpaces(String values, String expected) {
            assertEquals(expected,AuthorValidator.validateName(values));
        }
    }

    private static Executable getValidateNameExecutable(String name) {
        return ()->AuthorValidator.validateName(name);
    }
}
