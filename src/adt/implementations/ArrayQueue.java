package adt.implementations;

import adt.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private class Node {
        private final T element;
        private Node next;

        Node(T element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void enqueue(T element) {
        var node = new Node(element);

        if (isEmpty()) head = tail = node;
        else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;

        T element = head.element;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;

        return element;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T element = node.element;
                node = node.next;
                return element;
            }
        };
    }
}
