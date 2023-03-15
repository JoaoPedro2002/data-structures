package br.dev.adinfinitum.structures.lists;

public class LinkedList<T> {
    private class Node {
        private final T data;
        private Node next, previous;
        public Node(T data) {
            this.data = data;
        }
    }

    private int size = 0;
    private Node first, last, pointer;

    public T getFirst() {
        return first.data;
    }

    public Node getLast() {
        return last;
    }

    public T get(int index) {
        if (size <= index) {
            throw new NullPointerException("NullPointer at " + index + "for size " + size);
        }
        pointer = first;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }

    public void add(T item) {
        Node newItem = new Node(item);
        if (first == null) {
            first = newItem;
        } else {
            last.next = newItem;
            newItem.previous = last;
        }
        last = newItem;
        size++;
    }

    public boolean remove(T item) {
        pointer = first;
        if (pointer == null) return false;
        while (pointer != null) {
            if (pointer.data.equals(item)) {
                removeItem(pointer);
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    private void removeItem(Node node) {
        if (size == 1) {
            first = null;
            last = null;
        } else if (node.equals(first)) {
            first = first.next;
            first.previous = null;
        } else if (node.equals(last)) {
            last = last.previous;
            last.next = null;
        } else {
            Node previous = node.previous;
            Node next = node.next;
            previous.next = next;
            next.previous = previous;
        }
        size--;
    }

    public int getSize() {
        return size;
    }
}
