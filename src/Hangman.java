import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static String englishWordsURLString = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt";
    String secretWord = "";
    String[] secretWordInArrayForm;
    String[] wordCompletionArray;
    ArrayList<String> guessedLetters = new ArrayList<>();

    public Hangman() {
        secretWord = getWordForGuessing();
        secretWordInArrayForm = secretWord.split("");
        wordCompletionArray = new String[secretWordInArrayForm.length];
        refreshWordCompletionArray();
    }

    public boolean gettingAWordWasSuccesful() {
        if (secretWord.equals("0")) {
            return false;
        } else return true;
    }

    public String getSecretWord() {
        return this.secretWord;
    }

    public boolean checkWordForALetter(String letter) {
        letter = letter.toLowerCase();
        boolean result = false;
        if (letter.length() == 1) {
            if (secretWord.contains(letter) && !guessedLetters.contains(letter)) {
                guessedLetters.add(letter);
                result = true;
            } else if (!guessedLetters.contains(letter)) {
                guessedLetters.add(letter);
            }
        }
        refreshWordCompletionArray();
        return result;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    public String getCurrentStateOfTheGame() {
        String result = "";
        for (int i = 0; i < secretWord.length(); i++) {
            result += wordCompletionArray[i] + " ";
        }
        return result;
    }

    public void refreshWordCompletionArray() {
        for (int i = 0; i < secretWordInArrayForm.length; i++) {
            if (guessedLetters.contains(secretWordInArrayForm[i])) {
                wordCompletionArray[i] = secretWordInArrayForm[i];
            } else {
                wordCompletionArray[i] = "_";
            }
        }
    }

    public static void main(String[] args) {

    }

    public static String getWordForGuessing() {
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = createAnURLScanner();
        } catch (Exception e) {
            return "0";
        }

        int randomLocation = getARandomNumber(9999);
        return getWordFromLocation(scanner, randomLocation).toLowerCase();
    }

    public static Scanner createAnURLScanner() throws Exception {
        URL englishWordsURL = new URL(englishWordsURLString);
        return new Scanner(englishWordsURL.openStream());
    }

    public static String getWordFromLocation(Scanner scanner, int randomNumbersLocation) {
        String randomWord = "";
        String throwAwayInput = "";
        for (int i = 0; i <= randomNumbersLocation; i++) {
            if (i == randomNumbersLocation) {
                randomWord = scanner.nextLine();
            } else {
                throwAwayInput = scanner.nextLine();
            }
        }
        return randomWord;
    }

    public static int getARandomNumber(int maxNumber) {
        Random rand = new Random();
        return rand.nextInt(maxNumber);
    }
}
