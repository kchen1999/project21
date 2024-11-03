package deque;

public class ArrayDeque<T> {
    private int size;
    private T [] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T item) {
        size = size + 1;
    }

    public void addLast(T item) {
        size = size + 1;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        return;
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        else if(size == 1) {

        }

    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
    }

    public T get(int index) {
    }


    public static void main(String[] args) {

    }
}