/**
 * Cell.java
 * This is the cell object that is use for tracking the neighbour node for creating a path in maze
 * @author Michael Chang
 */

package model;

public class Cell {
    private int x;
    private int y;
    private Cell parent;

    /**
     * Constructor for Cell
     * @param x - current row index
     * @param y - current column index
     * @param parent - parent cell of the cell (previous)
     */
    public Cell(int x, int y, Cell parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Getter for row index
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for column index
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * It computes the cell that is the opposite side of the parent cell
     * @return cell that's the opposite side
     */
    public Cell getOtherNode()
    {
        if (String.valueOf(x).compareTo(String.valueOf(parent.getX())) != 0)
        {
            return new Cell(x + String.valueOf(x).compareTo(String.valueOf(parent.getX())), y, this);
        }
        if (String.valueOf(y).compareTo(String.valueOf(parent.getY())) != 0)
        {
            return new Cell(x, y + String.valueOf(y).compareTo(String.valueOf(parent.getY())), this);
        }
        return null;
    }
}
