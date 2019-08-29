import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
    Hangman hangman = new Hangman();
    String wordToBeGuessed = "";

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
}
