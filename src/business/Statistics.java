package business;

import model.StatisticalInfo;
import model.Student;
import java.util.HashMap;
import java.util.List;

public class Statistics extends HashMap<String, StatisticalInfo> {
    private static final String HEADER_TABLE = "|--------------------------------------------------|\n" +
            "| Peak Name  | Number of Participants  | Total Cost |\n" +
            "|-----------------------------------------------------|";
    private static final String FOOTER_TABLE = "|-----------------------------------------------------|";

    public Statistics() {
        super();
    }

    public Statistics(List<Student> list) {
        super();
        statisticalize(list);
    }

    public List<StatisticalInfo> statisticalize(List<Student> list) {
        for (Student i : list) {
            if (this.containsKey(i.getMountainCode())) {
                StatisticalInfo x = this.get(i.getMountainCode());
                x.setNumOfStudent(x.getNumOfStudent() + 1);
                x.setTotalCost(x.getTotalCost() + i.getTuitionFee());
            } else {
                StatisticalInfo z = new StatisticalInfo(i.getMountainCode(), 1, i.getTuitionFee());
                this.put(i.getMountainCode(), z);
            }
        }
        return new java.util.ArrayList<>(this.values());
    }

    public void show() {
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo i : this.values()) {
            System.out.println("| " + i.toString());
        }
        System.out.println(FOOTER_TABLE);
    }
}