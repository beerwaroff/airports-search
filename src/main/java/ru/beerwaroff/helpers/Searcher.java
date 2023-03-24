package ru.beerwaroff.helpers;

import java.util.List;

public class Searcher {
    public List<String[]> dataColumn;
    public String key;

    public Searcher(List<String[]> dataColumn, String key) {
        this.dataColumn = dataColumn;
        this.key = key;
    }

    private int[] binarySearch(char keySymbol, int left, int right, int index) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            char[] rowSymbols = dataColumn.get(middle)[1].toLowerCase().toCharArray();
            if (keySymbol > rowSymbols[index])
                left = middle + 1;
            else if (keySymbol < rowSymbols[index])
                right = middle - 1;
            else
                return new int[] {left, right};
        }
        return new int[] {-1, -1};
    }

    private int[] findArea(char keySymbol, int left, int right, int index, int size) {
        char[] leftSymbols = dataColumn.get(left)[1].toLowerCase().toCharArray();
        char[] rightSymbols = dataColumn.get(right)[1].toLowerCase().toCharArray();

        while (leftSymbols[index] == keySymbol && left > 0) {
            left--;
            leftSymbols = dataColumn.get(left)[1].toLowerCase().toCharArray();
        }

        while (rightSymbols[index] == keySymbol && right < size-1) {
            right++;
            rightSymbols = dataColumn.get(right)[1].toLowerCase().toCharArray();
        }

        while (leftSymbols[index] != keySymbol && left <= right) {
            left++;
            leftSymbols = dataColumn.get(left)[1].toLowerCase().toCharArray();
        }

        while (rightSymbols[index] != keySymbol && right >= left) {
            right--;
            rightSymbols = dataColumn.get(right)[1].toLowerCase().toCharArray();
        }

        return new int[] {left, right};
    }

    public int[] modifiedBinarySearch() {
        int size = dataColumn.size();

        int left = 0;
        int right = size - 1;

        char[] keySymbols = key.toCharArray();
        int lengthKey = keySymbols.length;

        for (int i = 0; i < lengthKey; i++) {
            int[] indexes = binarySearch(keySymbols[i], left, right, i+1);
            if (indexes[0] == -1) {
                return indexes;
            }
            int[] updateBorder = findArea(keySymbols[i], indexes[0], indexes[1], i+1, size);
            left = updateBorder[0];
            right = updateBorder[1];
        }

        return new int[] {left, right};
    }


}
