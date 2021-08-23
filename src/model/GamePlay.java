/**
 * GamePlay.java
 * This is where reports the current score and game status
 *
 * @author Michael Chang
 */


package model;
import textui.*;

public class GamePlay {
    private int totalNumRelic; // 3
    private int numOfRelic;
    private String gameStatus;

    /**
     * Constructor for the game play objext
     * @param totalNumRelic
     * @param numOfRelic
     */
    public GamePlay(int totalNumRelic, int numOfRelic) {
        this.totalNumRelic = totalNumRelic;
        this.numOfRelic = numOfRelic;
        this.gameStatus = "neutral";
    }

    /**
     * get the total number of relic require
     * @return totalNumRelic
     */
    public int getTotalNumRelic() {
        return totalNumRelic;
    }

    /**
     * Set the total number of relic that are require to capture
     * @param totalNumRelic
     */
    public void setTotalNumRelic(int totalNumRelic) {
        this.totalNumRelic = totalNumRelic;
    }

    /**
     * Get the number of relic from the player
     * @return numOfRelic
     */
    public int getNumOfRelic() {
        return numOfRelic;
    }

    /**
     * set the number of relic that the player captured
     * @param numOfRelic - the updated number of relics
     */
    public void setNumOfRelic(int numOfRelic) {
        this.numOfRelic = numOfRelic;
    }

    /**
     * getter for game status
     * @return gameStatus
     */
    public String getGameStatus() {
        return gameStatus;
    }

    /**
     * Setter for game status
     * @param gameStatus
     */
    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * This is where it'll count the amount of relic and determine if the player win
     * @param maze - current maze that we're playing at
     */
    public void collectRelic(Maze maze)
    {
        this.setNumOfRelic(numOfRelic + 1);
        // if all the relics are collected
        if (numOfRelic == totalNumRelic) {
            System.out.println("Congratulations! You won!\n");
            maze.printMaze();
            Menu.viewRelicCount(this);
            this.setGameStatus("Win");
        } else {
            Relic.setRelic(maze, this);
        }
    }

    /**
     * Activating the cheat code by setting the total required relic to 1
     */
    public void cheatCodeActivate() {
        if (numOfRelic == 0) {
            this.setTotalNumRelic(1);
        }
    }

    /**
     * Check if the game is complete
     * @return a boolean value
     */
    public boolean checkGameStatus() {
        if (this.getGameStatus().equals("Win") || this.getGameStatus().equals("Lose"))
            return true;
        return false;
    }
}
