package dispatcher;

import business.Mountains;
import business.Students;
import dispatcher.handler.*;
import tools.Acceptable;
import tools.Inputter;

public class Main {

    public static void main(String[] args) {
        int choice = 0;
        Inputter ndl = new Inputter();
        Students rl = new Students();
        Mountains mountains = new Mountains();

        do {
            printMenu();
            String input = ndl.getString("Enter your choice: ");
            if (!Acceptable.isValid(input, Acceptable.INTEGER_VALID)) {
                System.out.println("This function is not available.");
                continue;
            }
            choice = Integer.parseInt(input.trim());

            switch (choice) {
                case 1:
                    AddRegistration.execute(ndl, rl, mountains);
                    break;
                case 2:
                    UpdateRegistration.execute(ndl, rl, mountains);
                    break;
                case 3:
                    System.out.println("\n--- Registered Students ---");
                    rl.showAll();
                    break;
                case 4:
                    DeleteRegistration.execute(ndl, rl);
                    break;
                case 5:
                    SearchByName.execute(ndl, rl);
                    break;
                case 6:
                    FilterByCampus.execute(ndl, rl);
                    break;
                case 7:
                    ShowStatistics.execute(rl);
                    break;
                case 8:
                    rl.saveToFile();
                    break;
                case 9:
                    ExitProgram.execute(ndl, rl);
                    break;
                default:
                    System.out.println("This function is not available.");
            }
        } while (choice != 9);
    }

    private static void printMenu() {
        System.out.println("\n========= Mountain Hiking Challenge Registration =========");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search Participants by Name");
        System.out.println("6. Filter Data by Campus");
        System.out.println("7. Statistics of Registration Numbers by Location");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
        System.out.println("==========================================================");
    }
}