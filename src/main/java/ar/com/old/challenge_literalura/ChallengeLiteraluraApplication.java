package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.models.dto.BookDTO;
import ar.com.old.challenge_literalura.models.mapers.BookMapper;
import ar.com.old.challenge_literalura.api.GutendexService;
import ar.com.old.challenge_literalura.view.Menu;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication {
    static Scanner scanner = new Scanner(System.in);
    static GutendexService service = new GutendexService();
    static Gson gson = new Gson();

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);

        Menu.printBanner();
        int option = -1;
        while (option != 0) {
            Menu.printMenu();
            option = getUserOption();
            switch (option) {
                case 1:
                    System.out.println("Escribe el titulo del libro que deseas buscar");
                    String title = scanner.nextLine();
                    getBooksFromAPI(title);
            }
        }

    }

    private  static void getBooksFromAPI(String title) {
        service.getByTitle(title).forEach(jsonElement -> {
            BookDTO dto = gson.fromJson(jsonElement, BookDTO.class);
            Book book = BookMapper.map(dto);
            System.out.println(book);
        });
    }

    private static int getUserOption() {
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            return option;

        } catch (InputMismatchException e) {
            System.out.println("Debes ingresar un formato válido\n");
            scanner.nextLine();
            return -1;
        }
    }
}
