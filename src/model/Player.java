/**
 * Player.java
 * This is where we have the mechanics of the player's movement
 * @author Michael Chang
 *
 */

package model;
import textui.*;

public class Player {
    private int x;
    private int y;

    /**
     * Constructor of the player class
     * @param x the row index of the player
     * @param y the column index of the player
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the row index of the player
     * @param x the row index
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the column index of the player
     * @param y the row index
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * allow player to move up the maze
     * @param maze the current maze that we have so far
     * @param gamePlay the game play object for changing status and displaying scores
     */
    public void moveUp(Maze maze, GamePlay gamePlay)
    {
        char[][] tempMaze = maze.getMaze();
        int currX = x, currY = y, updatedX = currX-1;
        if (updatedX < 0) {
            System.out.println("out of bound");
        }
        else
        {
            // check if its a wall
            if (tempMaze[updatedX][currY] == '#') {
                System.out.println("Invalid move: you cannot move through walls!");
            }
            // check if its the guardian
            if (tempMaze[updatedX][currY] == '!') {
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', updatedX, currY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
            // check if got the treasure
            if (tempMaze[updatedX][currY] == '^') {
                maze.updateCell(' ', currX, currY);
                this.setX(updatedX);
                maze.updateCell('@', updatedX, currY);
                maze.revealHiddenCell(updatedX, currY);
                gamePlay.collectRelic(maze);
            }
            // check if the space is walkable
            if (tempMaze[updatedX][currY] == ' ') {
                // update
                maze.updateCell(' ', currX, currY);
                this.setX(updatedX);
                maze.updateCell('@', updatedX, currY);
                maze.revealHiddenCell(updatedX, currY);
            }
        }
    }

    /**
     * allow player to move down the maze
     * @param maze the current maze that we have so far
     * @param gamePlay the game play object for changing status and displaying scores
     */
    public void moveDown(Maze maze, GamePlay gamePlay)
    {
        char[][] tempMaze = maze.getMaze();
        int currX = x, currY = y, updatedX = currX+1;
        if (updatedX > maze.getRow()) {
            System.out.println("out of bound");
        } else {
            // check if its a wall
            if (tempMaze[updatedX][currY] == '#') {
                System.out.println("Invalid move: you cannot move through walls!");
            }
            // check if its a enemy
            if (tempMaze[updatedX][currY] == '!') {
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', updatedX, currY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
            // check if got the treasure
            if (tempMaze[updatedX][currY] == '^') {
                // update position on maze
                maze.updateCell(' ', currX, currY);
                this.setX(updatedX);
                maze.updateCell('@', updatedX, currY);
                maze.revealHiddenCell(updatedX, currY);
                gamePlay.collectRelic(maze);
            }
            // check if the space is walkable
            if (tempMaze[updatedX][currY] == ' ') {
                // update
                maze.updateCell(' ', currX, currY);
                this.setX(updatedX);
                maze.updateCell('@', updatedX, currY);
                maze.revealHiddenCell(updatedX, currY);
            }
        }
    }

    /**
     * allow player to move to the left of the maze
     * @param maze the current maze that we have so far
     * @param gamePlay the game play object for changing status and displaying scores
     */
    public void moveLeft(Maze maze, GamePlay gamePlay)
    {
        char[][] tempMaze = maze.getMaze();
        int currX = x, currY = y, updateY = currY-1;
        if (updateY < 0) {
            System.out.println("out of bound");
        }
        else {
            // check if its a wall
            if (tempMaze[currX][updateY] == '#') {
                System.out.println("Invalid move: you cannot move through walls!");
            }
            // check if its a enemy
            if (tempMaze[currX][updateY] == '!') {
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', currX, updateY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
            // check if got the treasure
            if (tempMaze[currX][updateY] == '^') {
                maze.updateCell(' ', currX, currY);
                this.setY(updateY);
                maze.updateCell('@', currX, updateY);
                maze.revealHiddenCell(currX, updateY);
                gamePlay.collectRelic(maze);
            }
            // check if its a empty space
            if (tempMaze[currX][updateY] == ' ') {
                // update
                maze.updateCell(' ', currX, currY);
                this.setY(updateY);
                maze.updateCell('@', currX, updateY);
                maze.revealHiddenCell(currX, updateY);
            }
        }
    }

    /**
     * allow player to move to the right of the maze
     * @param maze the current maze that we have so far
     * @param gamePlay the game play object for changing status and displaying scores
     */
    public void moveRight(Maze maze, GamePlay gamePlay)
    {
        char[][] tempMaze = maze.getMaze();
        int currX = x, currY = y, updateY = currY+1;
        if (updateY > maze.getCol()) {
            System.out.println("out of bound");
        } else {
            // check if its a wall
            if (tempMaze[currX][updateY] == '#') {
                System.out.println("Invalid move: you cannot move through walls!");
            }
            // check if its a guardian
            if (tempMaze[currX][updateY] == '!') {
                System.out.println("Oh no! The hunter has been killed!\n");
                maze.updateCell(' ', currX, currY);
                maze.updateCell('X', currX, updateY);
                maze.printMaze();
                Menu.viewRelicCount(gamePlay);
                gamePlay.setGameStatus("Lose");
                System.out.println("GAME OVER... please try again.");
            }
            // check if got the treasure
            if (tempMaze[currX][updateY] == '^') {
                maze.updateCell(' ', currX, currY);
                this.setY(updateY);
                maze.updateCell('@', currX, updateY);
                maze.revealHiddenCell(currX, updateY);
                gamePlay.collectRelic(maze);
            }
            // check if the space is walkable
            if (tempMaze[currX][updateY] == ' ') {
                maze.updateCell(' ', currX, currY);
                this.setY(updateY);
                maze.updateCell('@', currX, updateY);
                maze.revealHiddenCell(currX, updateY);

            }
        }
    }
}
