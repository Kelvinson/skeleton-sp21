package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void test1() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(Comparator.comparingInt(x -> (int)x));
        maxArrayDeque.addFirst(1);
        maxArrayDeque.addFirst(2);
        maxArrayDeque.addFirst(4);
        int max = maxArrayDeque.max();
        assertEquals(max, 4);
    }

    @Test
    public void test2() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(Comparator.comparingInt(x -> (int)x));
        maxArrayDeque.addFirst(2);
        maxArrayDeque.addFirst(2);
        maxArrayDeque.addFirst(2);
        int max = maxArrayDeque.max();
        assertEquals(max, 2);
    }

    @Test
    public void test3() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(Comparator.comparingInt(x -> (int)x));
//        int max = maxArrayDeque.max();
        assertNull(maxArrayDeque.max());
    }
}
