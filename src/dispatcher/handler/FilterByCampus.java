package dispatcher.handler;

import business.Students;
import model.Student;
import tools.Acceptable;
import tools.Inputter;
import java.util.List;

public class FilterByCampus {

    public static void execute(Inputter ndl, Students rl) {
        System.out.println("\n--- Filter by Campus ---");
        System.out
                .println("Campus codes: CE (Can Tho) | DE (Da Nang) | HE (Ha Noi) | SE (Ho Chi Minh) | QE (Quy Nhon)");
        String campusCode = ndl.getString("Enter campus code: ").trim().toUpperCase();

        if (!Acceptable.isValid(campusCode, Acceptable.CAMPUS_VALID)) {
            System.out.println("Invalid campus code.");
            return;
        }

        List<Student> list = rl.filterByCampusCode(campusCode);
        if (list.isEmpty()) {
            System.out.println("No students have registered under this campus.");
        } else {
            System.out.println("Registered Students Under Campus (" + campusCode + "):");
            rl.showAll(list);
        }
    }
}