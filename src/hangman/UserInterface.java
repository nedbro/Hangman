package hangman;

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
    }

    public String getInput() {
        String input = scanner.nextLine();
        return input;
    }

    public void turn() {
        printTheCurrentStateOfTheGame();
        hangman.checkLetter(getInput());
    }
}
