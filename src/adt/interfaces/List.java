package adt.interfaces;

public interface List<T> extends Collection<T> {
    T get(int index);
    T set(int index, T value);
    default void add(T element) { insert(size(), element); }
    boolean insert(int index, T element);
    T remove(int index);
}
