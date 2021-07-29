package Presentaion.CardGame;

import java.util.ArrayList;

/**
 * This class represents a Player in a Blackjack game.
 */
public class Player {
    private Hand hand;

    /**
     * Constructs a Player object with a new empty hand.
     */
    public Player() {
        hand = new Hand();
    }

    /**
     * Gets a copy of hand of a Player object.
     * @return a copy of the hand of this calling object
     */
    public Hand getHand() {
        return new Hand(hand);
    }

    /**
     * Sets the hand of a player object to the copy of the given hand.
     * @param hand the hand to set to this calling object
     */
    public void setHand(Hand hand) {
        this.hand = new Hand(hand);
    }

    /**
     * Gets the total points of the player's current hand.
     *
     * @return the total points of this calling player's current hand
     */
    public int getTotalPoints() {
        return hand.totalPoints();
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to be added to this calling player's hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Prints the all the cards in a player's hand.
     * Sort the hand by name before printing.
     */
    public void printCards() {
        hand.sortDeck();
        System.out.print("\nPlayer's cards: ");
        hand.printDeck();
    }

    /**
     * Checks if the player gets a Blackjack.
     * If the player is dealt 21 from the start (Ace & 10-value card), player has a blackjack.
     *
     * @return true if this calling hands has a Blackjack, false if not
     */
    public boolean isBlackjack() {
        ArrayList<Card> currHand = getHand().getDeck();
        boolean aceFlag = false;
        boolean tenFlag = false;
        for (Card card: currHand) {
            if (card.getPointByName() == 1) {
                aceFlag = true;
            }
            if (card.getPointByName() >= 10) {
                tenFlag = true;
            }
        }
        return aceFlag && tenFlag;
    }

}
