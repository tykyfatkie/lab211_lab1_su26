package business;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Students extends ArrayList<Student> {
    private String pathFile;
    private boolean isSaved;

    private static final String HEADER_TABLE = "|-----------------------------------------------------------------------|\n"
            +
            "| StudentID  | Name                 | Phone        | PeakCode   | Fee   |\n" +
            "|-----------------------------------------------------------------------|";
    private static final String FOOTER_TABLE = "|-----------------------------------------------------------------------|";

    public Students() {
        this.pathFile = "registrations.dat";
        this.isSaved = true;
        readFromFile();
    }

    public boolean isSaved() {
        return isSaved;
    }

    @Override
    public boolean add(Student x) {
        boolean result = super.add(x);
        isSaved = false;
        return result;
    }

    public void update(Student x) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equalsIgnoreCase(x.getId())) {
                this.set(i, x);
                isSaved = false;
                return;
            }
        }
    }

    public void delete(String id) {
        Student s = searchById(id);
        if (s != null) {
            this.remove(s);
            isSaved = false;
        }
    }

    public Student searchById(String id) {
        for (Student s : this) {
            if (s.getId().equalsIgnoreCase(id.trim()))
                return s;
        }
        return null;
    }

    public void searchByName(String name) {
        List<Student> found = new ArrayList<>();
        for (Student s : this) {
            if (s.getName().toLowerCase().contains(name.toLowerCase()))
                found.add(s);
        }
        if (found.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            showAll(found);
        }
    }

    public void showAll() {
        showAll(this);
    }

    public void showAll(List<Student> l) {
        if (l.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.println(HEADER_TABLE);
        for (Student i : l) {
            System.out.println("| " + i.toString());
        }
        System.out.println(FOOTER_TABLE);
    }

    public List<Student> filterByCampusCode(String campusCode) {
        List<Student> result = new ArrayList<>();
        for (Student s : this) {
            if (s.getId().toUpperCase().startsWith(campusCode.toUpperCase()))
                result.add(s);
        }
        return result;
    }

    public void statisticalizeByMountainPeak() {
        if (this.isEmpty()) {
            System.out.println("No registration data available.");
            return;
        }
        Statistics stats = new Statistics(this);
        stats.show();
    }

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            File f = new File(pathFile);
            if (!f.exists())
                return;
            fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                Student x = (Student) ois.readObject();
                super.add(x);
            }
            ois.close();
            this.isSaved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                }
        }
    }

    public void saveToFile() {
        if (this.isSaved)
            return;
        FileOutputStream fos = null;
        try {
            File f = new File(pathFile);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Student i : this) {
                oos.writeObject(i);
            }
            oos.close();
            this.isSaved = true;
            System.out.println("Registration data has been successfully saved to `registrations.dat`.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                }
        }
    }
}