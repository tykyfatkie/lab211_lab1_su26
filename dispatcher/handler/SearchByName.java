package dispatcher.handler;

import business.Students;
import tools.Inputter;

public class SearchByName {

    public static void execute(Inputter ndl, Students rl) {
        System.out.println("\n--- Search by Name ---");
        String name = ndl.getString("Enter name or partial name: ");
        rl.searchByName(name);
    }
}