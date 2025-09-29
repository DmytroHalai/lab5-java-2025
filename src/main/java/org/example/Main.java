package org.example;

import org.example.controller.BookController;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.view.BookView;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "Scribner", 180, 13.99),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960, "J.B. Lippincott & Co.", 281, 7.99),
                new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328, 6.99),
                new Book("Pride and Prejudice", "Jane Austen", 1813, "T. Egerton", 279, 5.99),
                new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "Little, Brown and Company", 214, 8.99),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937, "George Allen & Unwin", 310, 10.99),
                new Book("Fahrenheit 451", "Ray Bradbury", 1953, "Ballantine Books", 194, 9.99),
                new Book("Brave New World", "Aldous Huxley", 1932, "Chatto & Windus", 268, 12.99),
                new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, "Bloomsbury", 223, 10.99),
                new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, "George Allen & Unwin", 1178, 20.99)
        );

        BookService service = new BookService(books);
        BookView view = new BookView();
        BookController controller = new BookController(service, view);

        controller.run();
    }
}