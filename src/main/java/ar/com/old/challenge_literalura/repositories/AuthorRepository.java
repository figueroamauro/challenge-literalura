package ar.com.old.challenge_literalura.repositories;

import ar.com.old.challenge_literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT a FROM Author a WHERE a.birthYear < :year AND (a.deathYear > :year OR a.deathYear IS NULL)")
    List<Author> findAuthorsAliveInYear(@Param("year") int year);
}
