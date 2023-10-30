package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{
    private class LinkedListNode<T> {
        T element;
        LinkedListNode<T> prev, next;
        public LinkedListNode() {
            this.element = null;
        }
        public LinkedListNode(T element) {
            this.element = element;
        }
        public T getElement() {
            return element;
        }
    }

    private LinkedListNode<T> front, back;
    private int size;
    @Override
    public void addFirst(T item) {
        //assert item not null;
        LinkedListNode<T> node = new LinkedListNode<T>(item);
        LinkedListNode<T> first = front.next;
        front.next = node;
        node.prev = front;
        first.prev = node;
        node.next = first;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        LinkedListNode<T> node = new LinkedListNode<T>(item);
        LinkedListNode<T> last = back.prev;
        last.next = node;
        node.prev = last;
        back.prev = node;
        node.next = back;
        size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i) + ",");
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        LinkedListNode<T> first = front.next;
        T element = first.element;
        front.next = first.next;
        first.next.prev = front;
        first = null;
        --size;
        return element;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        LinkedListNode<T> last = back.prev;
        T element = last.element;
        back.prev = last.prev;
        last.prev.next = back;
        last = null;
        --size;
        return element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        LinkedListNode<T> st = front;
        while (index-- > 0) {
            st = st.next;
        }
        T ans = st.next.element;
        st = null;
        return ans;
    }

    private T getHelper(LinkedListNode<T> node, int idx) {
        if (node == null || idx < 0) return null;
        if (idx == 0) return node.getElement();
        return getHelper(node.next, idx - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getHelper(front.next, index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> that = (LinkedListDeque<T>)o;
        if (that.size() != this.size) return false;
        for (int i = 0; i < that.size; ++i) {
            if (!that.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
    public LinkedListDeque() {
        front = new LinkedListNode<>();
        back = new LinkedListNode<>();
        front.next = back;
        back.prev = front;
        size = 0;
    }

    public Iterator<T> iterator() {
        return null;
    }
}
