package dispatcher.handler;

import business.Students;
import tools.Inputter;

public class ExitProgram {

    public static void execute(Inputter ndl, Students rl) {
        if (!rl.isSaved()) {
            String ans = ndl.getString("Do you want to save the changes before exiting? (Y/N): ");
            if (ans.trim().equalsIgnoreCase("Y")) {
                rl.saveToFile();
            } else {
                String confirm = ndl
                        .getString("You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
                if (!confirm.trim().equalsIgnoreCase("Y")) {
                    System.out.println("Exit cancelled.");
                    return;
                }
            }
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }
}