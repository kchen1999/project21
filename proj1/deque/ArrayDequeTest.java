package deque;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.Stopwatch;

public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertTrue("A newly initialised array deque should be empty", ad1.isEmpty());
        ad1.addFirst("Hello");
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }
    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", ad1.isEmpty());

    }
    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }
    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();

    }
    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

    }
    @Test
    /* check if size is correct after adding 8 items using add first */
    public void addEightItemsAddFirstTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        assertEquals(8, ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }
    @Test
    /* check if size is correct after adding 8 items using add last */
    public void addEightItemsAddLastTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        assertEquals(8, ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }
    @Test
    /* check if size and get is correct after adding items using add first last */
    public void addFirstAndLastTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");
        assertEquals(8, ad1.size());
        assertEquals("b", ad1.get(3));
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /* Check if iterator works */
    public void checkIterator() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");
        for (String s : ad1) {
            System.out.println(s);
        }
    }

    @Test
    /*Check equals works */
    public void checkEquals() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad2.addLast("a");
        ad2.addLast("b");
        ad2.addFirst("c");
        ad2.addLast("d");
        ad2.addLast("e");
        ad2.addFirst("f");
        assertEquals("expected ad1 to be equal to ad2", true, ad1.equals(ad2));
        ad2.addFirst("k");
        assertEquals("expected ad1 to be equal to ad2", true, !ad1.equals(ad2));

    }

    @Test
    /* check if resize works when adding additional element to full array */
    public void resizeToBiggerArrayTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");
        ad1.addLast("i");
        ad1.addLast("j");
        ad1.addFirst("k");
        assertEquals(11, ad1.size());
        assertEquals("k", ad1.get(0));
        assertEquals("f", ad1.get(1));
        assertEquals("j", ad1.removeLast());
        assertEquals("i", ad1.removeLast());
        assertEquals(9, ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }
    @Test
    /* check if resize works when usage factor is below 25% */
    public void resizeToSmallerArrayTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");
        ad1.addLast("i");
        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.addFirst("f");
        ad1.addLast("e");
        assertEquals(5, ad1.size());
        assertEquals("f", ad1.get(0));
        System.out.println("Printing out deque: ");
        ad1.printDeque();
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
        timeArrayConstruction();
    }

    public static void timeAddArray (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast(10000);
        for(int i = 0; i < nSize; i++) {
            ad1.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < 10000; i++) {
            ad1.addLast(6);
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);
        for(int i = 0; i < 10000; i++) {
            ad1.removeLast();
        }

    }

    public static void timeRemoveArray (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast((int) (nSize * 0.75));
        for(int i = 0; i < nSize; i++) {
            ad1.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < nSize * 0.75; i++) {
            ad1.removeLast();
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeRemoveArray1 (LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts, int nSize ) {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        Ns.addLast(nSize);
        opCounts.addLast((int) (nSize * 0.75 + 1));
        for(int i = 0; i < nSize; i++) {
            ad1.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < nSize * 0.75 + 1; i++) {
            ad1.removeLast();
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeArrayConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount = new LinkedListDeque<>();

        LinkedListDeque<Integer> Ns1 = new LinkedListDeque<>();
        LinkedListDeque<Double> times1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount1 = new LinkedListDeque<>();

        LinkedListDeque<Integer> Ns2 = new LinkedListDeque<>();
        LinkedListDeque<Double> times2 = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount2 = new LinkedListDeque<>();

        LinkedListDeque<Integer> Ns3 = new LinkedListDeque<>();
        LinkedListDeque<Double> times3 = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCount3 = new LinkedListDeque<>();
        int nSize = 512;

        for(int i = 0; i < 16; i++) {
            nSize = nSize * 2;
            timeAddArray(Ns, times, opCount, nSize);
        }
        System.out.println("Timing table for addLast with no resize");
        printTimingTable(Ns, times, opCount);

        nSize = 512;
        for(int i = 0; i < 16; i++) {
            nSize = nSize * 2 - 1;
            timeAddArray(Ns1, times1, opCount1, nSize);
        }
        System.out.println("Timing table for addLast with resize");
        printTimingTable(Ns1, times1, opCount1);

        nSize = 512;
        for(int i = 0; i < 19; i++) {
            nSize = nSize * 2;
            timeRemoveArray(Ns2, times2, opCount2, nSize);
        }
        System.out.println("Timing table for removeLast with no resize");
        printTimingTable(Ns2, times2, opCount2);

        nSize = 512;
        for(int i = 0; i < 19; i++) {
            nSize = nSize * 2;
            timeRemoveArray1(Ns3, times3, opCount3, nSize);
        }
        System.out.println("Timing table for removeLast with resize");
        printTimingTable(Ns3, times3, opCount3);

    }






}