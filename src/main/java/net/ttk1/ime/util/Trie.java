package net.ttk1.ime.util;

import java.util.HashMap;
import java.util.Map;

public class Trie<K, V> {
    private final V value;
    private final Map<K, Trie<K, V>> children;

    public Trie(V value) {
        this.value = value;
        children = new HashMap<>();
    }

    public boolean hasKey(K key) {
        return children.containsKey(key);
    }

    public Trie<K, V> getChild(K key) {
        return children.get(key);
    }

    public Map<K, Trie<K, V>> getChildren() {
        return children;
    }

    public V getValue() {
        return value;
    }

    public Trie<K, V> createChild(K key, V value) throws KeyAlreadyExistsException {
        if (children.containsKey(key)) {
            throw new KeyAlreadyExistsException(String.format("Key '%s' already exists.", key));
        }
        Trie<K, V> child = new Trie<>(value);
        children.put(key, child);
        return child;
    }

    public void addChild(K key, Trie<K, V> child) throws KeyAlreadyExistsException {
        if (children.containsKey(key)) {
            throw new KeyAlreadyExistsException(String.format("Key '%s' already exists.", key));
        }
        children.put(key, child);
    }

    public static class KeyAlreadyExistsException extends Exception {
        public KeyAlreadyExistsException(String msg) {
            super(msg);
        }
    }
}
