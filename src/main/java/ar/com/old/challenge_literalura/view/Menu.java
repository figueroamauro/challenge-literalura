package ar.com.old.challenge_literalura.view;

import ar.com.old.challenge_literalura.models.Book;

import java.util.List;

public class Menu {

    public static void printBanner() {
        System.out.println();
        System.out.println("*  *           *  *     *  *  *  *  *  *     *  *  *  *  *  *     *  *  *  *  *           *  *  *  *  *  *     *  *           *  *        *  *     *  *  *  *  *         *  *  *  *  *  *");
        System.out.println("*  *           *  *     *  *  *  *  *  *     *  *  *  *  *  *     *  *  *  *  *  *        *  *  *  *  *  *     *  *           *  *        *  *     *  *  *  *  *  *      *  *  *  *  *  *");
        System.out.println("*  *           *  *           *  *           *  *                 *  *          *  *      *  *        *  *     *  *           *  *        *  *     *  *          *  *    *  *        *  *")      ;
        System.out.println("*  *           *  *           *  *           *  *  *  *  *  *     *  *  *  *  *  *        *  *  *  *  *  *     *  *           *  *        *  *     *  *  *  *  *  *      *  *  *  *  *  *");
        System.out.println("*  *           *  *           *  *           *  *  *  *  *  *     *  *  *  *  *           *  *  *  *  *  *     *  *           *  *        *  *     *  *  *  *  *         *  *  *  *  *  *");
        System.out.println("*  *           *  *           *  *           *  *                 *  *       *  *         *  *        *  *     *  *           *  *        *  *     *  *       *  *       *  *        *  *");
        System.out.println("*  *  *  *     *  *           *  *           *  *  *  *  *  *     *  *         *  *       *  *        *  *     *  *  *  *     *  *  *  *  *  *     *  *        *  *      *  *        *  *");
        System.out.println("*  *  *  *     *  *           *  *           *  *  *  *  *  *     *  *           *  *     *  *        *  *     *  *  *  *     *  *  *  *  *  *     *  *          *  *    *  *        *  *");
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("¿ QUÉ DESEAS HACER AHORA ?\n");
        System.out.println("1. Buscar libros en internet.");
        System.out.println("2. Agregar un libro a tu colección.");
        System.out.println("3. Listar todos los libros registrados.");
        System.out.println("4. Listar todos los autores registrados");
        System.out.println("5. Listar libros por idioma.");
        System.out.println("6. Listar autores vivos en un determinado año");
        System.out.println("0. Salir.\n");
    }

    public static void printBookList(List<Book> bookList) {
        System.out.println("\nLIBROS ENCONTRADOS:\n");
        bookList.forEach(System.out::println);
        System.out.println();
    }
    }
