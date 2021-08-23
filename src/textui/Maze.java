/**
 * Maze.java
 * This is where the maze is create and manage
 *
 * @author Michael Chang
 */

package textui;
import java.util.ArrayList;
import model.*;

public class Maze {
    private int col;
    private int row;
    private char[][] maze;
    private char[][] hiddenMaze;

    /**
     * constructor for maze object
     * @param row - amount of rows in our maze
     * @param col - amount of columns in our maze
     */
    public Maze(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.maze = new char[row][col];
        this.hiddenMaze = new char[row][col];
    }

    /**
     * get the columns of the maze
     * @return columns
     */
    public int getCol() {
        return col;
    }

    /**
     * get the amount of rows from the maze
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *  get the actual maze
     * @return
     */
    public char[][] getMaze() {
        return maze;
    }

    /**
     * get the hidden maze
     * @return hiddenMaze
     */
    public char[][] getHiddenMaze() {
        return hiddenMaze;
    }

    /**
     * Update the player's position in the maze
     * @param data - the updated symbol of the player
     * @param x -  the row index that will be updated
     * @param y -  the column index that will be updated
     */
    public void updateCell(char data, int x, int y)
    {
        maze[x][y] = data;
    }

    /**
     * Update Guardian's position
     * @param data - the symbol of the guardian !
     * @param x - the row index of row
     * @param y - the col index of col
     */
    public void updateHiddenCellGuardian(char data, int x, int y)
    {
        hiddenMaze[x][y] = data;
    }

    /**
     * It reveals the surrounding of the treasure hunter
     * @param x - the row index of the player
     * @param y - the column index of the player
     */
    public void revealHiddenCell( int x, int y)
    {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x+i >= 0 && x+i < row) && (y+j >= 0 && y+j < col)){
                    hiddenMaze[x+i][y+j] = maze[x+i][y+j];
                }
            }
        }
    }

    /**
     * add neighbour node helper function for generateMaze()
     * @param neighbours - chain of neighbour
     * @param maze -  current maze
     * @param origin - the parent cell
     */
    public static void addNeighbour(ArrayList<Cell> neighbours, char[][] maze, Cell origin){
        // iterate through direct neighbors of node, same as earlier
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0)
                    continue;
                try {
                    if (maze[origin.getX() + x][origin.getY() + y] == ' ') continue;
                } catch (Exception e) {
                    continue;
                }
                neighbours.add(new Cell(origin.getX() + x, origin.getY() + y, origin));
            }
        }
    }

    /**
     * Using Prim algorithm to generate a maze
     * Reference:
     *      https://www.geeksforgeeks.org/hashtable-in-java/
     *      https://en.wikipedia.org/wiki/Maze_generation_algorithm
     */
    public void GenerateMazeTest()
    {
        // create maze with only walls
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                maze[i][j] = '#';
            }
        }

        // Initialize the starting point (place player here)
        Cell startPoint = new Cell(0, 0, null);

        maze[0][0] = '@';

        // add 3 guardians
        maze[0][col-1] = '!';
        maze[row-1][0] = '!';
        maze[row-1][col-1] = '!';

        // build a chain of neighbours
        ArrayList<Cell> neighbours = new ArrayList<Cell>();

        addNeighbour(neighbours, maze, startPoint);

        Cell endPoint = null;

        // Build a path for our maze
        while(!neighbours.isEmpty())
        {
            // pick a random node
            int randIndex = (int)(Math.random() * neighbours.size());
            Cell randCurrNode = neighbours.remove(randIndex);
            Cell oppositeNode = randCurrNode.getOtherNode();

            try {
                // Check both nodes are walls
                if (maze[randCurrNode.getX()][randCurrNode.getY()] == '#'){
                    if (maze[oppositeNode.getX()][oppositeNode.getY()] == '#'){

                        // Create a path between these nodes
                        maze[randCurrNode.getX()][randCurrNode.getY()] = ' ';
                        maze[oppositeNode.getX()][oppositeNode.getY()] = ' ';

                        endPoint = oppositeNode;

                        addNeighbour(neighbours, maze, oppositeNode);
                    }
                }
            } catch(Exception e){

            }

            // this place will be our treasure
            if (neighbours.isEmpty()) {
                int lastX = endPoint.getX();
                int lastY = endPoint.getY();
                maze[lastX][lastY] = '^';
            }
        }

        // last row clear
        for (int i = 1; i < col-1; i++)
        {
            if (Math.random() < 0.5)
                maze[row-1][i] = ' ';
        }

        // check if the maze is legal
        if (!checkMaze(row, col, maze))
        {
            GenerateMazeTest();
        }

        // copy our maze into our hiddenMaze
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (maze[i][j] == '@' || maze[i][j] == '!' || maze[i][j] == '^') {
                    hiddenMaze[i][j] = maze[i][j];
                } else {
                    hiddenMaze[i][j] = '.';
                }
            }
        }

        // unreveal the neighbour part of the hunter
        hiddenMaze[0][1] = maze[0][1];
        hiddenMaze[1][0] = maze[1][0];
        hiddenMaze[1][1] = maze[1][1];
    }


    /**
     * Check if the maze is legal
     * @param row - amount of rows that the maze has
     * @param col - amount of columns that the maze has
     * @param maze -  current maze
     * @return a true of false answer
     */
    public static boolean checkMaze(int row, int col, char[][] maze)
    {
        for (int i = 0; i < row-1; i++){
            for (int j = 0; j < col-1; j++)
            {
                if (maze[i][j] == maze[i][j+1] && maze[i][j] == maze[i+1][j] && maze[i][j] == maze[i+1][j+1])
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * it prints the maze, used for (m)
     */
    public void printMaze() {
        int newRow = row+2, newCol = col+2;

        System.out.println("Maze:");
        for (int i = 0; i < newCol; i++)
            System.out.print('#');
        System.out.println();
        for (int j = 0; j < row; j++)
        {
            System.out.print('#');
            for (int k = 0; k < col; k++){
                System.out.print(maze[j][k]);
            }
            System.out.print("#\n");
        }
        for (int i = 0; i < newCol; i++)
            System.out.print("#");
        System.out.println();
    }

    /**
     * it prints the hidden maze that the player will see
     */
    public void printHiddenMaze() {
        int newCol = col+2;

        System.out.println("Maze:");
        for (int i = 0; i < newCol; i++)
            System.out.print('#');
        System.out.println();
        for (int j = 0; j < row; j++)
        {
            System.out.print('#');
            for (int k = 0; k < col; k++){
                System.out.print(hiddenMaze[j][k]);
            }
            System.out.print("#\n");
        }
        for (int i = 0; i < newCol; i++)
            System.out.print("#");
        System.out.println();
    }
}
