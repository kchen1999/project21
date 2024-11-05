package deque;

public class ArrayDeque<T> {
    private int size;
    private T [] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        nextFirst = items.length / 2;
        nextLast = nextFirst + 1;
    }

    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if(nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        else {
            nextFirst = nextFirst - 1;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if(nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast = nextLast + 1;
        }
        size = size + 1;
    }

    public void resize(int capacity) {
        T[] tmp = (T []) new Object[size];
        for(int i = 0; i < size; i++) {
            tmp[i] = items[(i + nextFirst + 1) % items.length];
        }
        T[] a = (T []) new Object[capacity];
        System.arraycopy(tmp, 0, a, capacity / 2, size);
        nextFirst = capacity / 2 - 1;
        nextLast = 0;
        items = a;
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

        for(int i = 0; i < size; i++) {
            System.out.print(items[(nextFirst + 1 + i) % items.length] + " ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        if(items.length >= 16 && (double) (size - 1) / (double) items.length < 0.25) {
            resize(size * 2);
        }
        T tmp;
        if(nextFirst == items.length - 1) {
            tmp = items[0];
            items[0] = null;
            nextFirst = 0;
        }
        else {
            tmp = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = nextFirst + 1;
        }
        size = size - 1;
        return tmp;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        if(items.length >= 16 && (double) (size - 1) / (double) items.length < 0.25) {
            resize(size * 2);
        }
        T tmp;
        if(nextLast == 0) {
            tmp = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        }
        else {
            tmp = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        }
        size = size - 1;
        return tmp;
    }

    public T get(int index) {
        if(index > items.length - 1) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }


    public static void main(String[] args) {

    }
}