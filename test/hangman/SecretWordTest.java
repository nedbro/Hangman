package hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SecretWordTest {
    SecretWord secretWord;

    @BeforeEach
    public void init() {
        secretWord = new SecretWord("abcd");
    }


    @Test
    public void testGuessingWithALetter() {
        String testLetter = "a";
        boolean secretWordContainsTheLetter = secretWord.checkWordForALetter(testLetter);
        if (!secretWordContainsTheLetter) {
            Assertions.fail("The method couldn't find a letter that was obviously there");
        }
    }

    @Test
    public void testGuessingWithMoreThanOneLetter() {
        Assertions.assertFalse(secretWord.checkWordForALetter("ab"));
    }


    @Test
    public void testNonCharacterInput() {
        Assertions.assertFalse(secretWord.checkWordForALetter("."));
    }

    @Test
    public void testCheckIfGuessedLettersAreSaved() {
        secretWord.checkWordForALetter("a");
        Assertions.assertTrue(secretWord.getGuessedLetters().contains("a"));
    }


    @Test
    public void testLowerAndUpperCaseInput() {
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("A");
        Assertions.assertTrue(secretWord.getGuessedLetters().contains("a"));
        Assertions.assertFalse(secretWord.getGuessedLetters().contains("A"));

        secretWord.checkWordForALetter("B");
        Assertions.assertFalse(secretWord.getGuessedLetters().contains("B"));
        Assertions.assertTrue(secretWord.getGuessedLetters().contains("b"));

    }


    @Test
    public void testGettingTheCurrentGuessedLettersInWordRepetitiveLetters() {
        secretWord.setWord("aabbccdd");
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("d");
        Assertions.assertEquals("a a _ _ _ _ d d ", secretWord.getTheCurrentStateOfTheWord());
    }


    @Test
    public void testGettingTheCurrentGuessedLettersInWordNormalLetters() {
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("c");
        Assertions.assertEquals("a _ c _ ", secretWord.getTheCurrentStateOfTheWord());
    }

    @Test
    public void testGettingTheCurrentGuessedLettersInWordZeroLetters() {
        Assertions.assertEquals("_ _ _ _ ", secretWord.getTheCurrentStateOfTheWord());
    }

    @Test
    public void testNoMoreLettersToGuess() {
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("b");
        secretWord.checkWordForALetter("c");
        secretWord.checkWordForALetter("d");
        Assertions.assertFalse(secretWord.areThereAnyLettersToGuess());
    }

    @Test
    public void testOneMoreLettersToGuess() {
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("b");
        secretWord.checkWordForALetter("c");
        Assertions.assertTrue(secretWord.areThereAnyLettersToGuess());
    }

    @Test
    public void testTwoMoreLettersToGuess() {
        secretWord.checkWordForALetter("a");
        secretWord.checkWordForALetter("b");
        Assertions.assertTrue(secretWord.areThereAnyLettersToGuess());
    }

    @Test
    public void testThreeMoreLettersToGuess() {
        Assertions.assertTrue(secretWord.areThereAnyLettersToGuess());
    }

    @Test
    public void testNoLettersHaveBeenGuessed() {
        Assertions.assertTrue(secretWord.areThereAnyLettersToGuess());
    }


    @Test
    public void testNoCorrectLettersHaveBeenGuessed() {
        secretWord.checkWordForALetter("s");
        secretWord.checkWordForALetter("g");
        secretWord.checkWordForALetter("r");
        secretWord.checkWordForALetter("t");
        secretWord.checkWordForALetter("k");
        secretWord.checkWordForALetter("v");
        secretWord.checkWordForALetter("y");
        Assertions.assertTrue(secretWord.areThereAnyLettersToGuess());
    }


    @Test
    public void testGettingTheIncorrectlyGuessedLetters() {
        secretWord.checkWordForALetter("g");
        secretWord.checkWordForALetter("k");
        secretWord.checkWordForALetter("r");
        secretWord.checkWordForALetter("s");
        secretWord.checkWordForALetter("t");
        secretWord.checkWordForALetter("v");
        secretWord.checkWordForALetter("y");
        Assertions.assertEquals("g k r s t v y ", secretWord.returnTheIncorrectlyGuessedLettersInAString());
    }

}
