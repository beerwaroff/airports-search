package ru.beerwaroff.helpers;

public class Stopwatch {
    private long start;
    public Stopwatch() {
    }

    public void start() {
        this.start = System.currentTimeMillis();
    }
    public long getDuration() {
        return System.currentTimeMillis() - start;
    }
}
