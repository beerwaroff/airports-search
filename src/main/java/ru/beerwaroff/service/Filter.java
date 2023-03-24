package ru.beerwaroff.service;

import ru.beerwaroff.helpers.FileProvider;
import ru.beerwaroff.helpers.Searcher;
import java.util.*;

public class Filter {
    private final FileProvider fileProvider;

    public Filter(FileProvider fileProvider) {
        this.fileProvider = fileProvider;
    }

    private String[] searchIndexes(String key, List<String[]> dataColumn) {
        Searcher searcher = new Searcher(dataColumn, key);
        int[] border = searcher.modifiedBinarySearch(); //
        if (border[0] == -1) {
            return new String[] {};
        }
        String[] data = new String[border[1] - border[0] + 1];

        int j = 0;
        for (int i = border[0]; i <= border[1]; i++)
            data[j++] = dataColumn.get(i)[0];

        return data;
    }

    public String[] filter(String key, int column, List<String[]> dataColumn) {

        String[] data = searchIndexes(key, dataColumn); //find the necessary indexes

        Set<String> indexes = new HashSet<>();
        Collections.addAll(indexes, data);

        List<String> row;

        int count = 0;
        while (fileProvider.getScanner().hasNextLine() || !indexes.isEmpty()) {
            if (indexes.contains(Integer.toString(count))) {
                indexes.remove(Integer.toString(count));
                row = new ArrayList<>(Arrays.asList(fileProvider.getScanner().nextLine().split(",")));
                int index = Arrays.asList(data).indexOf(Integer.toString(count));
                data[index] = row.get(column - 1) + row;
            } else
                fileProvider.getScanner().nextLine();
            count++;
        }
        return data;
    }
}
