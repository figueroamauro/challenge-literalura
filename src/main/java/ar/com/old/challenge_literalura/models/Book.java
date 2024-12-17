package ar.com.old.challenge_literalura.models;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Long id;
    private String title;
    private final List<Author> authorList;
    private final List<String> languages;
    private Integer downloadCount;

    public Book(Long id, String title, int downloadCount) {
        this.authorList = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.downloadCount = downloadCount;
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
