package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private String secretWord = "";
    private String[] secretWordInArrayForm;
    private String[] wordCompletionArray;
    private List<String> guessedLetters = new ArrayList<>();

    public Hangman() {
        WordGenerator wordGenerator = new WordGenerator();
        this.setSecretWord(wordGenerator.getWordForGuessing());
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

    public List<String> getGuessedLetters() {
        return guessedLetters;
    }

    public String returnTheIncorrectlyGuessedLettersInAString() {
        List<String> allGuessedLetters = getGuessedLetters();
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
            if (!wordCompletionArray[i].equals(secretWordInArrayForm[i])) {
                return true;
            }
        }
        return false;
    }

    boolean gettingAWordWasSuccessful() {
        return !secretWord.equals("0");
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

}
