package org.example.file;

import org.example.model.Book;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileBooksReader {
    public static List<Book> downloadFromFile(String fileName) throws IOException {
        List<Book> books = new ArrayList<>();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        while(true) {
            try {
                Book book = (Book) in.readObject();
                books.add(book);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        return books;
    }
}
