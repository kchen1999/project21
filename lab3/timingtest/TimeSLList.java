package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeSLList (AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts, int nSize ) {
        SLList<Integer> sList = new SLList<>();
        Ns.addLast(nSize);
        opCounts.addLast(10000);
        for(int i = 0; i < nSize; i++) {
            sList.addLast(5);
        }
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < 10000; i++) {
            sList.getLast();
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();
        int nSize = 500;

        for(int i = 0; i < 8; i++) {
            nSize = nSize * 2;
            timeSLList(Ns, times, opCount, nSize);
        }

        System.out.println("Timing table for addLast");
        printTimingTable(Ns, times, opCount);
    }

}
