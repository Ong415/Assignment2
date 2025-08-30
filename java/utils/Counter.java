package utils;

public class Counter {
    private int i;

    public Counter(int i) {
        this.i = i;
    }

    public int get() {
        return i++;
    }
}
