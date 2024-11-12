package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int index;
        int count;
        ArrayDequeIterator() {
            index = (nextFirst + 1) % items.length;
            count = 0;
        }
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T tmp = items[index];
            index += 1;
            count += 1;
            if (index == items.length) {
                index = 0;
            }
            return tmp;
        }
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast = nextLast + 1;
        }
        size = size + 1;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        int currentFirst = (nextFirst + 1) % items.length;
        int currentLast = (nextLast - 1) % items.length;
        if (currentLast > currentFirst) {
            System.arraycopy(items, currentFirst, a, capacity / 2, size);
        } else {
            System.arraycopy(items, currentFirst, a ,capacity / 2,items.length - currentFirst);
            System.arraycopy(items, 0, a, capacity / 2 + items.length - currentFirst, currentLast
                    + 1);
        }

        nextFirst = capacity / 2 - 1;
        nextLast = 0;
        items = a;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

        for (int i = 0; i < size; i++) {
            System.out.print(items[(nextFirst + 1 + i) % items.length] + " ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (double) (size - 1) / (double) items.length < 0.25) {
            resize(items.length / 2);
        }
        T tmp;
        if (nextFirst == items.length - 1) {
            tmp = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            tmp = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = nextFirst + 1;
        }
        size = size - 1;
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (double) (size - 1) / (double) items.length < 0.25) {
            resize(items.length / 2);
        }
        T tmp;
        if (nextLast == 0) {
            tmp = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            tmp = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        }
        size = size - 1;
        return tmp;
    }

    public T get(int index) {
        if (index > items.length - 1) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque ad1 = (Deque) obj;
        if (size != ad1.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(ad1.get(i))) {
                return false;
            }
        }
        return true;
    }
}
