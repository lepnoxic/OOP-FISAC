import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class PrimeCounter {
    static <T extends Number> int countPrimes(Collection<T> collection) {
        int count = 0;
        for (T number : collection) {
            if (isPrime(number.intValue())) {
                count++;
            }
        }
        return count;
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> integerList = new ArrayList<>();

        System.out.print("Enter the number of elements in the collection: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            integerList.add(number);
        }

        int primeCount = PrimeCounter.countPrimes(integerList);
        System.out.println("Number of prime numbers in the collection: " + primeCount);

        scanner.close();
    }
}
