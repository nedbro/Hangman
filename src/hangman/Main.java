package hangman;

public class Main {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        UserInterface userInterface = new UserInterface(hangman);

        while (hangman.getTheStateOfTheGame().equals(GameState.IN_PROGESS)) {
            userInterface.turn();
        }
        userInterface.endingMessage();
    }
}
