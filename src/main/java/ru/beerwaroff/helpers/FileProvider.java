package ru.beerwaroff.helpers;

import java.io.*;
import java.util.Scanner;

public class FileProvider {
    private Scanner scanner;
    private FileInputStream fileInputStream;
    private final String PATH;
    public FileProvider(String path) throws FileNotFoundException {
        this.PATH = path;
        this.fileInputStream = new FileInputStream(PATH);
        this.scanner = new Scanner(fileInputStream);
    }
    public void refresh() throws IOException {
        this.fileInputStream = new FileInputStream(PATH);
        this.scanner = new Scanner(fileInputStream);
    }
    public void close() throws IOException {
        this.fileInputStream.close();
        this.scanner.close();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
