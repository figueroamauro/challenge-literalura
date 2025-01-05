package ar.com.old.challenge_literalura.models.mapers;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.models.dto.AuthorDTO;

public class AuthorMapper {

    public static Author map(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.name());
        author.setBirthYear(dto.birthYear());
        author.setDeathYear(dto.deathYear());
        return author;
    }

}
