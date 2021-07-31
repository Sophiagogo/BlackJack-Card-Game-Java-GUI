package Presentaion.CardGame;

import java.util.ArrayList;

public class Hand extends Deck {

    /**
     * The default constructor should set the hand to an empty set of cards.
     */
    public Hand() {
        super();
        setDeck(new ArrayList<>());
    }

    /**
     * Copies from the given Hand object and constructs an independent Hand object.
     * @param original Hand object to copy from
     */
    public Hand(Hand original) {
        this();
        setDeck(new ArrayList<>(original.getDeck()));
    }

    /**
     * Gets the card stored at the given index in a hand.
     *
     * @param index index of a card in this calling hand
     * @return the card at the given index in this calling hand
     */
    public Card getCard(int index) {
        return getDeck().get(index);
    }


    /**
     * Calculates the total points of a Hand and decides if Ace is 1 or 11 points.
     *
     * @return total points of this calling hand
     */
    public int totalPoints() {
        ArrayList<Card> currDeck = getDeck();
        int pointSum = 0;
        boolean ifAce = false;

        for (Card card : currDeck) {
            int point = card.getPointByName();
            if (point == 1) {
                // there is an Ace
                ifAce = true;
            }
            if (point >= 10) {
                point = 10;
            }
            pointSum += point;
        }

        // decides if Ace is worth 11
        if (ifAce && pointSum + 10 <= 21) {
            pointSum += 10;
        }
        return pointSum;
    }
}
