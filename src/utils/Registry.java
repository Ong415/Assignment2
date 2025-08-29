package utils;

import adt.implementations.HashMap;
import adt.interfaces.Collection;
import adt.interfaces.Map;

import java.util.Iterator;

public class Registry<K, T extends Registry.Identifiable<K>> implements Collection<T> {
    public interface Identifiable<K> {
        K key();
    }

    private final Map<K, T> map = new HashMap<>();

    public T find(K key) { return map.get(key); }
    public void register(T element) { map.insert(element.key(), element); }

    @Override
    public int size() { return map.size(); }

    @Override
    public void clear() { map.clear(); }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<Map.Entry<K, T>> iterator = map.iterator();

            @Override
            public boolean hasNext() { return iterator.hasNext(); }

            @Override
            public T next() { return iterator.next().value(); }
        };
    }
}
