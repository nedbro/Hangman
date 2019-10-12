package hangman;

public class Main {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        UserInterface userInterface = new UserInterface(hangman);

        while (hangman.areThereAnyLettersToGuess()) {
            userInterface.turn();
        }
        userInterface.printTheCurrentStateOfTheGame();
    }
}
