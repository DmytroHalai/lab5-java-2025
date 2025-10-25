package org.example.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;

public class CryptoService {
    public static void encrypt(String inputFile, String outputFile, char key) throws IOException {
        try (FileReader fr = new FileReader(inputFile);
             FilterWriter fw = new FilterWriter(new FileWriter(outputFile)) {
                 @Override
                 public void write(int c) throws IOException {
                     super.write(c + key);
                 }
             }) {
            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }
        }
    }

    public static void decrypt(String inputFile, String outputFile, char key) throws IOException {
        try (FileReader fr = new FileReader(inputFile);
             FilterWriter fw = new FilterWriter(new FileWriter(outputFile)) {
                 @Override
                 public void write(int c) throws IOException {
                     super.write(c - key);
                 }
             }) {
            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }
        }
    }
}
