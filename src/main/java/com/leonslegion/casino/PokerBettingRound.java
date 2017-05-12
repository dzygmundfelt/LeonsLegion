package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
class PokerBettingRound {

    private double highBet;
    ArrayList<PokerPlayerBettingRound> playersInRound;

    PokerBettingRound(ArrayList<PokerPlayer> players) {
        playersInRound = new ArrayList<PokerPlayerBettingRound>();
        for (PokerPlayer p : players) {
            playersInRound.add(new PokerPlayerBettingRound(p));
        }
    }

    /*
    Offers each player their options and routs their choice appropriately.
     */
    private double playerChoice(PokerPlayerBettingRound player) {
        System.out.println(player.showHand());
        String choice = InputHandler.getStringInput("You can FOLD, CALL, RAISE, or if no bets have been made, CHECK.");
        try {
            switch(choice) {
                case "FOLD": // fold
                    player.folds();
                    return -1;
                case "CALL":
                    player.player.placeBet(highBet - player.amountIn);
                    player.amountIn = highBet;
                    return 0;
                case "RAISE":
                    double raise = InputHandler.getDoubleInput("How much would you like to raise?");
                    highBet = player.player.placeBet(highBet + raise);
                    player.amountIn = highBet;
                    return highBet;
                case "CHECK":
                    if(highBet > 0) {
                        throw new Exception("You cannot check.");
                    }
                    return 0;
                default:
                    System.out.println("Not a valid choice. Read the instructions again.");
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return playerChoice(player);
        }
        return 0;
    }

    /*
    This method holds the logic that ends a round
    of betting when it becomes the turn of the last
    player who raised.
     */
    void playersMakeBets() {
        PokerPlayerBettingRound player = playersInRound.get(0);
        int turnIndex = 0;
        PokerPlayerBettingRound lastToRaise = player;
        highBet = 0;

        do { // an iteration represents a single move for
             // a player
            double amount = playerChoice(player);
            if(amount > highBet) {                  //this is a raise
                highBet = amount;
                lastToRaise = player;
            } else if(amount == -1) {               //this is a fold
                turnIndex--;
            }
            turnIndex = (turnIndex + 1) % playersInRound.size();
            player = playersInRound.get(turnIndex);
        } while(player != lastToRaise);
        // end of round
    }

}