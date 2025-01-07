package ar.com.old.challenge_literalura.models.mapers;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.models.dto.BookDTO;

import java.util.List;

public class BookMapper {

    public static Book map(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.id());
        book.setTitle(dto.title());
        book.setDownloadCount(dto.downloadCount());
        dto.authorList().forEach(authorDTO -> {
            book.addAuthor(AuthorMapper.map(authorDTO));
        });
        dto.languages().forEach(book::addLanguage);
        return book;
    }

    public static  List<Book> bookDtoListToBookList(List<BookDTO> bookDtoList) {
        return bookDtoList.stream().map(BookMapper::map).toList();
    }

}
