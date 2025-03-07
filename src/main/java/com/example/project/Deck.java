package com.example.project;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        // Create a new ArrayList to hold the deck of cards
        cards = new ArrayList<>();
        initializeDeck(); // Initialize the deck with all combinations of ranks and suits
        shuffleDeck(); // Shuffle the deck to randomize the card order
    }

    public ArrayList<Card> getCards() {
        // Return the list of cards in the deck
        return cards;
    }

    public void initializeDeck() {
        String[] ranks = Utility.getRanks(); // gets all ranks from the Utility class
        String[] suits = Utility.getSuits(); // getsall suits from the Utility class

       
        cards = new ArrayList<>(ranks.length * suits.length);

        // sets the array to all possible combos 
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                // Directly set the Card object at the correct index
                cards.set(i * ranks.length + j, new Card(ranks[j], suits[i]));
            }
        }
    }

    public void shuffleDeck() {
        // Shuffle the cards using the Collections library method 
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        // Remove and return the top card from the deck, or return null if the deck is empty
        if (isEmpty()) {
            return null;
        } else {
            return cards.remove(cards.size() - 1);
        }
    }

    public boolean isEmpty() {
        // Check if the deck is empty and  if it is it returns that it ios empty 
        return cards.isEmpty();
    }
}
