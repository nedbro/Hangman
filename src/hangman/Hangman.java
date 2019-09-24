package hangman;

import java.util.*;

public class Hangman {

    private SecretWord secretWord;

    public Hangman() {
        WordGenerator wordGenerator = new WordGenerator();
        secretWord = new SecretWord(wordGenerator.getWordForGuessing());
    }

    public void setSecretWord(String secretWord) {
        this.secretWord.setWord(secretWord);
    }

    public String getSecretWord() {
        return this.secretWord.getWord();
    }

    public boolean areThereAnyLettersToGuess() {
        return secretWord.areThereAnyLettersToGuess();
    }

    public void doARoundOfGuessing() {
        printTheCurrentStateOfTheGame();
        Scanner scanner = new Scanner(System.in);
        String inputLetter = scanner.nextLine();
        secretWord.checkWordForALetter(inputLetter);
    }

    public void printTheCurrentStateOfTheGame() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(secretWord.getTheCurrentStateOfTheWord());
        System.out.println();
        System.out.println(secretWord.returnTheIncorrectlyGuessedLettersInAString());
        System.out.println();
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        while (hangman.areThereAnyLettersToGuess()) {
            hangman.doARoundOfGuessing();
        }

        hangman.printTheCurrentStateOfTheGame();

    }

}
