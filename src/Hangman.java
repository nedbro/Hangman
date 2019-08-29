import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    String englishWordsURLString = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt";

    public static void main(String[] args) {

    }

    public String getWordForGuessing() {
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = createAnURLScanner(scanner);
        } catch (Exception e) {
            return "0";
        }

        String wordForGuessing = getARandomWordWithScanner(scanner);


        return wordForGuessing;
    }

    public Scanner createAnURLScanner(Scanner scanner) throws Exception {
        URL englishWordsURL = new URL(englishWordsURLString);
        try {
            return scanner = new Scanner(englishWordsURL.openStream());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getARandomWordWithScanner(Scanner scanner) {
        int randomNumbersLocation = getARandomNumber(10000);
        String randomWord = "";
        for (int i = 0; i < randomNumbersLocation + 1; i++) {
            if (i == randomNumbersLocation) {
                randomWord = scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }
        return randomWord;
    }

    public int getARandomNumber(int maxNumber) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxNumber);
        return randomNumber;
    }
}
