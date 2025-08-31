package adt.interfaces;

import java.util.Objects;

public interface Collection<T> extends Iterable<T> {
    int DEFAULT_SIZE = 10;
    double GROW_FACTOR = 1.5;

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void clear();

    default boolean contains(T element) {
        for (var t : this) {
            if (Objects.equals(t, element)) {
                return true;
            }
        }

        return false;
    }

    static int calculateGrowth(int size, double factor) {
        return Math.max(size + 1, (int) (size * factor));
    }
}
