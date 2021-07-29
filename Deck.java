package Presentaion.CardGame;

import java.util.*;

/**
 * Deck class represent the standard 52 cards
 * and store them in the ArrayList. The Deck class contains methods:
 * add, remove, shuffle, sort and print.
 */

public class Deck {
    private ArrayList<Card> deck;
    //private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;


    /**
     * Construct a default Deck object that represent the standard 52 cards
     * and store them in the arraylist.
     */

    public Deck() {
        this.deck = new ArrayList<>(NUMBER_OF_CARDS);
        Suit[] suit = {Suit.CLUB, Suit.DIAMOND, Suit.SPADE, Suit.HEART};
        Name[] point = {Name.ACE, Name.DEUCE, Name.THREE, Name.FOUR, Name.FIVE, Name.SIX,
                Name.SEVEN, Name.EIGHT, Name.NINE, Name.TEN, Name.JACK, Name.QUEEN, Name.KING};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(point[j], suit[i]);
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return new ArrayList<>(deck);
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = new ArrayList<>(deck);
    }

    /**
     * Print every card in the deck.
     */
    public void printDeck() {
        System.out.print("| ");
        for (Card card : deck) {
            System.out.print(card + " | ");
        }

    }

    /**
     * Shuffle the cards in the deck, randomly swapping every card in the deck.
     * Go through the array and exchange each element with the randomly chosen element
     * in the range of 52.
     * It is possible that an element may be swap with itself, we treat it as reasonable.
     */
    public void shuffle() {
        Random ram = new Random();
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            int randomNum = ram.nextInt(NUMBER_OF_CARDS);
            // swap the element at position i with element at position randomNum
            Collections.swap(deck, i, randomNum);
        }

    }

    public Card nextCard() {
        return remove();
    }

    /**
     * Add a new card to the deck.
     *
     * @param card the card to be added to the Arraylist
     */
    public void add(Card card) {
        deck.add(card);
    }

    /**
     * Remove a card from the deck.
     * This removes the first card stored in the Arraylist
     */
    public Card remove() {
        return deck.remove(0);

    }

    /**
     * Sort the cards in the deck ordered by name.
     */

    public void sortDeck() {
        Collections.sort(deck);
    }

}




