package ar.com.old.challenge_literalura.models.validators;

import java.time.LocalDate;

public abstract class AuthorValidator {

    public static Long validateId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id no puede ser negativo");
        }
        return id;
    }

    public static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío ni ser nulo");
        }

        if (name.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede superar los 30 caracteres");
        }

        return name.trim();
    }

    public static int validateBirthYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser menor a 0");
        }

        if (year >= LocalDate.now().getYear()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor que el año actual");
        }

        return year;
    }

    public static int validateDeathYear(int deathYear, int birthYear) {
        if (deathYear < 0) {
            throw new IllegalArgumentException("La fecha de fallecimiento no puede ser menor a 0");
        }

        if (deathYear >= LocalDate.now().getYear()) {
            throw new IllegalArgumentException("La fecha de fallecimiento no puede ser mayor que el año actual");
        }

        if (deathYear < birthYear && deathYear != 0) {
            throw new IllegalArgumentException("La fecha de fallecimiento no puede ser menor que la fecha de nacimiento. Use 0 si el autor aún vive");
        }

        return deathYear;
    }

}
