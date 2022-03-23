/* (C) 2022 */
package io.aroundij.tennisleague;

import io.aroundij.tennisleague.domain.GameSet;
import io.aroundij.tennisleague.domain.Player;
import io.aroundij.tennisleague.service.PlayGame;
import io.aroundij.tennisleague.service.ScoreService;
import io.aroundij.tennisleague.service.impl.PlayClassicalGameImpl;
import io.aroundij.tennisleague.service.impl.ScoreClassicalGameImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Let's assume that 0 is a miss and 1 is a score
        // The characters in an even position are the scores PlayerA
        // The characters in an odd position are the scores PlayerB
        // O position is included

        Scanner scanner = new Scanner(System.in);
        PlayGame playGame = new PlayClassicalGameImpl();
        ScoreService scoreService = new ScoreClassicalGameImpl();
        GameSet match = playGame.startMatch();
        int i = 0;

        System.out.println("This is a Tennis Match !");
        System.out.println("There are two Player : Player A and Player B");
        System.out.println("Each player has to choose between the characters \"1\" and \"0\"");
        System.out.println("Character \"0\" means that the current player scores");
        System.out.println("Character \"1\" means that the current player scores");

        System.out.println("You can exit the Match at any moment by clicking on \"Ctrl + C\"");

        System.out.println("Let's Start");

        while (true) {
            Player playerToMove = i % 2 == 0 ? Player.PLAYER_A : Player.PLAYER_B;
            System.out.println(
                    playerToMove + " to Move (enter \"1\" to score, or \"0\" to miss) : ");
            String input = scanner.next();
            if ("1".equals(input)) {
                playGame.playerScored(playerToMove, match);
                i++;
            } else if ("0".equals(input)) {
                i++;
            } else {
                System.out.println("Wrong Move !");
            }
            System.out.println("The current score : " + scoreService.getScore(match));
        }
    }
}
