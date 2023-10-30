package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFront;
    private int nextBack;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        //for inner user of circular array representation
        nextFront = 3;
        nextBack = 4;
    }

    private void resize(int newCapacity) {
        assert  newCapacity > size : "new capacity must be larger than the current items count";
        T[] newItems = (T[]) new Object[newCapacity];
        int j = (newCapacity - size) / 2;
        int i = (nextFront + 1) % items.length, len = 0;
        for (; len < size; ++i, ++len, ++j) {
            newItems[j] = items[i % items.length];
        }
        items = newItems;
        nextBack = j;
        nextFront = ((newCapacity - size) / 2 - 1 + items.length) % items.length;
    }
    @Override
    public void addFirst(T item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[nextFront] = item;
        nextFront = (nextFront - 1 + items.length) % items.length;
        ++size;
    }

    @Override
    public void addLast(T item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[nextBack] = item;
        nextBack = (nextBack + 1) % items.length;
        ++size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i) + ", ");
        }
    }
    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        T first = get(0);
        items[(nextFront + 1) % items.length] = null;
        nextFront = (nextFront  + 1) % items.length;
        --size;
        if (size < (items.length / 4) && size >= 4) {
            resize(items.length / 4);
        }
        return first;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        T last = get(size - 1);
        items[(nextBack - 1 + items.length) % items.length] = null;
        nextBack = (nextBack - 1 + items.length) % items.length;
        --size;
        if (size < (items.length / 4) && size >= 4) {
            resize(items.length / 4);
        }
        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFront + 1 + index) % items.length];
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> that = (Deque<T>) o;
        if (that.size() != this.size) {
            return false;
        }
        for (int i = 0; i < that.size(); ++i) {
            if (!that.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int cur = 0;
        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(cur++);
        }
    }
}
