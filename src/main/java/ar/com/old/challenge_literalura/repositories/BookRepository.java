package ar.com.old.challenge_literalura.repositories;

import ar.com.old.challenge_literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByTitle(String title);
}
