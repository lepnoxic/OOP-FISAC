import java.util.Scanner;

class OddArraySizeException extends Exception {
    public OddArraySizeException(String message) {
        super(message);
    }
}

class FirstHalf extends Thread {
    int arr[];
    int element;
    boolean found = false;

    FirstHalf(int arr[], int element) {
        this.arr = arr;
        this.element = element;
    }

    public void run() {
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == element) {
                found = true;
                break;
            }
        }
    }

    public boolean isFound() {
        return found;
    }
}

class SecondHalf extends Thread {
    int arr[];
    int element;
    boolean found = false;

    SecondHalf(int arr[], int element) {
        this.arr = arr;
        this.element = element;
    }

    public void run() {
        for (int i = arr.length / 2; i < arr.length; i++) {
            if (arr[i] == element) {
                found = true;
                break;
            }
        }
    }

    public boolean isFound() {
        return found;
    }
}

public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        int element;

        System.out.println("Enter the size of the array:");
        n = scanner.nextInt();

        try {
            if (n % 2 != 0) {
                throw new OddArraySizeException("Odd size array not allowed.");
            }
        } catch (OddArraySizeException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter element " + (i + 1) + ":");
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter the element to be found:");
        element = scanner.nextInt();

        FirstHalf firstHalfThread = new FirstHalf(arr, element);
        SecondHalf secondHalfThread = new SecondHalf(arr, element);

        firstHalfThread.start();
        secondHalfThread.start();

        try {
            firstHalfThread.join();
            secondHalfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!firstHalfThread.isFound() && !secondHalfThread.isFound()) {
            System.out.println("Element not found in the first or second half");
        } 
        else if (firstHalfThread.isFound() && !secondHalfThread.isFound()) {
            System.out.println("Found in first half");
        }
        else if (!firstHalfThread.isFound() && secondHalfThread.isFound()) {
            System.out.println("Found in second half");
        }
        else{
            System.out.println("Found in first and second half");
        }
        
        scanner.close();
    }
}
