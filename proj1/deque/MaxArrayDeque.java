package deque;

import java.util.Comparator;

public class MaxArrayDeque <T> extends ArrayDeque<T> {
    Comparator<T> c;

    public static class MaxComparator<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T ad1, T ad2) {
            return ad1.compareTo(ad2);
        }
    }

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    public T max() {
        return max(c);
    }

    public T max(Comparator<T> c) {
        if(size() == 0) {
            return null;
        }
        T max = get(0);
        for(T item: this) {
            if(c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

}