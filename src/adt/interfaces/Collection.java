package adt.interfaces;

public interface Collection<T> extends Iterable<T> {
    int DEFAULT_SIZE = 10;
    double GROW_FACTOR = 1.5;

    int size();
    default boolean isEmpty() { return size() == 0; }

    void clear();

    static int calculateGrowth(int size, double factor) {
        return Math.max(size + 1, (int) (size * factor));
    }
}
