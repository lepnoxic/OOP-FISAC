import java.util.Arrays;
import java.util.Scanner;

class SortUtility <T extends Comparable<T>>{
    T[] data;

    public SortUtility(T[] data) {
        this.data = data;
    }

    public void sort() {
        Arrays.sort(data);
    }

    public void display() {
        for (T item : data) {
            System.out.println(item);
        }
    }
}

class Employee implements Comparable<Employee> {
    String name;
    int YoE;

    public Employee(String name, int YoE) {
        this.name = name;
        this.YoE = YoE;
    }

    public int getYoE() {
        return YoE;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.YoE, other.YoE);
    }

    @Override
    public String toString() {
        return "Employee: " + name + " YoE:" + YoE;
    }
}

class Student implements Comparable<Student> {
    String name;
    String grade;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student other) {
        return this.grade.compareTo(other.grade);
    }

    @Override
    public String toString() {
        return "Student: " + name + " grade=" + grade;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        Employee[] employees = new Employee[numEmployees];

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter employee name: ");
            String name = scanner.next();
            System.out.print("Enter years of experience (YoE): ");
            int YoE = scanner.nextInt();
            employees[i] = new Employee(name, YoE);
        }

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        Student[] students = new Student[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.next();
            System.out.print("Enter grade: ");
            String grade = scanner.next();
            students[i] = new Student(name, grade);
        }

        SortUtility<Employee> employeeSorter = new SortUtility<>(employees);
        employeeSorter.sort();
        System.out.println("Sorted Employees:");
        employeeSorter.display();

        SortUtility<Student> studentSorter = new SortUtility<>(students);
        studentSorter.sort();
        System.out.println("\nSorted Students:");
        studentSorter.display();

        scanner.close();
    }
}



