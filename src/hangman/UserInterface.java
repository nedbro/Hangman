package hangman;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private Hangman hangman;

    public UserInterface(Hangman hangman) {
        this.hangman = hangman;
    }

    public void printTheCurrentStateOfTheGame() {
        System.out.println();
        System.out.println("------------------------");
        System.out.println(hangman.getTheCurrentStateOfTheWord());
        System.out.println(hangman.getTheIncorrectlyGuessedLetters());
        System.out.println();
    }

    public String getInput() {
        System.out.println("Type in a letter: ");
        String input = scanner.nextLine();
        return input;
    }

    public void turn() {
        printTheCurrentStateOfTheGame();
        hangman.checkLetter(getInput());
    }

    public void endingMessage() {
        System.out.println();
        System.out.println("------------------------");

        if (hangman.getTheStateOfTheGame().equals(GameState.WON)) {
            System.out.println("CONGRATULATIONS! YOU WON!");
        } else {
            System.out.println("GAME OVER! BETTER LUCK NEXT TIME!");
        }

        System.out.println("THE SECRET WORD WAS: " + hangman.getSecretWord());
    }
}
