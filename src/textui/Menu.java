/**
 * Menu.java
 * This is where we output the menu options
 *
 * @author Michael Chang
 */

package textui;
import model.*;

public class Menu {
    /**
     * Show the legend menu
     */
    public static void viewLegendMenu(){
        System.out.println("DIRECTIONS:\n\tCollect 3 relics!");
        System.out.println("LEGEND:");
        System.out.println("\t#: Wall\n\t@: You (the treasure hunter)\n\t!: Guardian\n\t^: Relic\n\t. Unexplored space");
        System.out.println("MOVES:");
        System.out.println("\tUse W (up), A (left), S (down) and D (right) to move.");
        System.out.println("\t(You must press enter after each move).");
    }

    /**
     * This shows the relic score of the player
     * @param gamePlay - the current status and score of the game
     */
    public static void viewRelicCount(GamePlay gamePlay){
        System.out.println("Total number of relics to be collected: " + gamePlay.getTotalNumRelic());
        System.out.println("Number of relics currently in possession: " + gamePlay.getNumOfRelic());
    }
}
