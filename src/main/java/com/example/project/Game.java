package com.example.project;
import java.util.ArrayList;

public class Game {

    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        // Compare the hand rankings of Player 1 and Player 2
        if (Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)) {
            return "Player 1 wins!"; // Player 1 has a higher-ranked hand
        }
        if (Utility.getHandRanking(p1Hand) < Utility.getHandRanking(p2Hand)) {
            return "Player 2 wins!"; // Player 2 has a higher-ranked hand
        }
        // If the hand rankings are equal,the winner is whoever has the better hand card
        if (Utility.getHandRanking(p1Hand) == Utility.getHandRanking(p2Hand)) {
            // Get the highest card rank from Player 1's hand
            int p1High = Math.max(
                Utility.getRankValue(p1.getHand().get(1).getRank()), 
                Utility.getRankValue(p1.getHand().get(0).getRank())
            );

            // Get the highest card rank from Player 2's hand
            int p2High = Math.max(
                Utility.getRankValue(p2.getHand().get(1).getRank()), 
                Utility.getRankValue(p2.getHand().get(0).getRank())
            );

            // Compare the highest cards of both players
            if (p1High > p2High) {
                return "Player 1 wins!"; // Player 1's highest card is better in this case
            } else if (p1High < p2High) {
                return "Player 2 wins!"; // Player 2's highest card is stronger
            } else {
                return "Tie!"; // Both players have the same highest card rank
            }
        }

        
        return "-1";
    }


    public static void play(){ //simulate card playing
    
    }
        
        

}





