package ru.niatomi.util.cache.Impl;

import ru.niatomi.util.cache.Cache;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author niatomi
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private HashMap<K, Node<K, V>> cache = new HashMap<>();
    private Node<K, V> head, tail;

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;

    public LRUCache() {
        setCapacity(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity) {
        setCapacity(capacity);
    }

    public void setCapacity(int capacity) {
        checkCapacity(capacity);
        for (int i = cache.size(); i > capacity; i--) {
            Node<K, V> evicted = evict();
            cache.remove(evicted.getKey());
        }
        this.capacity = capacity;
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер кэша должен быть больше чем 0");
        }
    }

    private Node<K, V> evict() {
        if (head == null) {
            throw new AssertionError("Кэш пустой, удалять нечего");
        }
        Node<K, V> evicted = head;
        head = evicted.getNext();
        head.setPrevious(null);
        evicted.setNext(null);
        return evicted;
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> existing = cache.get(key);
            existing.setValue(value);
            moveNodeToLast(existing);
            return;
        }
        Node<K, V> newNode;
        if (cache.size() == capacity) {
            newNode = evict();
            cache.remove(newNode.getKey());
        } else {
            newNode = new Node<>();
        }
        newNode.setKey(key);
        newNode.setValue(value);
        addNewNode(newNode);
        cache.put(key, newNode);
    }

    private void moveNodeToLast(Node<K, V> node) {
        if (tail == node) {
            return;
        }
        Node<K, V> previous = node.getPrevious();
        Node<K, V> next = node.getNext();
        if (previous != null) {
            previous.setNext(next);
        }
        if (next != null) {
            next.setPrevious(previous);
        }
        if (head == node) {
            head = next;
        }
        tail.setNext(node);
        node.setPrevious(tail);
        node.setNext(null);
        tail = node;
    }

    private void addNewNode(Node<K, V> node) {
        if (cache.isEmpty()) {
            head = tail = node;
            return;
        }
        tail.setNext(node);
        node.setPrevious(tail);
        node.setNext(null);
        tail = node;
    }

    @Override
    public Optional<V> get(K key) {
        if (!cache.containsKey(key)) {
            return Optional.empty();
        }
        Node<K, V> node = cache.get(key);
        moveNodeToLast(node);
        return Optional.of(node.getValue());
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

}
