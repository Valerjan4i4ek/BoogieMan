import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BoogieMan {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:/Words.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()){
            words.add(scanner.nextLine());
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));
        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while (true){

            printBoogieMan(wrongCount);

            if(wrongCount >= 6){
                System.out.println("You lose!:(");
                break;
            }

            printWordState(word, playerGuesses);
            if(!getPlayerGuess(keyboard, word, playerGuesses)){
                wrongCount++;
            }
            if(printWordState(word, playerGuesses)){
                System.out.println("You win!!!");
                break;
            }

            System.out.println("Please enter your guess for the world");
            if(keyboard.nextLine().equals(word)){
                System.out.println("You win!!!");
                break;
            }
            else{
                System.out.println("Nope! Try again!");
            }
        }
    }

    public static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses){
        System.out.println("Please enter a letter!");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    public static boolean printWordState(String word, List<Character> playerGuesses){
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if(playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else{
                System.out.print("_");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

    public static void printBoogieMan(Integer wrongCount){
        System.out.println(" -------");
        System.out.println(" |     |");
        if(wrongCount >= 1){
            System.out.println(" O");
        }
        if(wrongCount >= 2){
            System.out.print("\\ ");
            if(wrongCount >= 3){
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }
        if(wrongCount >= 4){
            System.out.println(" |");
        }
        if(wrongCount >= 5){
            System.out.print("/ ");
            if(wrongCount >= 6){
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
    }
}
