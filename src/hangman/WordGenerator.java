package hangman;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class WordGenerator {
    private static final String ENGLISH_WORDS_URL = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt";
    private Random rand = new Random();

    String getWordForGuessing() {
        Scanner scanner;
        try {
            scanner = createAnURLScanner();
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't reach the file with the words.");
        }

        int randomLocation = rand.nextInt(9999);
        return getWordFromLocation(scanner, randomLocation).toLowerCase();
    }

    Scanner createAnURLScanner() throws IOException {
        URL englishWordsURL = new URL(ENGLISH_WORDS_URL);
        return new Scanner(englishWordsURL.openStream());
    }

    String getWordFromLocation(Scanner scanner, int randomNumbersLocation) {
        String randomWord = "";
        for (int i = 0; i <= randomNumbersLocation; i++) {
            if (i == randomNumbersLocation) {
                randomWord = scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }
        return randomWord;
    }
}
