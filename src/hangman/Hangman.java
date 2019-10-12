package hangman;

import java.util.*;

public class Hangman {

    private SecretWord secretWord;

    private int guessesLeft = 6;

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

    public void checkLetter(String inputLetter) {
        secretWord.checkWordForALetter(inputLetter);
        guessesLeft--;
    }

    public String getTheCurrentStateOfTheWord() {
        return secretWord.getTheCurrentStateOfTheWord();
    }

    public String getTheIncorrectlyGuessedLetters() {
        return secretWord.getTheIncorrectlyGuessedLetters();
    }

    public GameState getTheStateOfTheGame() {
        if (!areThereAnyLettersToGuess()) {
            return GameState.WON;
        } else if (guessesLeft < 1) {
            return GameState.LOST;
        } else {
            return GameState.IN_PROGESS;
        }
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

}
