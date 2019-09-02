import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static String englishWordsURLString = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt";


    private String secretWord = "";
    private String[] secretWordInArrayForm;
    private String[] wordCompletionArray;
    private ArrayList<String> guessedLetters = new ArrayList<>();

    public Hangman() {
        this.setSecretWord(getWordForGuessing());
        refreshWordCompletionArray();
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
        this.secretWordInArrayForm = this.secretWord.split("");
        this.wordCompletionArray = new String[this.secretWord.length()];
        this.refreshWordCompletionArray();
    }

    public String getSecretWord() {
        return this.secretWord;
    }

    public boolean checkWordForALetter(String letter) {
        letter = letter.toLowerCase();
        boolean result = false;
        if (letter.length() == 1 && letter.matches("[A-Za-z]")) {
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

    public String returnTheIncorrectlyGuessedLettersInAString() {
        ArrayList<String> allGuessedLetters = getGuessedLetters();
        String result = "";
        for (String letter : allGuessedLetters) {
            if (!secretWord.contains(letter))
                result += letter + " ";
        }
        return result;
    }

    public String getCurrentStateOfTheGuessedWord() {
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

    public boolean areThereAnyLettersToGuess() {
        for (int i = 0; i < secretWordInArrayForm.length; i++) {
            if (wordCompletionArray[i] != secretWordInArrayForm[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean gettingAWordWasSuccessful() {
        if (secretWord.equals("0")) {
            return false;
        } else return true;
    }


    public void doARoundOfGuessing() {
        printTheCurrentStateOfTheGame();
        Scanner scanner = new Scanner(System.in);
        String inputLetter = scanner.nextLine();
        checkWordForALetter(inputLetter);
    }

    public void printTheCurrentStateOfTheGame() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(getCurrentStateOfTheGuessedWord());
        System.out.println();
        System.out.println(returnTheIncorrectlyGuessedLettersInAString());
        System.out.println();
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        while(hangman.areThereAnyLettersToGuess()) {
            hangman.doARoundOfGuessing();
        }

        hangman.printTheCurrentStateOfTheGame();

    }

    public static String getWordForGuessing() {
        Scanner scanner;
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
        for (int i = 0; i <= randomNumbersLocation; i++) {
            if (i == randomNumbersLocation) {
                randomWord = scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }
        return randomWord;
    }

    public static int getARandomNumber(int maxNumber) {
        Random rand = new Random();
        return rand.nextInt(maxNumber);
    }
}
