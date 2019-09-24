package hangman;

import hangman.WordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class WordGeneratorTest {
    private WordGenerator wordGenerator;

    @BeforeEach
    public void init() {
        wordGenerator = new WordGenerator();
    }

    @Test
    public void testGetAWordForGuessing() {
        String returnedString = wordGenerator.getWordForGuessing();
        boolean returnedStringIsAWord = returnedString.matches("[A-Za-z]+");

        if (!returnedStringIsAWord) {
            Assertions.fail("The game couldn't get a word for the game.");
        }

    }

    @Test
    public void testGetADifferentWordEachTime() {
        String firstWord = wordGenerator.getWordForGuessing();
        String secondWord = wordGenerator.getWordForGuessing();
        if (firstWord.equals(secondWord)) {
            String thirdWord = wordGenerator.getWordForGuessing();
            if (secondWord.equals(thirdWord)) {
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
            scanner = wordGenerator.createAnURLScanner();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        String readFirstWord = wordGenerator.getWordFromLocation(scanner, 0);
        String readLastWord = wordGenerator.getWordFromLocation(scanner, 9998);

        if (!expectedFirstWord.equals(readFirstWord)) {
            Assertions.fail("The expected and read first words don't match");
        }

        if (!expectedLastWord.equals(readLastWord)) {
            Assertions.fail("The expected and read first words don't match");
        }
    }

}
