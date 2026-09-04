import java.io.*;
import java.util.*;
public class StudentManagement {
    static final int ADD = 1, RESULT = 2, LIST = 3, EXIT = 4;
    private final StudentRepository repository;
    public StudentManagement(StudentRepository repository) {
        this.repository = repository;
    }
    public void addStudent(Student student) throws IOException {
        repository.save(student);
    }
    public void showResult(Student student) {
        System.out.println("Student: " + student);
        System.out.println("Average: " + student.getAverage());
        System.out.println("Grade: " + student.getGrade());
    }
    public void listStudents() {
        List<Student> students = repository.findAll();
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : students)
            System.out.println(s);
    }
    private static Student inputStudent(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Math: ");
        int m = Integer.parseInt(sc.nextLine());
        System.out.print("Science: ");
        int s = Integer.parseInt(sc.nextLine());
        System.out.print("English: ");
        int e = Integer.parseInt(sc.nextLine());
        return new Student(name, id, m, s, e);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement app =
            new StudentManagement(new FileStudentRepository("students.txt"));
        while (true) {
            System.out.println("\n1. Add 2. Result 3. List 4. Exit");
            System.out.print("Choice: ");
            if (!sc.hasNextLine()) break;
            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == EXIT) break;
                if (choice == ADD) {
                    app.addStudent(inputStudent(sc));
                    System.out.println("Student added.");
                } else if (choice == RESULT) {
                    app.showResult(inputStudent(sc));
                } else if (choice == LIST) {
                    app.listStudents();
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Save failed: " + e.getMessage());
            }
        }
        sc.close();
    }
}
