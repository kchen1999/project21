package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAList (AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts, int nSize ) {
        AList<Integer> aList = new AList<>();
        Ns.addLast(nSize);
        opCounts.addLast(nSize * 1);
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < nSize; i++) {
            aList.addLast(5);
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);

    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();
        int nSize = 500;

        for(int i = 0; i < 8; i++) {
            nSize = nSize * 2;
            timeAList(Ns, times, opCount, nSize);
        }

        System.out.println("Timing table for addLast");
        printTimingTable(Ns, times, opCount);
    }
}
