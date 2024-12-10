package ar.com.old.challenge_literalura.models;

public class Author {
    private String name;
    private int birthYear;
    private int deathYear;

    public Author(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio ni ser nulo");
        }
    }

}
