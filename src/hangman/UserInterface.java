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
        System.out.println();
    }

    public String getInput() {
        System.out.println("Type in a letter: ");
        return scanner.nextLine();
    }

    public void turn() {
        printHangman();
        printTheCurrentStateOfTheGame();
        hangman.checkLetter(getInput());
    }

    public void endingMessage() {
        System.out.println();
        printHangman();
        System.out.println("------------------------");

        if (hangman.getTheStateOfTheGame().equals(GameState.WON)) {
            System.out.println("CONGRATULATIONS! YOU WON!");
        } else {
            System.out.println("GAME OVER! BETTER LUCK NEXT TIME!");
        }

        System.out.println("THE SECRET WORD WAS: " + hangman.getSecretWord());
    }

    public void printHangman() {
        switch (hangman.getGuessesLeft()) {
            case 6:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
                System.out.println();
                break;
            case 5:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |    O  ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
                System.out.println();
                break;
            case 4:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |   _O  ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
                System.out.println();
                break;
            case 3:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |   _O_ ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
                System.out.println();
                break;

            case 2:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |   _O_ ");
                System.out.println(" |    |  ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
                System.out.println();
                break;

            case 1:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |   _O_ ");
                System.out.println(" |    |  ");
                System.out.println(" |   /   ");
                System.out.println("_|___    ");
                System.out.println();
                break;

            case 0:
                System.out.println("  ____   ");
                System.out.println(" |    |  ");
                System.out.println(" |   _O_ ");
                System.out.println(" |    |  ");
                System.out.println(" |   / \\ ");
                System.out.println("_|___    ");
                System.out.println();
                break;

        }
    }
}
