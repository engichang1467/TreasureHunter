/**
 * Guardians.java
 * This is where we control the Guardian's movement
 *
 * @author Michael Chang
 */

package model;

import textui.*;
import java.util.ArrayList;
import java.util.List;

public class Guardians {
    private int x;
    private int y;

    /**
     * Constructor of Guardian
     * @param x
     * @param y
     */
    public Guardians(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set new row index for guardian
     * @param x - row index
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set new column index for quardian
     * @param y - column index
     */
    public void setY(int y) {
        this.y = y;
    }

    // move guardian randomly across

    /**
     * Allow the guardian for move randomly around the maze
     * @param maze - current maze that the player is playing
     * @param gamePlay - current status and score of the game
     */
    public void moveRandom(Maze maze, GamePlay gamePlay) {

        char[][] tempMaze = maze.getMaze();

        // create an array for available direction
        List<Integer> direction2 = new ArrayList<>();

        // Check the coordinate if it went out of bounds
        if (y-1 >= 0){
            if (tempMaze[x][y-1] == ' ')
                direction2.add(3); // left
        }
        if (y+1 < maze.getCol()){
            if (tempMaze[x][y+1] == ' ')
                direction2.add(4); // right
        }
        if (x-1 >= 0 ){
            if (tempMaze[x-1][y] == ' ')
                direction2.add(1); // up
        }
        if (x+1 < maze.getRow()){
            if (tempMaze[x+1][y] == ' ')
                direction2.add(2); // down
        }

        int randIndex = (int)(Math.random() * direction2.size());
        int rand = direction2.get(randIndex);

        switch (rand) {
            case 1:
                this.moveUp(maze, gamePlay);
                break;
            case 2:
                this.moveDown(maze, gamePlay);
                break;
            case 3:
                this.moveLeft(maze, gamePlay);
                break;
            case 4:
                this.moveRight(maze, gamePlay);
                break;
            default:

        }
    }

    /**
     * Allow the guardian to move up
     * @param maze - current maze that the player is playing
     * @param gamePlay - current status and score of the game
     */
    public void moveUp(Maze maze, GamePlay gamePlay) {
        char[][] tempMaze = maze.getMaze(), tempHidMaze = maze.getHiddenMaze();
        int currX = x, currY = y, updatedX = currX - 1;
        if (updatedX > 0) {
            // check if the space is walkable
            if (tempMaze[updatedX][currY] == ' ') {
                // update
                char value = tempHidMaze[updatedX][currY];
                maze.updateCell(' ', currX, currY);
                maze.updateHiddenCellGuardian(value, currX, currY);
                this.setX(updatedX);
                maze.updateCell('!', updatedX, currY);
                maze.updateHiddenCellGuardian('!', updatedX, currY);
            }
            if (tempMaze[updatedX][currY] == '@'){
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', updatedX, currY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
        }
    }

    /**
     * Allow the guardian to move down
     * @param maze - current maze that the player is playing
     * @param gamePlay - current status and score of the game
     */
    public void moveDown(Maze maze, GamePlay gamePlay) {
        char[][] tempMaze = maze.getMaze(), tempHidMaze = maze.getHiddenMaze();
        int currX = x, currY = y, updatedX = currX+1;
        if (updatedX < maze.getRow()) {
            // check if the space is walkable
            if (tempMaze[updatedX][currY] == ' ') {
                // update
                char value = tempHidMaze[updatedX][currY];
                maze.updateCell(' ', currX, currY);
                maze.updateHiddenCellGuardian(value, currX, currY);
                this.setX(updatedX);
                maze.updateCell('!', updatedX, currY);
                maze.updateHiddenCellGuardian('!', updatedX, currY);
            }
            if (tempMaze[updatedX][currY] == '@'){
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', updatedX, currY);
                // keep going
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
        }
    }

    /**
     * Allow the guardian to move left
     * @param maze - current maze that the player is playing
     * @param gamePlay - current status and score of the game
     */
    public void moveLeft(Maze maze, GamePlay gamePlay) {
        char[][] tempMaze = maze.getMaze(), tempHidMaze = maze.getHiddenMaze();
        int currX = x, currY = y, updateY = currY-1;
        if (updateY < 0) {
            System.out.println("out of bound");
        }
        else {
            // check if the space is walkable
            if (tempMaze[currX][updateY] == ' ') {
                // update
                char value = tempHidMaze[currX][updateY];
                maze.updateCell(' ', currX, currY);
                maze.updateHiddenCellGuardian(value, currX, currY);
                this.setY(updateY);
                maze.updateCell('!', currX, updateY);
                maze.updateHiddenCellGuardian('!', currX, updateY);
            }
            if (tempMaze[currX][updateY] == '@'){
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', currX, updateY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
        }
    }

    /**
     * Allow the guardian to move right
     * @param maze - current maze that the player is playing
     * @param gamePlay - current status and score of the gamey
     */
    public void moveRight(Maze maze, GamePlay gamePlay){
        char[][] tempMaze = maze.getMaze();
        int currX = x, currY = y, updateY = currY+1;
        if (updateY < maze.getCol()) {
            System.out.println("out of bound");
        }
        else {
            // check if the space is walkable
            if (tempMaze[currX][updateY] == ' ') {
                // update
                maze.updateCell(' ', currX, currY);
                maze.updateHiddenCellGuardian('.', currX, currY);
                this.setY(updateY);
                maze.updateCell('!', currX, updateY);
                maze.updateHiddenCellGuardian('!', currX, updateY);
            }
            if (tempMaze[currX][updateY] == '@'){
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', currX, updateY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
        }
    }
}
