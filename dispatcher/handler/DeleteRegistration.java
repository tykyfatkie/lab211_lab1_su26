package dispatcher.handler;

import business.Students;
import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class DeleteRegistration {

    public static void execute(Inputter ndl, Students rl) {
        System.out.println("\n--- Delete Registration ---");
        String id = ndl.inputAndLoop("Enter Student ID: ", Acceptable.STU_ID_VALID).toUpperCase();
        Student s = rl.searchById(id);
        if (s == null) {
            System.out.println("This student has not registered yet.");
            return;
        }

        System.out.println("Student Details:");
        System.out.println("Student ID : " + s.getId());
        System.out.println("Name       : " + s.getName());
        System.out.println("Phone      : " + s.getPhone());
        System.out.println("Mountain   : " + s.getMountainCode());
        System.out.printf("Fee        : %,.0f%n", s.getTuitionFee());

        String confirm = ndl.getString("Are you sure you want to delete this registration? (Y/N): ");
        if (confirm.trim().equalsIgnoreCase("Y")) {
            rl.delete(id);
            System.out.println("The registration has been successfully deleted.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}