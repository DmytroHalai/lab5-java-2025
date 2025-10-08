package org.example.model;

public record Book(String title, String author, int yearPublished, String publisher, int pages,
                   double price) implements Comparable<Book> {
    @Override
    public int compareTo(Book book) {
        return this.publisher().compareToIgnoreCase(book.publisher());
    }
}
