/**
 * Main.java
 * This is where everything is put together
 *
 * @author Michael Chang
 */

package textui;
import java.util.Scanner;
import model.*;

public class Main {
    /**
     * The main function where everything start
     * @param args
     */
    public static void main(String[] args)
    {
        int row = 14, col = 18;
        GamePlay gamePlay = new GamePlay(3, 0);
        Maze newMaze = new Maze(row,col);

        Player player = new Player(0,0);
        Guardians guardian1 = new Guardians(0, col-1);
        Guardians guardian2 = new Guardians(row-1, 0);
        Guardians guardian3 = new Guardians(row-1, col-1);

        newMaze.GenerateMazeTest();

        // Print the Menu
        Menu.viewLegendMenu();
        System.out.println();
        newMaze.printHiddenMaze();
        Menu.viewRelicCount(gamePlay);

        // testing
        boolean keepGoing = true;
        while (keepGoing) {
            // iterate input
            System.out.print("Enter your move [WASD?]: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {
                case "w":
                    player.moveUp(newMaze, gamePlay);
                    guardian1.moveRandom(newMaze, gamePlay);
                    guardian2.moveRandom(newMaze, gamePlay);
                    guardian3.moveRandom(newMaze, gamePlay);
                    if (gamePlay.checkGameStatus())
                    {
                        keepGoing = false;
                        break;
                    } else {
                        newMaze.printHiddenMaze();
                        Menu.viewRelicCount(gamePlay);
                        break;
                    }
                case "s":
                    player.moveDown(newMaze, gamePlay);
                    guardian1.moveRandom(newMaze, gamePlay);
                    guardian2.moveRandom(newMaze, gamePlay);
                    guardian3.moveRandom(newMaze, gamePlay);
                    if (gamePlay.checkGameStatus()){
                        keepGoing = false;
                        break;
                    } else {
                        newMaze.printHiddenMaze();
                        Menu.viewRelicCount(gamePlay);
                        break;
                    }
                case "a":
                    player.moveLeft(newMaze, gamePlay);
                    guardian1.moveRandom(newMaze, gamePlay);
                    guardian2.moveRandom(newMaze, gamePlay);
                    guardian3.moveRandom(newMaze, gamePlay);
                    if (gamePlay.checkGameStatus()){
                        keepGoing = false;
                        break;
                    } else {
                        newMaze.printHiddenMaze();
                        Menu.viewRelicCount(gamePlay);
                        break;
                    }
                case "d":
                    player.moveRight(newMaze, gamePlay);
                    guardian1.moveRandom(newMaze, gamePlay);
                    guardian2.moveRandom(newMaze, gamePlay);
                    guardian3.moveRandom(newMaze, gamePlay);
                    if (gamePlay.checkGameStatus()){
                        keepGoing = false;
                        break;
                    } else {
                        newMaze.printHiddenMaze();
                        Menu.viewRelicCount(gamePlay);
                        break;
                    }
                case "m":
                    newMaze.printMaze();
                    Menu.viewRelicCount(gamePlay);
                    break;
                case "c":
                    gamePlay.cheatCodeActivate();
                    break;
                case "?":
                    Menu.viewLegendMenu();
                    break;
                default:
                    System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
                    break;
            }
        }
    }
}
