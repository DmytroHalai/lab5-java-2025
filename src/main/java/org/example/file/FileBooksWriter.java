package org.example.file;

import org.example.model.Book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileBooksWriter {
    public static void saveInFile(String fileName, List<Book> books) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        books.forEach(book -> {
            try {
                out.writeObject(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
