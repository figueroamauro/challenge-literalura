package ar.com.old.challenge_literalura.view;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import ar.com.old.challenge_literalura.services.AuthorService;
import ar.com.old.challenge_literalura.services.BookService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final BookService bookService;
    private final AuthorService authorService;
    private List<Book> bookList;
    private List<Author> authorList;

    public UserInterface(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.scanner = new Scanner(System.in);
        this.bookService = new BookService(bookRepository, new GutendexServiceAPI());
        this.authorService = new AuthorService(authorRepository);
        this.bookList = new ArrayList<>();
        this.authorList = new ArrayList<>();
    }

    public void start() {
        Menu.printBanner();
        int option;
        do
        {
            Menu.printMenu();
            option = getUserOption();
            switch (option) {
                case 1:
                    System.out.println("Ingresa el titulo, nombre del autor o palabra clave del libro que deseas buscar.");
                    String title = scanner.nextLine();
                    this.bookList = bookService.fetchBookByTitleInApi(title);
                    System.out.println("\nLIBROS ENCONTRADOS:\n");
                    Menu.printList(this.bookList);
                    break;

                case 2:
                    System.out.println("Ingresa el id del libro que deseas agregar a tu colección.\n");
                    int id = getUserOption();
                    boolean bookFound = findAndSaveBook(id);
                    if (!bookFound) {
                        System.out.println("El libro no se encuentra en el listado anteriormente buscado.\n");
                    }
                    break;

                case 3:
                    System.out.println("Tu colección de libros:\n");
                    this.bookList = bookService.getAllBooks();
                    Menu.printList(this.bookList);
                    break;

                case 4:
                    System.out.println("Tu colección de autores:\n");
                     this.authorList = authorService.getAllAuthors();
                    Menu.printList(this.authorList);
                    break;
                case 0:
                    // Salir.
                    break;
                default:
                    System.out.println("Opcion no válida: Debes ingresar un número entre 0 y 6.\n");
            }
        } while (option != 0);
    }

    private void saveBook(Book book) {
        try {
            bookService.saveBook(book);
            System.out.println("Libro guardado con exito: " + book.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private int getUserOption() {
        int option;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            option = -1;

            scanner.nextLine();
        }
        return option;
    }

    private boolean findAndSaveBook(int id) {
        boolean bookFound = false;

        for (Book current : bookList) {
            if (current.getId() == id) {
                saveBook(current);
                bookFound = true;
                break;
            }
        }
        return bookFound;
    }
}