package ar.com.old.challenge_literalura.models;

import java.time.LocalDate;

public class Author {
    private String name;
    private int birthYear;
    private int deathYear;

    public Author(String name, int birthYear, int deathYear) {
        this.name = validateName(name);
        this.birthYear = validateBirthYear(birthYear);
        this.deathYear = deathYear;

    }

    private static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío ni ser nulo");
        }
        if (name.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede superar los 30 caracteres");
        }
        return name.trim();
    }

    private static int validateBirthYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser menor a 0");
        }
        if (year >= LocalDate.now().getYear()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor que el año actual");
        }
        return year;
    }

    public String getName() {
        return this.name;
    }
}
