import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class HangmanTest {
    private Hangman hangman;

    @BeforeEach
    public void init(){
        hangman = new Hangman();
        hangman.setSecretWord("abcd");
    }

    @Test
    public void testGetAWordForGuessing() {
        String returnedString = Hangman.getWordForGuessing();
        boolean returnedStringIsAWord = returnedString.matches("[A-Za-z]+");

        if (!returnedStringIsAWord) {
            Assertions.fail("The game couldn't get a word for the game.");
        }

    }

    @Test
    public void testGetADifferentWordEachTime() {
        hangman.setSecretWord(Hangman.getWordForGuessing());
        String returnedWord = Hangman.getWordForGuessing();
        if (hangman.getSecretWord().equals(returnedWord)) {
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

    @Test
    public void testGuessingWithALetter() {
        String testLetter = "a";
        boolean secretWordContainsTheLetter = hangman.checkWordForALetter(testLetter);
        if (!secretWordContainsTheLetter) {
            Assertions.fail("The method couldn't find a letter that was obviously there");
        }
    }

    @Test
    public void testGuessingWithMoreThanOneLetter() {
        Assertions.assertFalse(hangman.checkWordForALetter("ab"));
    }

    @Test
    public void testCheckIfGuessedLettersAreSaved() {
        hangman.checkWordForALetter("a");
        if (!hangman.getGuessedLetters().contains("a")) {
            Assertions.fail("The program is not saving the guessed letters");
        }
    }

    @Test
    public void testLowerAndUpperCaseInput() {
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("A");
        Assertions.assertTrue(hangman.getGuessedLetters().contains("a"));
        Assertions.assertFalse(hangman.getGuessedLetters().contains("A"));

        hangman.checkWordForALetter("B");
        Assertions.assertFalse(hangman.getGuessedLetters().contains("B"));
        Assertions.assertTrue(hangman.getGuessedLetters().contains("b"));

    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordRepetitiveLetters() {
        hangman.setSecretWord("aabbccdd");
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("d");
        Assertions.assertEquals("a a _ _ _ _ d d ", hangman.getCurrentStateOfTheGuessedWord());
    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordNormalLetters() {
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("c");
        Assertions.assertEquals("a _ c _ ", hangman.getCurrentStateOfTheGuessedWord());
    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordZeroLetters() {
        Assertions.assertEquals("_ _ _ _ ", hangman.getCurrentStateOfTheGuessedWord());
    }

    @Test
    public void testNoMoreLettersToGuess() {
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        hangman.checkWordForALetter("c");
        hangman.checkWordForALetter("d");
        Assertions.assertFalse(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testOneMoreLettersToGuess() {
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        hangman.checkWordForALetter("c");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testTwoMoreLettersToGuess() {
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testThreeMoreLettersToGuess() {
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testNoLettersHaveBeenGuessed() {
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }


    @Test
    public void testNoCorrectLettersHaveBeenGuessed() {
        hangman.checkWordForALetter("s");
        hangman.checkWordForALetter("g");
        hangman.checkWordForALetter("r");
        hangman.checkWordForALetter("t");
        hangman.checkWordForALetter("k");
        hangman.checkWordForALetter("v");
        hangman.checkWordForALetter("y");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testCheckIfYouCanStartTheGame() {
        Hangman testHangman = new Hangman();
        Assertions.assertTrue(testHangman.gettingAWordWasSuccessful());
    }

    @Test
    public void testGettingTheIncorrectlyGuessedLetters() {
        hangman.checkWordForALetter("s");
        hangman.checkWordForALetter("g");
        hangman.checkWordForALetter("r");
        hangman.checkWordForALetter("t");
        hangman.checkWordForALetter("k");
        hangman.checkWordForALetter("v");
        hangman.checkWordForALetter("y");
        Assertions.assertEquals("s g r t k v y ", hangman.returnTheIncorrectlyGuessedLettersInAString());
    }

    @Test
    public void testARoundOfGuessing() {
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());

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

    @Test
    public void testNonCharacterInput(){
        Assertions.assertFalse(hangman.checkWordForALetter("."));
    }

}

