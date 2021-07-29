package Presentaion.CardGame;

/**
 * The Card class that stores the suit (e.g., Clubs, Diamonds, Hearts, Spades)
 * and name (e.g., Ace, 2, 10, Jack) of each card
 * along with appropriate accessors, constructors, and mutators.
 * The class contains compareTo methods that sorts the cards by name value.
 */
public class Card implements java.lang.Comparable<Object> {
    private Name point; //point of the card (Ace, 2, 10, Jack, Queen, King...)
    private Suit suit; //suit of card (Heart, Diamonds, Club, Spade)

    /**
     * Constructs a Card object with given card's name and suit.
     *
     * @param point name of the card (Ace, 2, 10, Jack, Queen, King...)
     * @param suit  suit of card (Heart, Diamonds,Club, Spades)
     */
    public Card(Name point, Suit suit) {
        setPoint(point);
        setSuit(suit);
    }

    /**
     * Sets the point of card.
     *
     * @param point point of the card to be set of this calling object
     */
    public void setPoint(Name point) {
        this.point = point;
    }

    /**
     * Returns the point of the card.
     *
     * @return the point of the card
     */
    public Name getPoint() {
        return point;
    }

    public int getPointByName() {
        return switch (getPoint()) {
            case ACE -> 1;
            case DEUCE -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
        };
    }

    /**
     * Sets the suit of card.
     *
     * @param suit suit of the card to be set of this calling object
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * get the suit of the card.
     *
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Return String representation of Card.
     *
     * @return String representation of Card, like: Ace of Hearts
     */
    public String toString() {
        return point + " of " + suit;
    }


    /**
     * Override the compareTo method that to compare two card by name.
     *
     * @param object another object to compare
     * @return less than 0 if the calling object is less than another object, return greater than 0
     * if calling object is greater than another object, return 0 if they are equal.
     */
    @Override
    public int compareTo(Object object) {
        if (object == null) throw new IllegalArgumentException("Given object is null.");
        if (!(object instanceof Card)) throw new IllegalArgumentException("Given object is not a card.");
        Card card = (Card) object;
        return getPointByName() - card.getPointByName();
    }

}
