import java.util.Scanner;

class NegativeMarkException extends Exception {
    public NegativeMarkException(String message) {
        super(message);
    }
}

class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super(message);
    }
}

interface Mark{
    void dispMark();
}

class Student implements Mark{
    String name;
    String regNo;
    String category;
    int[] marks;
    double average;
    char grade;

    Student(String name, String regNo, String category) {
        this.name = name;
        this.regNo = regNo;
        this.category = category;
        this.marks = new int[3];
        this.average = 0.0;
        this.grade = ' ';
    }

    void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter marks for " + name + " (" + category + "):");

        for (int i = 0; i < 3; i++) {
            try {
                System.out.print("Subject " + (i + 1) + ": ");
                int mark = scanner.nextInt();
                if (mark < 0) {
                    throw new NegativeMarkException("Negative mark is not allowed");
                } else if (mark < 0 || mark > 100) {
                    throw new OutOfRangeException("Mark is out of range (0-100)");
                } else {
                    marks[i] = mark;
                    average += mark;
                }
            }   
            catch (NegativeMarkException | OutOfRangeException e) {
                System.out.println("Error: " + e.getMessage());
                i--;  
            }
        }

        average /= 3;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

  
    }

    @Override
    public void dispMark() {
        System.out.println("Name: " + name);
        System.out.println("Reg No: " + regNo);
        System.out.println("Category: " + category);
        System.out.println("Marks: " + marks[0] + ", " + marks[1] + ", " + marks[2]);
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
    }
}

public class Main3 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Student Name: ");
            String name = scanner.next();
            System.out.print("Enter Reg No: ");
            String regNo = scanner.next();
            System.out.print("Enter Category (UG/PG): ");
            String category = scanner.next();

            students[i] = new Student(name, regNo, category);
            students[i].input();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].average < students[j + 1].average) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        System.out.println("Toppers: ");

        for (int i = 0; i < Math.min(n, 3); i++) {
            students[i].dispMark();
            System.out.println();
        }

        scanner.close();
    }
}
