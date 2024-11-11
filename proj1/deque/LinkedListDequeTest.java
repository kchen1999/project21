package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    /* Check whether get method works */
    public void getIndexTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addFirst("f");
        lld1.addLast("g");
        lld1.addLast("h");
        assertEquals("Should have the same value", "b", lld1.get(3));
        assertEquals("Should have the same value", "b", lld1.getRecursive(3));
        assertEquals("Should have the same value", "f", lld1.get(0));
        assertEquals("Should have the same value", "f", lld1.getRecursive(0));
        assertEquals("Should have the same value", "h", lld1.get(7));
        assertEquals("Should have the same value", "h", lld1.getRecursive(7));
    }

    @Test
    /* Check if iterator works */
    public void checkIterator() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addFirst("f");
        lld1.addLast("g");
        lld1.addLast("h");
        for (String s : lld1) {
            System.out.println(s);
        }
    }

    @Test
    /*Check equals works */
    public void checkEquals() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addFirst("f");
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        lld2.addLast("a");
        lld2.addLast("b");
        lld2.addFirst("c");
        lld2.addLast("d");
        lld2.addLast("e");
        lld2.addFirst("f");
        assertEquals("expected lld1 to be equal to lld1", true, lld1.equals(lld2));
        lld2.addFirst("k");
        assertEquals("expected lld1 to not be equal to lld1", false, lld1.equals(lld2));
    }

    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeLinkedListConstruction();
    }

    public static void timeAddLastLinkedList (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast(nSize * 1);
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < nSize; i++) {
            lld1.addLast(5);
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeRemoveLastLinkedList (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast(10000);
        for(int i = 0; i < nSize; i++) {
            lld1.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < 10000; i++) {
            lld1.removeLast();
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeIteratorLinkedList (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast(nSize * 1);
        for(int i = 0; i < nSize; i++) {
            lld1.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for (Integer i : lld1) {
            lld1.size();
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeLinkedListConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount = new LinkedListDeque<>();

        LinkedListDeque<Integer> Ns1 = new LinkedListDeque<>();
        LinkedListDeque<Double> times1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount1 = new LinkedListDeque<>();

        LinkedListDeque<Integer> Ns2 = new LinkedListDeque<>();
        LinkedListDeque<Double> times2 = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount2 = new LinkedListDeque<>();
        int nSize = 500;

        for(int i = 0; i < 8; i++) {
            nSize = nSize * 2;
            timeAddLastLinkedList(Ns, times, opCount, nSize);
        }
        System.out.println("Timing table for addLast");
        printTimingTable(Ns, times, opCount);

        nSize = 500;
        for(int i = 0; i < 8; i++) {
            nSize = nSize * 2;
            timeRemoveLastLinkedList(Ns1, times1, opCount1, nSize);
        }
        System.out.println("Timing table for GetLast");
        printTimingTable(Ns1, times1, opCount1);

        nSize = 500;
        for(int i = 0; i < 8; i++) {
            nSize = nSize * 2;
            timeIteratorLinkedList(Ns2, times2, opCount2, nSize);
        }
        System.out.println("Timing table for Iterator");
        printTimingTable(Ns2, times2, opCount2);
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque <Integer> l1 = new LinkedListDeque<>();
        ArrayDeque <Integer> l2 = new ArrayDeque<>();

        int N = 100000;
        for(int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 6);
            int randVal = StdRandom.uniform(0, 100);
            if(operationNumber == 0) {
                l1.addFirst(randVal);
                l2.addFirst(randVal);
            }
            else if(operationNumber == 1) {
                l1.addLast(randVal);
                l2.addLast(randVal);
            }
            else if(operationNumber == 2) {
                assertEquals(l1.size(), l2.size());
                l1.addFirst(randVal);
                l2.addFirst(randVal);
            }
            else if(operationNumber == 3) {
                assertEquals(l1.removeFirst(), l2.removeFirst());
            }
            else if(operationNumber == 4) {
                assertEquals(l1.removeLast(), l2.removeLast());
            }
            else if(operationNumber == 5) {
                if(l1.size() > 0) {
                    int index = StdRandom.uniform(0, l1.size());
                    assertEquals(l1.get(index), l2.get(index));
                    assertEquals(l1.getRecursive(index), l2.get(index));
                }
            }
        }

    }


}
