package adt.interfaces;

public interface Queue<T> extends Collection<T> {
    T dequeue();
    void enqueue(T element);
    default T popFirst() { return dequeue(); }
    default void pushLast(T element) { enqueue(element); }
}
