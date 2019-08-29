import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class HangmanTest {
    Hangman hangman = new Hangman();
    String wordToBeGuessed = hangman.getSecretWord();

    @Test
    public void testGetAWordForGuessing() {
        String returnedString = Hangman.getWordForGuessing();
        boolean returnedStringIsAWord = returnedString.matches("[A-Za-z]+");

        if (!returnedStringIsAWord) {
            Assertions.fail("The game couldn't get a word for the game.");
        } else {
            wordToBeGuessed = returnedString;
        }

    }

    @Test
    public void testGetADifferentWordEachTime() {
        String returnedWord = Hangman.getWordForGuessing();
        if (wordToBeGuessed.equals(returnedWord)) {
            String newReturnedWord = Hangman.getWordForGuessing();
            if (returnedWord.equals(newReturnedWord)) {
                Assertions.fail("The game gets the same word every time");
            }
        }
    }

    @Test
    public void testFirstAndLastWordFromTheFile() {
        String expectedFirstWord = "the";
        String expectedLastWord = "poison";
        Scanner scanner = new Scanner(System.in);

        try {
            scanner = Hangman.createAnURLScanner();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        String readFirstWord = Hangman.getWordFromLocation(scanner, 0);
        String readLastWord = Hangman.getWordFromLocation(scanner, 9998);

        if (!expectedFirstWord.equals(readFirstWord)) {
            Assertions.fail("The expected and read first words don't match");
        }

        if (!expectedLastWord.equals(readLastWord)) {
            Assertions.fail("The expected and read first words don't match");
        }
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
}
