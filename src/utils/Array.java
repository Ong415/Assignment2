package utils;

import adt.interfaces.Collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array<T> implements Collection<T> {
    private final Object[] array;
    public final int size;

    public Array(int size) {
        array = new Object[size];
        this.size = size;
    }

    public Array(Array<T> array, int size) {
        this.array = Arrays.copyOf(array.array, size);
        this.size = size;
    }

    @Override
    public int size() { return size; }
    @Override
    public void clear() {Arrays.fill(array, null); }
    @SuppressWarnings("unchecked")
    public T get(int index) { return (T) array[index]; }
    public T getOrNull(int index) { return index < size ? get(index) : null; }
    public T set(int index, T value) {
        T oldValue = get(index);
        array[index] = value;
        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return get(index++);
            }
        };
    }
}
