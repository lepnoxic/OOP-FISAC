import java.util.*;
import java.util.concurrent.CountDownLatch;

class VowelCountThread extends Thread {
    private String word;
    private CountDownLatch latch;
    private static int totalVowelWords = 0;
    
    public VowelCountThread(String word, CountDownLatch latch) {
        this.word = word;
        this.latch = latch;
    }

    @Override
    public void run() {
        if (word.length() > 0) {
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u') {
                int count1 = 0;
                for (int j = 0; j < word.length(); j++) {
                    count1++;
                }
                System.out.println("Word starting with a vowel: " + word);
                System.out.println("Number of letters in the word: " + count1);
                totalVowelWords++;
            }
        }
        latch.countDown();
    }

    public static int getTotalVowelWords() {
        return totalVowelWords;
    }
}

class Main {
    public static void main(String[] args) {
        String input;
        System.out.println("Enter the necessary string");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        sc.close();
        
        String[] words = input.split(" ");

        CountDownLatch latch = new CountDownLatch(words.length);

        for (String word : words) {
            VowelCountThread thread = new VowelCountThread(word, latch);
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total words that start with a vowel: " + VowelCountThread.getTotalVowelWords());
    }
}