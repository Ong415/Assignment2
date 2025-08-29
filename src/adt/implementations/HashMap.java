package adt.implementations;

import adt.interfaces.Collection;
import adt.interfaces.Map;
import utils.Array;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K,V> {
    private class MapEntry implements Entry<K, V> {
        private final K key;
        private V value;

        private MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return this.key;
        }

        @Override
        public V value() {
            return this.value;
        }

        @Override
        public V set(V value) {
            var oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() { return format(); }
    }

    private Array<MapEntry> entries;
    private int count;

    public HashMap() { this(DEFAULT_SIZE); }
    public HashMap(int size) { entries = new Array<>(size); }

    private void grow() {
        var oldEntries = entries;

        count = 0;
        entries = new Array<>(Collection.calculateGrowth(entries.size, GROW_FACTOR));

        for (var entry : oldEntries) {
            if (entry != null) insert(entry.key, entry.value);
        }
    }

    private Integer getIndex(K key) {
        if (isEmpty()) return null;

        int hash = key.hashCode();

        for (int i = 0; i < entries.size; i++) {
            int j = (hash + i) % entries.size;
            var entry = entries.get(j);

            if (entry != null && Objects.equals(entry.key, key)) return j;
        }

        return null;
    }

    private int getNewIndex(K key) {
        if (count + 1 > entries.size) grow();

        int i = key.hashCode();

        while (true) {
            i %= entries.size;

            if (entries.get(i) == null) return i;

            i++;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        entries.clear();
        count = 0;
    }

    @Override
    public boolean has(K key) {
        return getIndex(key) != null;
    }

    @Override
    public V insert(K key, V value) {
        var index = getIndex(key);

        if (index == null) {
            index = getNewIndex(key);

            entries.set(index, new MapEntry(key, value));
            count++;

            return null;
        }
        else return entries.get(index).set(value);
    }

    @Override
    public V remove(K key) {
        var index = getIndex(key);
        if (index == null) return null;
        var value = entries.get(index).value;
        entries.set(index, null);
        count--;
        return value;
    }

    @Override
    public Entry<K, V> entry(K key) {
        var index = getIndex(key);
        return index == null ? null : entries.get(index);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                while (index < entries.size) {
                    if (entries.get(index) != null) return true;
                    index++;
                }

                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) throw new NoSuchElementException();
                return entries.get(index++);
            }
        };
    }

    @Override
    public String toString() { return format(); }
}
