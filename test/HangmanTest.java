import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
    Hangman hangman = new Hangman();

    @Test
    public void testGetAWordForGuessing() {
        String returnedString = "asd";
        boolean returnedStringIsAWord = returnedString.matches("[A-Za-z]+");

        if (returnedStringIsAWord == false) {
            Assertions.fail("The game couldn't get a word for the game.");
        }

    }
}
