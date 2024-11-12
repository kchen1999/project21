package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.introcs.StdRandom;

import java.lang.reflect.Array;

public class TestArrayDequeEC {

    public String printErrorMessage (ArrayDequeSolution<String> ads1) {
        String errorMessage = "";
        for(String s: ads1) {
            errorMessage += (s + "\n");
        }
        return errorMessage;
    }

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        ArrayDequeSolution<String> methodCalls = new ArrayDequeSolution<>();
        methodCalls.addLast("isEmpty()");
        assertEquals(printErrorMessage(methodCalls), sad1.isEmpty(), ads1.isEmpty());
        methodCalls.removeLast();
        methodCalls.addLast("size()");
        assertEquals(printErrorMessage(methodCalls), sad1.size(), ads1.size());
        methodCalls.removeLast();
        int N = 100000;
        for(int i = 0; i < N; i++) {
            int randValue = StdRandom.uniform(0, 100);
            int operationNumber = StdRandom.uniform(0, 6);
            if(operationNumber == 0) {
                sad1.addFirst(randValue);
                ads1.addFirst(randValue);
                methodCalls.addLast("addFirst(" + randValue + ")");
            }
            else if(operationNumber == 1) {
                sad1.addLast(randValue);
                ads1.addLast(randValue);
                methodCalls.addLast("addLast(" + randValue + ")");
            }
            else if(operationNumber == 2) {
                if(ads1.size() > 0) {
                    Integer result1 = sad1.removeFirst();
                    Integer result2 = ads1.removeFirst();
                    methodCalls.addLast("removeFirst()");
                    assertEquals(printErrorMessage(methodCalls), result1, result2);
                }
            }
            else if(operationNumber == 3) {
                if(ads1.size() > 0) {
                    Integer result1 = sad1.removeLast();
                    Integer result2 = ads1.removeLast();
                    methodCalls.addLast("removeLast()");
                    assertEquals(printErrorMessage(methodCalls), result1, result2);
                }
            }
            else if(operationNumber == 4) {
                methodCalls.addLast("size()");
                assertEquals(printErrorMessage(methodCalls), sad1.size(), ads1.size());
                sad1.addLast(randValue);
                ads1.addLast(randValue);
                methodCalls.addLast("addLast(" + randValue + ")");
            }
            else if(operationNumber == 5) {
                if(!ads1.isEmpty()) {
                    methodCalls.addLast("get(" + 0+ ")");
                    assertEquals(printErrorMessage(methodCalls), sad1.get(0), ads1.get(0));
                    methodCalls.addLast("get(" + (ads1.size() - 1) + ")");
                    assertEquals(printErrorMessage(methodCalls), sad1.get(ads1.size() - 1), ads1.get(ads1.size() - 1));
                }
                sad1.addFirst(randValue);
                ads1.addFirst(randValue);
                methodCalls.addLast("addFirst(" + randValue + ")");

            }
        }
    }
}
