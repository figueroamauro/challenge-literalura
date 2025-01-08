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
    private List<Book> bookList;

    public UserInterface(BookRepository bookRepository) {
        this.scanner = new Scanner(System.in);
        this.service = new BookService(bookRepository, new GutendexServiceAPI());
        this.bookList = new ArrayList<>();
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
                    // Buscar libros en internet.
                    System.out.println("Ingresa el titulo, nombre del autor o palabra clave del libro que deseas buscar.");
                    String title = scanner.nextLine();
                    this.bookList = service.fetchBookByTitleInApi(title);
                    Menu.printBookList(this.bookList);
                    break;
                case 2:
                    // Agregar un libro a tu colección.
                    System.out.println("Ingresa el id del libro que deseas agregar a tu colección.\n");
                    int id = getUserOption();
                    boolean bookFound = findAndSaveBook(id);
                    if (!bookFound) {
                        System.out.println("El libro no se encuentra en el listado anteriormente buscado.\n");
                    }
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
                    System.out.println("Opcion no válida: Debes ingresar un número entre 0 y 6.\n");
            }
        } while (option != 0);
    }

    private void saveBook(Book book) {
        try {
            service.saveBook(book);
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