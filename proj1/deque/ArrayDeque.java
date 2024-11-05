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
        items[nextLast] = item;
        if(nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast = nextLast + 1;
        }
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
        for(int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
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
        return items[index];
    }


    public static void main(String[] args) {

    }
}