import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
    Hangman hangman = new Hangman();
    String wordToBeGuessed = "";

    @Test
    public void testGetAWordForGuessing() {
        String returnedString = hangman.getWordForGuessing();
        boolean returnedStringIsAWord = returnedString.matches("[A-Za-z]+");

        if (returnedStringIsAWord == false) {
            Assertions.fail("The game couldn't get a word for the game.");
        } else {
            wordToBeGuessed = returnedString;
        }

    }

    @Test
    public void testGetADifferentWordEachTime() {
        String returnedWord = hangman.getWordForGuessing();
        if(wordToBeGuessed.equals(returnedWord)) {
            String oldReturnedWord = returnedWord;
            String newReturnedWord = hangman.getWordForGuessing();
            if(oldReturnedWord.equals(newReturnedWord)) {
                Assertions.fail("The game gets the same word every time");
            }
        }
    }
}
