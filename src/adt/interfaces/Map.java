package adt.interfaces;

public interface Map<K, V> extends Collection<Map.Entry<K, V>> {
    default boolean has(K key) { return entry(key) != null; }
    default V get(K key) { var e = entry(key); return e == null ? null : e.value(); }
    V insert(K key, V value);
    V remove(K key);
    Entry<K, V> entry(K key);

    interface Entry<K, V> {
        K key();
        V value();
        V set(V value);

        default String format() { return "Entry { " + key() + ": " + value() + " }";}
    }

    default String format() {
        var b = new StringBuilder();
        var first = true;

        b.append("Map {");

        for (var entry : this) {
            if (first) {
                first = false;
                b.append(" ");
            }
            else b.append(", ");
            b.append(entry.key());
            b.append(": ");
            b.append(entry.value());
        }

        if (!first) b.append(" ");

        b.append("}");

        return new String(b);
    }
}
