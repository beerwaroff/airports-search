package ru.beerwaroff;


import java.io.File;
import java.util.*;
import ru.beerwaroff.exceptions.NonExistentArgument;
import ru.beerwaroff.helpers.FileProvider;
import ru.beerwaroff.helpers.Sorter;
import ru.beerwaroff.helpers.Stopwatch;
import ru.beerwaroff.service.Filter;
import ru.beerwaroff.service.Reader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NonExistentArgument {

        if (args.length > 1)
            throw new NonExistentArgument("Нельзя вводить больше одного аргумента.");

        int columnNumber;

        try {
            columnNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Аргумент должен быть числом.");
        }

        String path = "../airports.csv";
        FileProvider fileProvider = new FileProvider(path);

        Reader reader = new Reader(fileProvider);
        Filter filter = new Filter(fileProvider);
        Scanner scanner = new Scanner(System.in);
        Stopwatch stopwatch = new Stopwatch();

        List<String[]> dataColumn;
        String key;
        String duration;

        try {
            dataColumn = reader.readFileByColumn(columnNumber); //reads data from the specified column and indexes
            fileProvider.close();
            Sorter.sortByColumn(dataColumn); //sorts the column in ascending order

            do {
                fileProvider.refresh();
                System.out.println("Введите строку:");
                key = scanner.nextLine();

                if (Objects.equals(key, ""))
                    System.out.println("Строка не должна быть пустой. Попробуйте ещё раз!\n");
                else {
                    stopwatch.start(); //counts the execution time
                    String[] data = filter.filter(key.toLowerCase(), columnNumber, dataColumn); //we find the necessary data

                    for (String d: data)
                        System.out.println(d);

                    duration = Long.toString(stopwatch.getDuration());

                    System.out.println("Количество найденных строк: " + data.length + ", "
                            + "время затраченное на поиск: " + duration + " мс\n");
                }

                fileProvider.close();
            } while (!key.equals("!quit"));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Указанной колонки не существует\n");;
        }

    }

}
