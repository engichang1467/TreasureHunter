/**
 * Relic.java
 * This is where it generates new relic after the player collects the first relic
 * @author Michael Chang
 */


package model;
import textui.*;

public class Relic {
    /**
     * randomly set up a new position for the new relic
     * @param maze the current maze that is displayed
     * @param gamePlay the gameplay object to allow us to access scores
     */
    public static void setRelic(Maze maze, GamePlay gamePlay) {
        int randX = (int)(Math.random() * maze.getRow());
        int randY = (int)(Math.random() * maze.getCol());
        char[][] tempMaze = maze.getMaze();
        char[][] tempHiddenMaze = maze.getHiddenMaze();

        // Check if its a empty space
        if (tempMaze[randX][randY] == ' ' && tempHiddenMaze[randX][randY] == '.') {
            maze.updateCell('^', randX, randY);
            maze.updateHiddenCellGuardian('^', randX, randY);
            maze.printHiddenMaze();
            Menu.viewRelicCount(gamePlay);
        } else {
            setRelic(maze, gamePlay);
        }
    }
}
