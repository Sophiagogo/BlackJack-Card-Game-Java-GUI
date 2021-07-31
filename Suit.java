package Presentaion.CardGame;

public enum Suit {
    CLUB, DIAMOND, HEART, SPADE;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
