package ar.com.old.challenge_literalura.models;

import static ar.com.old.challenge_literalura.models.validators.AuthorValidator.*;

public class Author {
    private String name;
    private int birthYear;
    private int deathYear;

    public Author(String name, int birthYear, int deathYear) {
        this.name = validateName(name);
        this.birthYear = validateBirthYear(birthYear);
        this.deathYear = validateDeathYear(deathYear, birthYear);

    }

    public String getName() {
        return this.name;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public int getDeathYear() {
        return this.deathYear;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = validateBirthYear(birthYear);
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = validateDeathYear(deathYear, this.birthYear);
    }
}
