package org.example.file;

import org.example.model.Book;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileBooksWriter {
    public static void saveInFile(String fileName, List<Book> books) {
        if (fileName == null || fileName.isBlank()) {
            System.err.println("File name cannot be empty");
            return;
        }

        if (books == null || books.isEmpty()) {
            System.out.println("No books to save. File will not be created.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Book book : books) {
                out.writeObject(book);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot create or open file: " + fileName);
        } catch (IOException e) {
            System.err.println("I/O error while saving books: " + e.getMessage());
        }
    }

}
