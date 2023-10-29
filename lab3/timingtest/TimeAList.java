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

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> items = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int st = 1000;
        for (int i = 0; i < 16; ++i) {
            Stopwatch sw = new Stopwatch();
            AList<Integer> cur = new AList<>();
            Ns.addLast(st);
            for (int j = 0; j < st; ++j) {
                cur.addLast(1);
            }
            double timeInMicrosSeconds = sw.elapsedTime();
            items.addLast(timeInMicrosSeconds);
            opCounts.addLast(st);
            st *= 2;
        }
        printTimingTable(Ns, items, opCounts);
    }
}
