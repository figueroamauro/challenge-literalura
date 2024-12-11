package ar.com.old.challenge_literalura.models;

public class Author {
    private String name;
    private int birthYear;
    private int deathYear;

    public Author(String name, int birthYear, int deathYear) {
        this.name = validateName(name);
        this.birthYear = birthYear;
        this.deathYear = deathYear;

    }

    private static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El atributo name no puede estar vacio ni ser nulo");
        }
        if (name.length() > 30) {
            throw new IllegalArgumentException("El atributo name no puede superar los 30 caracteres");
        }
        return name.trim();
    }

    public String getName() {
        return this.name;
    }
}
