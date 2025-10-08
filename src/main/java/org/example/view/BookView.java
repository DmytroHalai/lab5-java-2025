package org.example.view;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class BookView {
    private final Scanner scanner;

    public int showMenu() {
        System.out.println("\n1 - Show all books");
        System.out.println("2 - Show books by author");
        System.out.println("3 - Show books by publisher");
        System.out.println("4 - Show books newer than a given year");
        System.out.println("5 - Sort books by publishers");
        System.out.println("6 - Exit");
        System.out.print("Choose: ");
        return scanner.hasNext() ? scanner.nextInt() : -1;
    }

    public String readAuthor() {
        System.out.print("Enter author: ");
        return scanner.next();
    }

    public void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            books.forEach(System.out::println);
        }
    }

    public String readPublisher() {
        System.out.print("Enter publisher: ");
        return scanner.next();
    }

    public int readYear() {
        System.out.print("Enter year: ");
        return scanner.nextInt();
    }
}
