import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static String englishWordsURLString = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt";
    String secretWord = "";

    public Hangman(){
        secretWord = getWordForGuessing();

    }

    public boolean gettingAWordWasSuccesful(){
        if(secretWord.equals("0")){
            return false;
        }
        else return true;
    }

    public String getSecretWord(){
        return this.secretWord;
    }
    public static void main(String[] args) {

    }

    public static String getWordForGuessing() {
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = createAnURLScanner();
        } catch (Exception e) {
            return "0";
        }

        int randomLocation = getARandomNumber(9999);
        return getWordFromLocation(scanner, randomLocation);
    }

    public static Scanner createAnURLScanner() throws Exception {
        URL englishWordsURL = new URL(englishWordsURLString);
        return new Scanner(englishWordsURL.openStream());
    }

    public static String getWordFromLocation(Scanner scanner, int randomNumbersLocation) {
        String randomWord = "";
        String throwAwayInput = "";
        for (int i = 0; i <= randomNumbersLocation; i++) {
            if (i == randomNumbersLocation) {
                randomWord = scanner.nextLine();
            } else {
                throwAwayInput = scanner.nextLine();
            }
        }
        return randomWord;
    }

    public static int getARandomNumber(int maxNumber) {
        Random rand = new Random();
        return rand.nextInt(maxNumber);
    }
}
