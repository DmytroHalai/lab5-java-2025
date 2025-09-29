package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;

import java.util.List;

@RequiredArgsConstructor
public class BookService {
    private final List<Book> books;

    public List<Book> getAll() {
        return books;
    }

    public List<Book> findByAuthor(String author) {
        return books.stream()
                    .filter(b -> b.getAuthor().contains(author))
                    .toList();
    }

    public List<Book> findByPublisher(String publisher) {
        return books.stream()
                    .filter(b -> b.getPublisher().contains(publisher))
                    .toList();
    }

    public List<Book> findNewerThan(int year) {
        return books.stream()
                    .filter(b -> b.getYearPublished() > year)
                    .toList();
    }

    public List<Book> sortByPublisher() {
        return books.stream()
                    .sorted()
                    .toList();
    }
}
