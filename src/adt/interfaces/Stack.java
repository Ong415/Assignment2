package adt.interfaces;

public interface Stack<T> extends Collection<T> {
    void push(T element);

    T peek();

    T pop();
}
