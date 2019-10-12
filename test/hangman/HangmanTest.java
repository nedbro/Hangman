package hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class HangmanTest {
    private Hangman hangman;

    @BeforeEach
    public void init() {
        hangman = new Hangman();
        hangman.setSecretWord("abcd");
    }

    @Test
    public void testEveryNewHangmanHasAWord() {
        Hangman firstTestHangman = new Hangman();
        Hangman secondTestHangman = new Hangman();
        Hangman thirdTestHangman = new Hangman();

        if (firstTestHangman.getSecretWord().equals("")) {
            Assertions.fail("The first hangman has no word to be guessed");
        }
        if (secondTestHangman.getSecretWord().equals("")) {
            Assertions.fail("The second hangman has no word to be guessed");
        }
        if (thirdTestHangman.getSecretWord().equals("")) {
            Assertions.fail("The third hangman has no word to be guessed");
        }
    }

    @Test
    public void testMultipleRoundsOfGuessing() {
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (int i = 0; i < alphabet.length; i++) {
            hangman.checkLetter(alphabet[i]);
        }
        Assertions.assertFalse(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testRunningOutOfGuesses() {
        String[] wrongLetters = {"e", "f", "g", "h", "i", "j"};
        for (int i = 0; i < 6; i++) {
            hangman.checkLetter(wrongLetters[i]);
        }

        Assertions.assertEquals(GameState.LOST, hangman.getTheStateOfTheGame());
    }

    @Test
    public void testGuessingTheWordCorrectly() {
        hangman.checkLetter("a");
        hangman.checkLetter("b");
        hangman.checkLetter("c");
        hangman.checkLetter("d");

        Assertions.assertEquals(GameState.WON, hangman.getTheStateOfTheGame());
    }

    @Test
    public void testGuessingTheWordCorrectlyWithTheLastGuess() {
        hangman.checkLetter("a");
        hangman.checkLetter("b");
        hangman.checkLetter("c");
        hangman.checkLetter("e");
        hangman.checkLetter("f");
        hangman.checkLetter("d");

        Assertions.assertEquals(GameState.WON, hangman.getTheStateOfTheGame());
    }

    @Test
    public void testGuessingStillInProgress() {
        hangman.checkLetter("a");
        Assertions.assertEquals(GameState.IN_PROGESS, hangman.getTheStateOfTheGame());
    }


}

