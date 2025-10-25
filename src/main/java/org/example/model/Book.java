package org.example.model;

import java.io.Serializable;

public record Book(String title, String author, int yearPublished, String publisher, int pages,
                   double price) implements Comparable<Book>, Serializable {
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Book book) {
        return this.publisher().compareToIgnoreCase(book.publisher());
    }
}
