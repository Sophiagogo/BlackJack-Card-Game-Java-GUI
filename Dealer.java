package Presentaion.CardGame;

/**
 * This class represents a Dealer in a Blackjack game.
 * Dealer is a derived class of Player.
 */
public class Dealer extends Player {
    /**
     * Constructs a Dealer object with a new empty hand.
     */
    public Dealer() {
        super();
    }

    /**
     * Prints the all the cards in a dealer's hand.
     * Sort the hand by name before printing.
     */
    @Override
    public void printCards() {
        Hand currHand = getHand();
        currHand.sortDeck();
        System.out.print("\nDealer's cards: ");
        currHand.printDeck();
    }

    /**
     * Prints all the face-up cards in the dealer's hand.
     * Sort the hand by name before printing.
     */
    public void printFaceUpCards() {
        Hand currHand = getHand();
        System.out.print("Dealer's face-up cards: | ");
        // keep the first card face-down
        for (int i = 1; i < currHand.getDeck().size(); i++) {
            System.out.print(currHand.getCard(i) + " | ");
        }
        currHand.remove();
        int faceUpPoints = currHand.totalPoints();
        System.out.println("\nDealer's total face-up points is: " + faceUpPoints);
    }

}
