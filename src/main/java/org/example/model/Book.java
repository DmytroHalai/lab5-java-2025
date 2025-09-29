package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final int yearPublished;
    private final String publisher;
    private final int pages;
    private final double price;

    @Override
    public int compareTo(Book book) {
        return this.getPublisher().compareToIgnoreCase(book.getPublisher());
    }
}
