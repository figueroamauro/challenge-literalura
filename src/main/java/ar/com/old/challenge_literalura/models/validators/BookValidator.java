package ar.com.old.challenge_literalura.models.validators;

public abstract class BookValidator {

    public static Long validateId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        if (id < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }

        return id;
    }

    public static String validateTitle(String title) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío ni ser nulo");
        }
        if (title.length() >= 50) {
            throw new IllegalArgumentException("El título no puede superar los 50 caracteres");
        }

        return title.trim();
    }
}
