package ar.com.old.challenge_literalura.models;

import jakarta.persistence.*;

import static ar.com.old.challenge_literalura.models.validators.AuthorValidator.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private Long id;
    private int birthYear;
    private int deathYear;

    public Author(String name, int birthYear, int deathYear) {
        this.name = validateName(name);
        this.birthYear = validateBirthYear(birthYear);
        this.deathYear = validateDeathYear(deathYear, birthYear);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
