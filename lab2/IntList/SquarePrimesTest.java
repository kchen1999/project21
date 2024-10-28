package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(2, 14, 15, 17, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 14 -> 15 -> 289 -> 169", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple3() {
        IntList lst = IntList.of(14, 15, 5, 3, 2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 25 -> 9 -> 4", lst.toString());
        assertTrue(changed);
    }
}
