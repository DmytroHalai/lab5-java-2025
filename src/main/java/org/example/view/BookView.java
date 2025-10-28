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
        System.out.println("6 - Save files into selected file");
        System.out.println("7 - Download books from file");
        System.out.println("8 - Encrypt file");
        System.out.println("9 - Decrypt file");
        System.out.println("10 - Get tags of the page by URL");
        System.out.println("11 - Print line with most words from file");
        System.out.println("0 - Exit");
        System.out.print("Choose: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print("Choose: ");
        }
        return scanner.hasNext() ? scanner.nextInt() : -1;
    }

    public void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            books.forEach(System.out::println);
        }
    }

    public int readInt(String s) {
        System.out.print(s);
        return scanner.nextInt();
    }

    public String readString(String s) {
        System.out.print(s);
        return scanner.next();
    }
}
