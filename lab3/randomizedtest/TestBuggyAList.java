package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> good = new AListNoResizing<>();
        BuggyAList<Integer> bad = new BuggyAList<>();
        good.addLast(4);
        bad.addLast(4);
        good.addLast(5);
        bad.addLast(5);
        good.addLast(6);
        bad.addLast(6);
        assertEquals(good.removeLast(), bad.removeLast());
        assertEquals(good.removeLast(), bad.removeLast());
        assertEquals(good.removeLast(), bad.removeLast());
    }

    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> buggyAList = new BuggyAList<>();

    @Test
    public void randomizedTest() {
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyAList.addLast(randVal);
//                System.out.println("L: addLast(" + randVal + ")");
//                System.out.println("Buggy L: addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
//                System.out.println("L size: " + size);
//                System.out.println("Buggy L size: " + size);
                assertEquals(L.size(), buggyAList.size());
            } else if (L.size() == 0 || buggyAList.size() == 0) {
                continue;
            } else if (operationNumber == 2) {
                int last = L.getLast();
                int last1 = buggyAList.getLast();
                assertEquals(last, last1);
//                System.out.println("L retrive the last from list: " + last);
//                System.out.println("Buggy L retrive the last from list: " + last1);
            } else {
                int last = L.removeLast();
                int last1 = buggyAList.removeLast();
                assertEquals(last, last1);
//                System.out.println("L remove from the last from list: " + last);
//                System.out.println("Buggy L remove from the last from list: " + last1);
            }
        }
    }
}
