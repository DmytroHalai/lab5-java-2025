package org.example.utils;

import java.io.*;

public class CryptoService {
    public static void encrypt(String inputFile, String outputFile, char key) {
        boolean doDecrypt = false;
        engine(inputFile, outputFile, key, doDecrypt);
    }

    public static void decrypt(String inputFile, String outputFile, char key) {
        boolean doDecrypt = true;
        engine(inputFile, outputFile, key, doDecrypt);
    }

    private static void engine(String inputFile, String outputFile, char key, boolean doDecrypt) {
        try (FileReader fr = new FileReader(inputFile);
             FilterWriter fw = new FilterWriter(new FileWriter(outputFile)) {
                 @Override
                 public void write(int c) throws IOException {
                     if (doDecrypt) super.write(c - key);
                     else super.write(c + key);
                 }
             }) {

            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }

            System.out.println("File processed successfully");

        } catch (FileNotFoundException fnf) {
            System.err.println("Input file not found");

        } catch (SecurityException sec) {
            System.err.println("Access denied when reading/writing files. Check file permissions.");

        } catch (IOException io) {
            System.err.println("I/O error while processing files: " + io.getMessage());

        } catch (IllegalArgumentException ex) {
            System.err.println("Invalid encryption key or parameters: " + ex.getMessage());

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getClass().getSimpleName() + " — " + e.getMessage());
        }
    }
}
