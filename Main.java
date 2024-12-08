import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main (String[] args){
        playGame();
    }
    public static void playGame(){
        final int MAX_ATTEMPTS = 5;
        final int CARGO_AMOUNT = 3;
        final int DISTANCE = 7;

        Scanner scanner = new Scanner (System.in);

        while (true){
            int [] cargoLocations=getRandomCargoLocations(CARGO_AMOUNT, DISTANCE);

            System.out.println("\nStart! Find all hidden cargo locations!\nYou have 5 attempts to won a game.");

            boolean gameWon = false;
            for (int attempt = 1; attempt<=MAX_ATTEMPTS; attempt++){
                System.out.println("\nAttempt "+ attempt+":");
                System.out.print("Enter "+CARGO_AMOUNT+" guesses (between 1 and "+DISTANCE+"): ");

                int [] guesses = getUserGuesses(scanner, CARGO_AMOUNT, DISTANCE);

                int correctCount=ifGuessesTrue(cargoLocations, guesses);


                if (correctCount == CARGO_AMOUNT) {
                    System.out.println("\nCongratulations! You found all the cargo!");
                    gameWon = true;
                    break;
                }else if(correctCount==0){
                    System.out.println("You couldn't find any of them.");

                }else {
                    System.out.println("You found " + correctCount+" cargo.");
                }
            }
            if (!gameWon) {
                System.out.println("\nGame over! You couldn't find all hidden cargo.");
                System.out.println(Arrays.toString(cargoLocations));
            }
            System.out.println("\nDo you want to try again? (write yes or no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")){
                System.out.println("\nGoodbye!)");
                break;
            }
        }
    }
    public static int[] getRandomCargoLocations(int countOfCargo, int intervalOfHidden) {
        Random random = new Random();
        int[] locations = new int[countOfCargo];
        boolean[] used = new boolean[intervalOfHidden + 1];
        int filled = 0;

        while (filled < countOfCargo) {
            int loc = random.nextInt(intervalOfHidden)+1;
            if (!used[loc]) {
                locations[filled] = loc;
                used[loc] = true;
                filled++;
            }
        }
        return locations;
    }
    public static int[] getUserGuesses(Scanner scanner, int countOfHidden, int intervalOfHidden) {
        int[] guesses = new int[countOfHidden];
        for (int i = 0; i < countOfHidden; i++) {
            boolean errorDisplayed = false;
            while (true) {
                try {
                    guesses[i] = scanner.nextInt();
//                    if (guesses.length != countOfHidden) {
//                        System.out.println("Error! Enter only 3 guesses.");
//                        errorDisplayed=true;
//                        scanner.next();
//                    }else
                    if (guesses[i] >= 1 && guesses[i] <= intervalOfHidden) {
                        break;
                    } else {
                        if (!errorDisplayed) {
                            System.out.println("Error! Please enter a number between 1 and " + intervalOfHidden + ".");
                            System.out.print("Enter your guesses again: ");
                            errorDisplayed = true;
                        }
                    }
                } catch (Exception e) {
                    if (!errorDisplayed) {
                        System.out.println("Error! Please enter a valid number.");
                        System.out.print("Enter your guesses again: ");
                        errorDisplayed = true;
                    }
                    scanner.next();
                }
            }
        }
        return guesses;
    }
    public static int ifGuessesTrue(int[] cargoLocations, int [] guesses){
        int countTrueGuesses = 0;
        for (int cargoLocation : cargoLocations) {
            for (int guess : guesses) {
                if (cargoLocation == guess) {
                    countTrueGuesses++;
                    break;
                }
            }
        }
        return countTrueGuesses;
    }

//   public static void (){
//    }
}