package ar.com.old.challenge_literalura.view;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import ar.com.old.challenge_literalura.services.BookService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final BookService service;
    private final BookRepository bookRepository;
    private List<Book> bookList;

    public UserInterface(BookRepository bookRepository) {
        this.scanner = new Scanner(System.in);
        this.bookRepository = bookRepository;
        this.service = new BookService(bookRepository, new GutendexServiceAPI());
        this.bookList = new ArrayList<>();
    }

    public void start() {
        Menu.printBanner();
        int option = -1;
        do
        {
        Menu.printMenu();
            option = getUserOption();
            switch (option) {
            case 1:
                // Buscar libros por su titulo.
                System.out.println("Ingresa el titulo del libro que deseas buscar.");
                String title = scanner.nextLine();
                this.bookList =  service.fetchBookByTitleInApi(title);
                Menu.printBookList(this.bookList);

                break;
            case 2:
                // Ver libros registrados en tu colección.
                break;
            case 3:
                // Agregar un libro a tu colección.
                break;
            case 4:
                // Eliminar un libro de tu colección.
                break;
            case 0:
                // Salir.
                break;
                default:
                    System.out.println("Opcion no válida: Debes ingresar un número entre 0 y 4.\n");
        }
        } while (option != 0);
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
}
