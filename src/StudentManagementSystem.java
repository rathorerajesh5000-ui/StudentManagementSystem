
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {

    private int id;
    private String name;
    private int age;
    private String course;

    // Constructor
    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Age: " + age +
                " | Course: " + course;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static final String FILE_NAME = "data/students.txt";

    public static void main(String[] args) {

        loadStudents();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Save & Exit");

            System.out.print("Enter your choice: ");

            try {

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        deleteStudent();
                        break;

                    case 5:
                        saveStudents();
                        System.out.println("Data saved successfully.");
                        System.out.println("Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {

                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    // Add Student
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Check duplicate ID
        for (Student student : students) {

            if (student.getId() == id) {

                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        Student student = new Student(id, name, age, course);

        students.add(student);

        System.out.println("Student added successfully!");
    }

    // View Students
    static void viewStudents() {

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println("\n------ Student List ------");

        for (Student student : students) {

            System.out.println(student);
        }
    }

    // Search Student
    static void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student student : students) {

            if (student.getId() == id) {

                System.out.println("\nStudent Found!");
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete Student
    static void deleteStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student student : students) {

            if (student.getId() == id) {

                students.remove(student);

                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Save students to file
    static void saveStudents() {

        try {

            File folder = new File("data");

            if (!folder.exists()) {

                folder.mkdir();
            }

            FileWriter writer = new FileWriter(FILE_NAME);

            for (Student student : students) {

                writer.write(
                        student.getId() + "," +
                                student.getName() + "," +
                                student.getAge() + "," +
                                student.getCourse() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error while saving data.");
        }
    }

    // Load students from file
    static void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String course = data[3];

                students.add(
                        new Student(id, name, age, course)
                );
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("Error while loading data.");
        }
    }
}
