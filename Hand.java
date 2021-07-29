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


    public static void main(String[] args) {
        // creates a deck of cards, shuffles the deck
        Deck deck = new Deck();
        deck.shuffle();
        // creates two hands of 5 cards each.
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        try {
            for (int i = 0; i < 5; i++) {
                Card card = deck.remove();
                hand1.add(card);
            }

            for (int i = 0; i < 5; i++) {
                Card card = deck.remove();
                hand2.add(card);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        // Test the sort and print functions for the hands and the deck.
        System.out.println("Deck is: ");
        deck.printDeck();
        System.out.println("\nHand1 is: ");
        hand1.printDeck();
        System.out.println("\nHand2 is: ");
        hand2.printDeck();
        deck.sortDeck();
        hand1.sortDeck();
        hand2.sortDeck();
        System.out.println("\n\nAfter sorting them ...");
        System.out.println("\nDeck is: ");
        deck.printDeck();
        System.out.println("\nHand1 is: ");
        hand1.printDeck();
        System.out.println("\nHand2 is: ");
        hand2.printDeck();

        // Finally, return the cards in the hand to the deck and test to ensure that the cards have been properly returned.
        System.out.println("\n\nReturn the cards in the hand to the deck:");
        for (int i = 0; i < 5; i++) {
            Card card = hand1.remove();
            deck.add(card);
        }

        for (int i = 0; i < 5; i++) {
            Card card = hand2.remove();
            deck.add(card);
        }

        System.out.println("Sorted deck is: ");
        deck.sortDeck();
        deck.printDeck();

    }
}
