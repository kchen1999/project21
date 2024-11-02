package deque;

public class LinkedListDeque<T> {
    private int size;
    private IntNode sentinel;

    private class IntNode {
        T item;
        IntNode next;
        IntNode prev;
    }
    public LinkedListDeque() {
        size = 0;
    }

    public void addFirst(T item) {
        return;
    }

    public void addLast(T item) {
        return;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        return;
    }

    public T removeFirst() {
        return null;
    }

    public T removeLast() {
        return null;
    }

    public T get(int index) {
        return null;
    }

    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {

    }
}