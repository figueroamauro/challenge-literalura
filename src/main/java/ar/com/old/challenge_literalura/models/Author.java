package ar.com.old.challenge_literalura.models;

import jakarta.persistence.*;

import static ar.com.old.challenge_literalura.models.validators.AuthorValidator.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birthYear;
    private int deathYear;

    public Author(Long id, String name, int birthYear, int deathYear) {
        this.id = validateId(id);
        this.name = validateName(name);
        this.birthYear = validateBirthYear(birthYear);
        this.deathYear = validateDeathYear(deathYear, birthYear);
    }

    public Author() {
    }

    public Long getId() {
        return this.id;
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

    @Override
    public String toString() {
        return "Author{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", birthYear=" + birthYear +
                       ", deathYear=" + deathYear +
                       '}';
    }
}
