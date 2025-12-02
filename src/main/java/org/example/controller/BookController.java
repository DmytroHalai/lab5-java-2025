package org.example.controller;

import org.example.file.FileReader;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.utils.CryptoService;
import org.example.utils.URLViewer;
import org.example.view.BookView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookController {

    private final BookService bookService;
    private final BookView view;

    public BookController(List<Book> books, Scanner scanner) {
        this.bookService = new BookService(books);
        this.view = new BookView(scanner);
    }

    public void run() {
        Map<Integer, Runnable> actions = new HashMap<>();
        actions.put(1, () -> view.printBooks(bookService.getAll()));
        actions.put(2, () -> {
            String author = view.readString("Enter author: ");
            view.printBooks(bookService.findByAuthor(author));
        });
        actions.put(3, () -> {
            String publisher = view.readString("Enter publisher: ");
            view.printBooks(bookService.findByPublisher(publisher));
        });
        actions.put(4, () -> {
            int year = view.readInt("Enter year: ");
            view.printBooks(bookService.findNewerThan(year));
        });
        actions.put(5, () -> view.printBooks(bookService.sortByPublisher()));
        actions.put(6, () -> {
            String fileName = view.readString("Enter the name of the file you'd like to save books list into: ");
            bookService.saveInFile(fileName);
            System.out.println("Books uploading process was finished");
        });
        actions.put(7, () -> {
            String fileName = view.readString("Enter the name of the file which you'd like to download books info from: ");
            bookService.downloadFromFile(fileName);
            System.out.println("Books downloading process was finished");
        });

        actions.put(8, () -> {
            String sourceFileName = view.readString("Enter the name of the file which content you want to encrypt: ");
            String resultFileName = view.readString("Enter the name of the file, in which encrypted content will be written: ");
            String key = view.readString("Enter the key for the encryptor (1-symbol): ");
            CryptoService.encrypt(sourceFileName, resultFileName, key.toCharArray()[0]);
            System.out.println("Encryption process was finished");
        });

        actions.put(9, () -> {
            String sourceFileName = view.readString("Enter the name of the file which content you want to decrypt: ");
            String resultFileName = view.readString("Enter the name of the file, in which decrypted content will be written: ");
            String key = view.readString("Enter the key for the decryptor (1-symbol): ");
            CryptoService.decrypt(sourceFileName, resultFileName, key.toCharArray()[0]);
            System.out.println("Decryption process was finished");
        });

        actions.put(10, () -> {
            String sourceURL = view.readString("Enter the source URL: ");
            int isOrderedByValue = view.readInt("Enter 1 to order by amount or 0 if by alphabet of tags: ");
            URLViewer.countAndPrintTagsFromUrl(sourceURL, isOrderedByValue == 1);
        });

        actions.put(11, () -> {
            String fileName = view.readString("Enter text file path: ");
            String longestLine = FileReader.findLongestLineByWords(fileName);
            System.out.println("Longest line: " + longestLine);
        });
        actions.put(0, () -> System.out.println("Exiting..."));

        while (true) {
            int choice = view.showMenu();
            Runnable action = actions.get(choice);
            if (action == null) System.out.println("Invalid choice. Please try again.");
            else if (action == actions.get(0)) break;
            else action.run();
        }
        System.out.println("Bye!");
    }
}
