package ru.beerwaroff.service;

import ru.beerwaroff.helpers.FileProvider;
import java.io.IOException;
import java.util.*;

public class Reader{
    private final FileProvider fileProvider;

    public Reader(FileProvider fileProvider) throws IOException {
        this.fileProvider = fileProvider;
    }

    public List<String[]> readFileByColumn(int column) throws IOException {
        List<String[]> data = new ArrayList<>();
        int count = 0;

        while (fileProvider.getScanner().hasNextLine()) {
            List<String> row = new ArrayList<>(Arrays.asList(fileProvider.getScanner().nextLine().split(",")));
            data.add(new String[] {Integer.toString(count++), row.get(column-1)});
        }
        return data;
    }

}
