package hangman;

import java.util.Set;
import java.util.TreeSet;

public class SecretWord {

    private String word = "";
    private Set<String> guessedLetters = new TreeSet<>();

    public SecretWord(String word) {
        this.word = word;
    }

    public boolean checkWordForALetter(String letter) {
        letter = letter.toLowerCase();
        boolean result = false;

        if (isTheLetterValid(letter)) {
            guessedLetters.add(letter);
            result = word.contains(letter);
        }

        return result;
    }

    private boolean isTheLetterValid(String letter) {
        return letter.length() == 1 && letter.matches("[A-Za-z]");
    }

    public String getTheIncorrectlyGuessedLetters() {
        String result = "";
        for (String letter : guessedLetters) {
            if (!word.contains(letter))
                result += letter + " ";
        }
        return result;
    }

    public String getTheCurrentStateOfTheWord() {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            String currentLetter = "" + word.charAt(i);

            if (guessedLetters.contains(currentLetter)) {
                result += currentLetter + " ";
            } else {
                result += "_ ";
            }
        }

        return result;
    }

    public boolean areThereAnyLettersToGuess() {
        return getTheCurrentStateOfTheWord().contains("_");
    }

    public void setWord(String word) {
        this.word = word;
        guessedLetters.clear();
    }

    public String getWord() {
        return this.word;
    }

    public Set<String> getGuessedLetters() {
        return guessedLetters;
    }


}
