package ru.beerwaroff.helpers;


import java.util.*;

public class Sorter {
    public static void sortByColumn(List<String[]> data) {
        Collections.sort(data, new Comparator<String[]> () {
            public int compare(String[] a, String[] b) {
                return a[1].compareTo(b[1]);
            }
        });
    }

}
