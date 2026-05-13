package dispatcher.handler;

import business.Students;

public class ShowStatistics {

    public static void execute(Students rl) {
        System.out.println("\n--- Statistics of Registration by Mountain Peak ---");
        rl.statisticalizeByMountainPeak();
    }
}