package deque;

public class LinkedListDeque<T> {
    private int size;
    private IntNode sentinel;

    private class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null, null);
    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1; 
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