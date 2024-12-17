package ar.com.old.challenge_literalura.models.validators;

import ar.com.old.challenge_literalura.models.Author;

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

    public static Author validateAuthor(Author author) {
        
        if (author == null) {
            throw new IllegalArgumentException("El autor no puede ser nulo");
        }
        return author;
    }

    public static String validateLanguage(String language) {
        if (language == null || language.isEmpty()) {
            throw new IllegalArgumentException("El lenguaje no puede estar vacío ni ser nulo");
        }
        if (language.length() >= 20) {
            throw new IllegalArgumentException("El lenguaje no puede superar los 20 caracteres");
        }

        return language.trim();
    }

    public static int validateDownloadCount(Integer downloadCount) {
        if (downloadCount == null) {
            throw new IllegalArgumentException("La cantidad de descargas no puede ser nula");
        }
        if (downloadCount < 0) {
            throw new IllegalArgumentException("La cantidad de descargas no puede ser menor a 0");
        }
        return downloadCount;
    }
}
