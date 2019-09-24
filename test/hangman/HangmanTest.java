package hangman;

import hangman.Hangman;
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
            ByteArrayInputStream in = new ByteArrayInputStream(alphabet[i].getBytes());
            System.setIn(in);
            hangman.doARoundOfGuessing();
        }
        Assertions.assertFalse(hangman.areThereAnyLettersToGuess());
    }


}

