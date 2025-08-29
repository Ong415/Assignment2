package adt.implementations;

import adt.interfaces.Collection;
import adt.interfaces.List;
import utils.Array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Array<T> array;
    private int length;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int size) {
        array = new Array<>(size);
    }

    private void grow() {
        array = new Array<>(array, Collection.calculateGrowth(array.size, GROW_FACTOR));
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void clear() {
        array.clear();
        length = 0;
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    @Override
    public T set(int index, T value) {
        return array.set(index, value);
    }

    @Override
    public boolean insert(int index, T element) {
        if (index > length) return false;
        if (++length > array.size) grow();
        for (int i = length - 1; i > index; i--) set(i, get(i - 1));
        array.set(index, element);
        return true;
    }

    @Override
    public T remove(int index) {
        T oldValue = null;

        if (index < length) {
            oldValue = get(index);

            for (int i = index + 1; i < length; i++) {
                set(i - 1, get(i));
            }

            array.set(--length, null);
        }

        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return get(index++);
            }
        };
    }
}
