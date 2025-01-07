package ar.com.old.challenge_literalura.view;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import ar.com.old.challenge_literalura.services.BookService;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final BookService service;
    private final BookRepository bookRepository;

    public UserInterface(BookRepository bookRepository) {
        this.scanner = new Scanner(System.in);
        this.bookRepository = bookRepository;
        this.service = new BookService(bookRepository, new GutendexServiceAPI());
    }

    public void start() {
        Menu.printBanner();
        int option = -1;
        do
        {
        Menu.printMenu();
        option = scanner.nextInt();
        switch (option) {
            case 1:
                // Buscar libros por su titulo.
                System.out.println("Ingresa el titulo del libro que deseas buscar.");
                String title = scanner.nextLine();
                service.searchByTitle(title);

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
                System.out.println("Opcion no válida.");
        }
        } while (option != 0);
    }
}
