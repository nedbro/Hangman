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

    public void checkLetter(String inputLetter) {
        secretWord.checkWordForALetter(inputLetter);
    }

    public String getTheCurrentStateOfTheWord() {
        return secretWord.getTheCurrentStateOfTheWord();
    }

    public String getTheIncorrectlyGuessedLetters() {
        return secretWord.getTheIncorrectlyGuessedLetters();
    }

}
