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

    @Test
    public void testGuessingWithALetter() {
        hangman.secretWord = "and";
        String testLetter = "a";
        boolean secretWordContainsTheLetter = hangman.checkWordForALetter(testLetter);
        if (!secretWordContainsTheLetter) {
            Assertions.fail("The method couldn't find a letter that was obviously there");
        }
    }

    @Test
    public void testGuessingWithMoreThanOneLetter() {
        hangman.secretWord = "and";
        Assertions.assertFalse(hangman.checkWordForALetter("an"));
    }

    @Test
    public void testCheckIfGuessedLettersAreSaved() {
        hangman.secretWord = "and";
        hangman.checkWordForALetter("a");
        if (!hangman.getGuessedLetters().contains("a")) {
            Assertions.fail("The program is not saving the guessed letters");
        }
    }

    @Test
    public void testLowerAndUpperCaseInput() {
        hangman.secretWord = "and";
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("A");
        Assertions.assertTrue(hangman.getGuessedLetters().contains("a"));
        Assertions.assertFalse(hangman.getGuessedLetters().contains("A"));

        hangman.checkWordForALetter("N");
        Assertions.assertFalse(hangman.getGuessedLetters().contains("N"));
        Assertions.assertTrue(hangman.getGuessedLetters().contains("n"));

    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordRepetitiveLetters() {
        hangman.secretWord = "aabbccdd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.wordCompletionArray = new String[hangman.secretWordInArrayForm.length];
        hangman.refreshWordCompletionArray();
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("d");
        Assertions.assertEquals("a a _ _ _ _ d d ", hangman.getCurrentStateOfTheGame());
    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordNormalLetters() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("c");
        Assertions.assertEquals("a _ c _ ", hangman.getCurrentStateOfTheGame());
    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordZeroLetters() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        Assertions.assertEquals("_ _ _ _ ", hangman.getCurrentStateOfTheGame());
    }

    @Test
    public void testNoMoreLettersToGuess() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        hangman.checkWordForALetter("c");
        hangman.checkWordForALetter("d");
        Assertions.assertFalse(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testOneMoreLettersToGuess() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        hangman.checkWordForALetter("c");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testTwoMoreLettersToGuess() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.checkWordForALetter("a");
        hangman.checkWordForALetter("b");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testThreeMoreLettersToGuess() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        hangman.checkWordForALetter("a");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testNoLettersHaveBeenGuessed() {
        hangman.secretWord = "abcd";
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }


    @Test
    public void testNoCorrectLettersHaveBeenGuessed() {
        hangman.secretWord = "abcd";
        hangman.checkWordForALetter("s");
        hangman.checkWordForALetter("g");
        hangman.checkWordForALetter("r");
        hangman.checkWordForALetter("t");
        hangman.checkWordForALetter("k");
        hangman.checkWordForALetter("v");
        hangman.checkWordForALetter("y");
        hangman.secretWordInArrayForm = hangman.secretWord.split("");
        Assertions.assertTrue(hangman.areThereAnyLettersToGuess());
    }

    @Test
    public void testCheckIfYouCanStartTheGame() {
        Hangman testHangman = new Hangman();
        Assertions.assertTrue(hangman.gettingAWordWasSuccesful());
    }

    @Test
    public void testPrintingTheIncorrectlyGuessedLetters(){
        hangman.secretWord = "abcd";
        hangman.checkWordForALetter("s");
        hangman.checkWordForALetter("g");
        hangman.checkWordForALetter("r");
        hangman.checkWordForALetter("t");
        hangman.checkWordForALetter("k");
        hangman.checkWordForALetter("v");
        hangman.checkWordForALetter("y");
        Assertions.assertEquals("s g r t k v y ",hangman.printTheIncorrectlyGuessedLetters());

    }

}

