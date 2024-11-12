package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private IntNode sentinel;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        IntNode p;
        LinkedListIterator() {
            p = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return p != null && p.item != null;
        }
        @Override
        public T next() {
            T tmp = p.item;
            p = p.next;
            return tmp;
        }
    }

    private class IntNode {
        T item;
        IntNode prev;
        IntNode next;

        IntNode(T i, IntNode p, IntNode n) {
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
        if (sentinel.next.next == null) {
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next.next.prev = sentinel.next;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
        if (sentinel.prev.prev == null) {
            sentinel.next = sentinel.prev;
        } else {
            sentinel.prev.prev.next = sentinel.prev;
        }
        size = size + 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item.toString() + " ");
            p = p.next;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T tmp = sentinel.next.item;
            sentinel.next = null;
            sentinel.prev = null;
            size = size - 1;
            return tmp;
        }
        T tmp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T tmp = sentinel.prev.item;
            sentinel.next = null;
            sentinel.prev = null;
            size = size - 1;
            return tmp;
        }
        T tmp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return tmp;
    }

    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        IntNode p = sentinel.next;
        int i = 0;
        while (p != null) {
            if (i == index) {
                return p.item;
            }
            i++;
            p = p.next;
        }
        return null;
    }

    private T getRecursiveHelper(int index, IntNode p, int i) {
        if (index == i) {
            return p.item;
        }
        return getRecursiveHelper(index, p.next, i + 1);
    }

    public T getRecursive(int index) {
        if (index + 1 > size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque d1 = (Deque) obj;
        if (size != d1.size()) {
            return false;
        }
        if(obj instanceof LinkedListDeque) {
            IntNode p = sentinel.next;
            for (int i = 0; i < size; i++) {
                if (!p.item.equals(d1.get(i))) {
                    return false;
                }
                p = p.next;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(d1.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
