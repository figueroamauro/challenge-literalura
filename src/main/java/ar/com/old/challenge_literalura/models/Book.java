package ar.com.old.challenge_literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    private final List<Author> authorList;
    @ElementCollection
    private final List<String> languages;
    private Integer downloadCount;

    public Book(Long id, String title, int downloadCount) {
        this.authorList = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.downloadCount = downloadCount;
    }

    public Book() {
        this.authorList = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<String> getLanguages() {
        return languages;
    }


}
