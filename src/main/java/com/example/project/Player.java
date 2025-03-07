package com.example.project;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards;
    private String[] suits = Utility.getSuits();
    private String[] ranks = Utility.getRanks();

    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

  
    public ArrayList<Card> getHand() {
        return hand;
    }

   
    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCard(Card c) {
        if (c != null) {
            hand.add(c);
            allCards.add(c);
        }
    }

    // Combines the player's hand with community cards and finds the best hand
    public String playHand(ArrayList<Card> communityCards) {
        if (communityCards == null) {
            communityCards = new ArrayList<>();
        }

        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);

        // if there is no card it return nothing
        if (allCards.isEmpty()) {
            return "Nothing";
        }

        sortAllCards();

        // finds the amount of suits and ranks 
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        ArrayList<Integer> suitFrequency = findSuitFrequency();

        boolean flush = false;
        boolean fourKind = false;
        boolean threeKind = false;
        boolean royal = false;
        boolean straight = false;
        int pairCount = 0;

        // Check for flush 
        for (int i = 0; i < suitFrequency.size(); i++) {
            if (suitFrequency.get(i) >= 5) {
                flush = true;
            }
        }

        // Count how much it occurs for each rank for pairs, three of a kind, or four of a kind
        for (int i = 0; i < rankFrequency.size(); i++) {
            if (rankFrequency.get(i) == 4) {
                fourKind = true;
            } else if (rankFrequency.get(i) == 3) {
                threeKind = true;
            } else if (rankFrequency.get(i) == 2) {
                pairCount++;
            }
        }

        // Check for a royal flush and a straight
        royal = isRoyalFlush();
        straight = isStraight();

        // finds best hand 
        if (royal && flush) return "Royal Flush";
        if (flush && straight) return "Straight Flush";
        if (fourKind) return "Four of a Kind";
        if (threeKind && pairCount == 1) return "Full House";
        if (flush) return "Flush";
        if (straight) return "Straight";
        if (threeKind) return "Three of a Kind";
        if (pairCount == 2) return "Two Pair";
        if (pairCount == 1) return "A Pair";

        // finds the hardcard
        if (highCardOrNothing()) {
            return "High Card";
        }

        return "Nothing";
    }

    // Checks if the hand contains a straight 
    private boolean isStraight() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < allCards.size(); i++) {
            int value = Utility.getRankValue(allCards.get(i).getRank());
            // Add unique values to the list manually
            boolean found = false;
            for (int j = 0; j < values.size(); j++) {
                if (values.get(j) == value) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                values.add(value);
            }
        }

        // sorts it by  ascending order
        for (int i = 0; i < values.size() - 1; i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(i) > values.get(j)) {
                    int temp = values.get(i);
                    values.set(i, values.get(j));
                    values.set(j, temp);
                }
            }
        }

        // Check for five consecutive values
        for (int i = 0; i <= values.size() - 5; i++) {
            if (values.get(i) + 4 == values.get(i + 4)) {
                return true;
            }
        }
        return false;
    }

    // Checks if the hand contains a royal flush (10, J, Q, K, A in the same suit)
    private boolean isRoyalFlush() {
        boolean found10 = false;
        boolean foundJ = false;
        boolean foundQ = false;
        boolean foundK = false;
        boolean foundA = false;

        for (int i = 0; i < allCards.size(); i++) {
            String rank = allCards.get(i).getRank();
            if (rank.equals("10")) found10 = true;
            if (rank.equals("J")) foundJ = true;
            if (rank.equals("Q")) foundQ = true;
            if (rank.equals("K")) foundK = true;
            if (rank.equals("A")) foundA = true;
        }

        return found10 && foundJ && foundQ && foundK && foundA;
    }

    // Checks if the player's hand contains the highest card
    private boolean highCardOrNothing() {
        Card highCard = allCards.get(allCards.size() - 1);
        boolean hasHighCard = false;
        for (Card card : hand) {
            if (card.getRank().equals(highCard.getRank())) {
                hasHighCard = true;
            }
        }
        return hasHighCard;
    }

  
    public void sortAllCards() {
        if (allCards == null || allCards.size() <= 1) {
            return;
        }
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(allCards.get(j).getRank())) {
                    Card temp = allCards.get(i);
                    allCards.set(i, allCards.get(j));
                    allCards.set(j, temp);
                }
            }
        }
    }

    // finds the frequency for each rank
    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> frequency = new ArrayList<>();
        for (String rank : ranks) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getRank().equals(rank)) {
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency;
    }

    // Calculates the frequency of each suit in allCards
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> frequency = new ArrayList<>();
        for (String suit : suits) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getSuit().equals(suit)) {
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency;
    }

  
    @Override
    public String toString() {
        return hand.toString();
    }
}
