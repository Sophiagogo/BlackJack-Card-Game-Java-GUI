package Presentaion.CardGame;

import java.util.Scanner;

/**
 * This class represents a Blackjack game. The game is a single-player version of Blackjack.
 * A human-controlled player will play against the computer-controlled dealer.
 */
public class BlackjackGame {
    private Deck deck;
    private Player user;
    private Dealer house;
    private Scanner userIn = new Scanner(System.in);

    /**
     * Constructs a BlackjackGame object with all the game settings reset to initial status.
     */
    public BlackjackGame() {
        printInstruction();
        resetGame();
    }

    /**
     * Resets the game to initial status.
     * Creates new human-controlled player, new computer-controlled dealer,
     * a new deck, and shuffles the deck.
     */
    public void resetGame() {
        // resets players and deck
        user = new Player();
        house = new Dealer();
        deck = new Deck();
        deck.shuffle();
    }

    /**
     * Prints game instructions.
     */
    public static void printInstruction() {
        System.out.println("Welcome to the Blackjack Game!");
        System.out.println("---------------------------------------");
        System.out.println("You will play against the house.");
        System.out.println("The goal is to get as close to 21 points as possible, without exceeding 21.");
        System.out.println("You will be dealt 2 cards. The dealer is dealt with 2 cards, one face-up and one face-down.");
        System.out.println("Face cards are worth 10, while Ace is worth 1 or 11, whichever makes a better hand.");
        System.out.println("You can ask for another card by choosing Hit or hold your total and end your turn by choosing Stand.");
        System.out.println("If you go over 21, you bust. The dealer wins regardless of the dealer's hand.");
        System.out.println("Dealer hits until his/her hand is 17 points or more.");
        System.out.println("---------------------------------------");
    }

    /**
     * Deals two cards to human-controlled player and computer-controlled dealer.
     */
    public void dealCards() {
        // deal user's cards
        user.addCard(deck.nextCard());
        user.addCard(deck.nextCard());

        // deal house's cards
        house.addCard(deck.nextCard());
        house.addCard(deck.nextCard());
    }

    /**
     * Checks if the two players get a Blackjack in the beginning.
     *
     * @return true if either player gets a Blackjack, false if no player gets a Blackjack
     */
    public boolean ifBlackjack() {
        if (house.isBlackjack() && user.isBlackjack()) {
            System.out.println("\nYou and dealer both have a Blackjack! Tie.");
            return true;
        } else if (house.isBlackjack()) {
            System.out.println("\nDealer has a Blackjack! You lost.");
            return true;
        } else if (user.isBlackjack()) {
            System.out.println("\nYou have a Blackjack! You won.");
            return true;
        }
        return false;
    }

    /**
     * Prints the current game status.
     * Prints out human-controlled player's current hand and total points, together with the dealer's face-up cards in hand.
     */
    public void printStatus() {
        // print user's hand
        user.printCards();
        System.out.println("\nPlayer's total points is: " + user.getTotalPoints());

        System.out.println("--");

        // print dealer's face-up cards
        house.printFaceUpCards();
    }

    /**
     * Lets the human-controlled player decide whether to hit or stand.
     */
    public void usersTurn() {
        String ans;
        System.out.println("\nPlayer's turn...");
        user.printCards();
        System.out.println("\nPlayer's total points is: " + user.getTotalPoints());


        do {
            do {
                System.out.println("Hit or Stand? (H/S)");
                ans = userIn.nextLine();
            } while (!ans.toLowerCase().startsWith("h") && !ans.toLowerCase().startsWith("s"));
            // user chooses to hit
            if (ans.toLowerCase().startsWith("h")) {
                user.addCard(deck.nextCard());
                user.printCards();
                System.out.println("\nPlayer's total points is: " + user.getTotalPoints());
            }
        } while (ans.toLowerCase().startsWith("h") && user.getTotalPoints() < 21);
    }


    /**
     * Lets the computer-controlled dealer play.
     */
    public void dealersTurn() {
        System.out.println("\nDealer's turn...\n");
        // dealer will continue hitting until reaches 17 points or more
        while (house.getTotalPoints() < 17) {
            house.addCard(deck.nextCard());
        }
        house.printFaceUpCards();
    }

    /**
     * Asks for user's input on whether to play the game again or not.
     *
     * @return true if the user wants to play again, false if not
     */
    public boolean playAgain() {
        String ans;
        do {
            System.out.println("Do you want to play again? (Yes/No)");
            ans = userIn.nextLine();
        } while (!ans.toLowerCase().startsWith("y") && !ans.toLowerCase().startsWith("n"));

        return ans.toLowerCase().startsWith("y");
    }

    /**
     * Prints out the final results of the game when game is over.
     * If the user gets more than 21 points, the user has busted.
     * If the user gets exactly 21 points , the user has a Blackjack.
     * If the user gets less than 21 points, checks the dealer's points.
     * If the dealer gets 21 points, dealer has a Blackjack.
     * If the dealer gets 21 points or more, the dealer has busted, and the user wins.
     * If the dealer gets less than 21 points, compares dealer and user's points.
     * If same, the game is a tie. If dealer has more points, dealer wins and user loses.
     * If dealer has less points, dealer loses and user wins.
     */
    public void printResults() {
        int userPoints = user.getTotalPoints();
        int dealerPoints = house.getTotalPoints();

        System.out.println("\nGame over!");
        house.printCards();
        user.printCards();

        if (userPoints > 21) {
            // user busted
            System.out.println("\nPlayer has " + userPoints + " points and has busted. Player lost.");
        } else if (dealerPoints > 21) {
            System.out.println("\nDealer has " + dealerPoints + " points and has busted. Player won.");
        } else if (dealerPoints > userPoints) {
            System.out.println("\nDealer has " + dealerPoints + " points and player has " + userPoints + " points. Player lost. Dealer has more points.");
        } else if (dealerPoints == userPoints) {
            System.out.println("\nPlayer and dealer both have " + userPoints + " points. Tie.");
        } else {
            System.out.println("\nDealer has " + dealerPoints + " points and player has " + userPoints + " points. Player won. Player has more points.");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();

       do {
            // reset game
            game.resetGame();

            // initial deal
            game.dealCards();

            // print out current status
            game.printStatus();

            // check for Blackjack
            if (game.ifBlackjack()) {
                continue;
            }

            // user plays first
            game.usersTurn();

            // deal plays
            game.dealersTurn();

            // print out final results
            game.printResults();

            // ask if user wants to play again
        } while (game.playAgain());

    }

}
