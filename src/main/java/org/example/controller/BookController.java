package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.example.view.BookView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookController {

    private final BookService bookService;
    private final BookView bookView;

    public BookController(List<Book> books, Scanner scanner) {
        this.bookService = new BookService(books);
        this.bookView = new BookView(scanner);
    }

    public void run() {
        Map<Integer, Runnable> actions = new HashMap<>();
        actions.put(1, () -> bookView.printBooks(bookService.getAll()));
        actions.put(2, () -> {
            String author = bookView.readAuthor();
            bookView.printBooks(bookService.findByAuthor(author));
        });
        actions.put(3, () -> {
            String publisher = bookView.readPublisher();
            bookView.printBooks(bookService.findByPublisher(publisher));
        });
        actions.put(4, () -> {
            int year = bookView.readYear();
            bookView.printBooks(bookService.findNewerThan(year));
        });
        actions.put(5, () -> bookView.printBooks(bookService.sortByPublisher()));
        actions.put(6, () -> System.out.println("Exiting..."));
        while (true) {
            int choice = bookView.showMenu();
            Runnable action = actions.get(choice);
            if (action != null && action != actions.get(6)) {
                action.run();
            } else break;
        }
        System.out.println("Bye!");
    }
}
