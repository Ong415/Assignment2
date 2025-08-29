package adt.implementations;

import adt.interfaces.List;
import adt.interfaces.Map;

import java.util.Iterator;
import java.util.Objects;

public class ArrayMap<K, V> implements Map<K, V> {
    private class MapEntry implements Entry<K, V> {
        private final K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
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

    private final List<MapEntry> entries;

    public ArrayMap() { this(DEFAULT_SIZE); }
    public ArrayMap(int size) { entries = new ArrayList<>(size); }

    @Override
    public V insert(K key, V value) {
        var entry = entry(key);

        if (entry != null) return entry.set(value);

        entries.add(new MapEntry(key, value));

        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (Objects.equals(entries.get(i).key, key)) {
                return entries.remove(i).value;
            }
        }

        return null;
    }

    @Override
    public Entry<K, V> entry(K key) {
        for (var entry : this) {
            if (Objects.equals(entry.key(), key)) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public int size() { return entries.size(); }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private final Iterator<MapEntry> iterator = entries.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                return iterator.next();
            }
        };
    }

    @Override
    public String toString() { return format(); }
}
