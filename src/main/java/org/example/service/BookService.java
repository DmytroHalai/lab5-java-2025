package org.example.service;

import org.example.model.Book;
import org.example.file.FileBooksReader;
import org.example.file.FileBooksWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books;

    public BookService(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    public List<Book> getAll() {
        return books;
    }

    public List<Book> findByAuthor(String author) {
        return books.stream()
                    .filter(b -> b.author().contains(author))
                    .toList();
    }

    public List<Book> findByPublisher(String publisher) {
        return books.stream()
                    .filter(b -> b.publisher().contains(publisher))
                    .toList();
    }

    public List<Book> findNewerThan(int year) {
        return books.stream()
                    .filter(b -> b.yearPublished() > year)
                    .toList();
    }

    public List<Book> sortByPublisher() {
        return books.stream()
                    .sorted()
                    .toList();
    }

    public void downloadFromFile(String fileName) throws IOException {
        books.clear();
        books.addAll(FileBooksReader.downloadFromFile(fileName));
    }

    public void saveInFile(String fileName) throws IOException {
        FileBooksWriter.saveInFile(fileName, books);
    }
}
