package dispatcher.handler;

import business.Mountains;
import business.Students;
import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class UpdateRegistration {
    private static final double BASE_FEE = 6000000;
    private static final double DISCOUNT_RATE = 0.35;

    public static void execute(Inputter ndl, Students rl, Mountains mountains) {
        System.out.println("\n--- Update Registration ---");
        String id = ndl.inputAndLoop("Enter Student ID: ", Acceptable.STU_ID_VALID).toUpperCase();
        Student s = rl.searchById(id);
        if (s == null) {
            System.out.println("This student has not registered yet.");
            return;
        }

        System.out.println("Current: " + s.toString());
        System.out.println("(Press Enter to keep current value)");

        String name = ndl.getString("New Name [" + s.getName() + "]: ").trim();
        if (!name.isEmpty()) {
            if (Acceptable.isValid(name, Acceptable.NAME_VALID))
                s.setName(name);
            else
                System.out.println("Invalid name. Keeping old value.");
        }

        String phone = ndl.getString("New Phone [" + s.getPhone() + "]: ").trim();
        if (!phone.isEmpty()) {
            if (Acceptable.isValid(phone, Acceptable.PHONE_VALID)) {
                s.setPhone(phone);
                double fee = BASE_FEE;
                if (Acceptable.isValid(phone, Acceptable.VIETTEL_VALID)
                        || Acceptable.isValid(phone, Acceptable.VNPT_VALID)) {
                    fee = BASE_FEE * (1 - DISCOUNT_RATE);
                }
                s.setTuitionFee(fee);
            } else
                System.out.println("Invalid phone. Keeping old value.");
        }

        String email = ndl.getString("New Email [" + s.getEmail() + "]: ").trim();
        if (!email.isEmpty()) {
            if (Acceptable.isValid(email, Acceptable.EMAIL_VALID))
                s.setEmail(email);
            else
                System.out.println("Invalid email. Keeping old value.");
        }

        String mountainCode = ndl.getString("New Mountain Code [" + s.getMountainCode() + "]: ").trim();
        if (!mountainCode.isEmpty()) {
            if (mountains.isValidMountainCode(mountainCode))
                s.setMountainCode(mountainCode);
            else
                System.out.println("Invalid mountain code. Keeping old value.");
        }

        rl.update(s);
        System.out.println("Registration updated successfully.");
    }
}