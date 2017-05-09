package com.leonslegion.casino;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class PokerHand extends Hand implements Comparable {

    HandType handType;

    PokerHand() {
        Collections.sort(getHand(), new CardComparator());
    }

    public int compareTo(Object other) {
        PokerHand otherHand = (PokerHand) other;
        return handType.ordinal() - otherHand.getHandType().ordinal();
    }

    enum HandType {HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH}

    HandType getHandType() {
        return handType;
    }

    void setHandType() {
        this.handType = HandType.HIGHCARD;
    }

}