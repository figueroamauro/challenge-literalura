package ar.com.old.challenge_literalura.models;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUtils {

    public static void assertIllegalArgumentException(Executable executable, String message) {
        assertEquals(message,
                assertThrows(IllegalArgumentException.class, executable).getMessage()
        )
        ;
    }
}
