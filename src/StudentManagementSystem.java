import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Student {
    private String studentID;
    private String name;
    private int age;
    private List<String> courses;

    public Student(String studentID, String name, int age) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Courses: " + courses);
    }

    public void updateAge(int newAge) {
        this.age = newAge;
    }

    public int getAge() {
        return age;
    }

    public String getStudentID() {
        return studentID;
    }

    public List<String> getCourses() {
        return courses;
    }
}

public class StudentManagementSystem {
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        students.add(new Student("1", "Akhil", 20) {{
            addCourse("Computer Science");
            addCourse("Javascript");
        }});
    
        students.add(new Student("2", "Raman", 22) {{
            addCourse("Computer Science");
            addCourse("Data Structures");
        }});
    
        students.add(new Student("3", "Rashika", 21) {{
            addCourse("Project Management");
            addCourse("Business Management");
            addCourse("German");
        }});
        boolean running = true;
        while (running) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Find Students Enrolled in a Course");
            System.out.println("4. Update Student's Age");
            System.out.println("5. Calculate Average Age of All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    findStudentsInCourse();
                    break;
                case 4:
                    updateStudentAge();
                    break;
                case 5:
                    calculateAverageAge();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.println("\nEnter student details:");
        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 

        Student student = new Student(studentID, name, age);
        System.out.print("Enter course (or 'done' to finish adding courses): ");
        String course;
        while (!(course = scanner.nextLine()).equalsIgnoreCase("done")) {
            student.addCourse(course);
            System.out.print("Enter another course (or 'done'): ");
        }
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void displayAllStudents() {
        System.out.println("\nDetails of all students:");
        students.forEach(Student::displayDetails);
    }

    private static void findStudentsInCourse() {
        System.out.print("\nEnter course to find students: ");
        String courseToFind = scanner.nextLine();

        List<Student> studentsInCourse = students.stream()
                .filter(student -> student.getCourses().contains(courseToFind))
                .collect(Collectors.toList());

        if (studentsInCourse.isEmpty()) {
            System.out.println("No students found in " + courseToFind);
        } else {
            System.out.println("Students enrolled in " + courseToFind + ":");
            studentsInCourse.forEach(Student::displayDetails);
        }
    }

    private static void updateStudentAge() {
        System.out.print("\nEnter student ID to update age: ");
        String studentIDToUpdate = scanner.nextLine();
        System.out.print("Enter new age: ");
        int updatedAge = scanner.nextInt();
        scanner.nextLine();

        students.stream()
                .filter(student -> student.getStudentID().equals(studentIDToUpdate))
                .findFirst()
                .ifPresent(student -> {
                    student.updateAge(updatedAge);
                    System.out.println("Age updated successfully!");
                });
    }

    private static void calculateAverageAge() {
        double averageAge = students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);

        System.out.println("\nAverage age of all students: " + averageAge);
    }
}
