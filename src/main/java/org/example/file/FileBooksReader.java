package org.example.file;

import org.example.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBooksReader {
    public static List<Book> downloadFromFile(String fileName) {
        List<Book> books = new ArrayList<>();

        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            System.err.println("File not found or empty: " + file.getAbsolutePath());
            return books;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = in.readObject();
                    if (obj instanceof Book book) {
                        books.add(book);
                    } else {
                        System.err.println("Skipped non-book object: " + obj.getClass().getSimpleName());
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found while reading: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
        return books;
    }

    public static String findLongestLineByWords(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return "Error: File name cannot be empty.";
        }
        File file = new File(fileName);
        if (!file.exists()) {
            return "Error: File not found: " + file.getAbsolutePath();
        }
        if (!file.canRead()) {
            return "Error: Cannot read file (check permissions): " + file.getAbsolutePath();
        }
        if (file.length() == 0) {
            return "File is empty: " + file.getAbsolutePath();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines()
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .reduce((l1, l2) -> {
                        int words1 = l1.trim().split("\\s+").length;
                        int words2 = l2.trim().split("\\s+").length;
                        return words1 >= words2 ? l1 : l2;
                    })
                    .orElse("No valid lines found in the file.");

        } catch (IOException e) {
            return "I/O error while reading file: " + e.getMessage();
        } catch (SecurityException e) {
            return "Access denied: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getClass().getSimpleName() + " — " + e.getMessage();
        }
    }
}
