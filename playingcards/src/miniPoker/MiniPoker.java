package miniPoker;
import playingCards.Deck;
import java.util.Scanner;

/**
 * A 2-card poker game played between a human and a computer player
 *
 * @author Disha Revandkar   dr6742@rit.edu
 * @author Shreya Pramod     sp3045@rit.edu
 */
public class MiniPoker {

    public static final int MAX_CARDS = 2;

    /**
     * Creates and intializes minipoker
     */
    public MiniPoker(){
        MiniPoker miniPoker = new MiniPoker();
    }

    /**
     * Plays multiple hands of poker, after each hand ask the user if they want
     * to play again. We also keep track of the number of games played/won by the user
     * and print the results at the end.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        Computer computer = new Computer();
        Human human = new Human(sc);

        int totalGames = 0;
        int totalWins = 0;
        int totalTies = 0;

        boolean humanFirst = true;
        boolean keepPlaying = false;
        boolean someoneFolded;

        do {
            someoneFolded = false;
            System.out.println("##########################################\n" +
                               "##########       NEW HAND      ###########\n" +
                               "##########################################");
            deck.shuffle();
            System.out.println("== Shuffling");
            human.newHand();
            computer.newHand();
            System.out.println("== Dealing Cards");
            for (int index = 0; index < 2; index++) {
                human.takeCard(deck.dealCard());
                computer.takeCard(deck.dealCard());
            }
            human.printHand();
            System.out.println("\nTotal =" +human.getValue());
            System.out.println("\n ");
            if (humanFirst) {
                System.out.println(
                        "\nIts your turn first. \n Do you want to stand(y) or fold(n)?");
                if (sc.nextLine().equals("n")) {
                    System.out.println(
                            "You have folded. The Computer wins!");someoneFolded = true;
                    totalGames++;
                } else {
                    System.out.println("You have decided to stand!");
                    System.out.println("Waiting for the computer...");
                    if (!computer.stand()) {
                        System.out.println(
                                "Computer folded. You have won. Congratulations!");someoneFolded = true;
                        computer.printHand();
                        System.out.println("\nTotal =" +computer.getValue());
                        totalWins++;
                        totalGames++;
                    }
                }
            } else {
                System.out.println("\nComputer plays first.");
                if (!computer.stand()) {
                    System.out.println(
                            "Computer folds. Congratulations, you have won!");someoneFolded = true;
                    computer.printHand();
                    System.out.println("\nTotal =" +computer.getValue());
                    totalWins++;
                    totalGames++;

                } else {
                    System.out.println("Computer decided to stand!");
                    System.out.println(
                            "Its your turn first. \n Do you want to stand(y) or fold(n)?");
                    if (sc.nextLine().equals("n")) {
                        System.out.println(
                                "You have folded. The Computer wins!");someoneFolded = true;
                        computer.printHand();
                        System.out.println("\nTotal =" +computer.getValue());
                        totalGames++;
                    }
                }
            }

            if (!someoneFolded) {
                if (computer.getValue() > human.getValue()) {
                    System.out.println("Computer wins! Better hand...");
                    computer.printHand();
                    System.out.println("\nTotal =" +computer.getValue());
                    totalGames++;

                } else if (computer.getValue() < human.getValue()) {
                    System.out.println("Congratulations! You have won!");
                    computer.printHand();
                    System.out.println("\nTotal =" +computer.getValue());
                    totalWins++;
                    totalGames++;
                } else {
                    System.out.println("Draw. Play again?");
                    computer.printHand();
                    System.out.println("Total =" +computer.getValue());
                    totalTies++;
                    totalGames++;
                }
            }
            humanFirst = !humanFirst;
            System.out.println(
                    "\nDo you wish to play another round?: y/n ?");
            keepPlaying = sc.nextLine().equals("y");
        } while (keepPlaying);
        System.out.println("========== Results ==========");
        System.out.println("Games = " +totalGames);
        System.out.println("Wins = " +totalWins);
        System.out.println("Ties = " +totalTies);
    }
}