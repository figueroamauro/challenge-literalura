package ar.com.old.challenge_literalura.repositories;

import ar.com.old.challenge_literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitle(String title);

    List<Book> findAllByLanguages(String lang1);
}
