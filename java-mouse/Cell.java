import java.util.*;
import ecs100.*;
import java.awt.*;
/**
 * This class describes Cells, the building block of the Maze.
 * 
 * @author (Sana Oshika) 
 * @version (0.1)
 */
public class Cell implements Comparable<Cell>
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private ArrayList<Cell> corridors;
    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;
    public static final int MAZE_TOP = 50;
    public static final int MAZE_LEFT = 50;
    public static final int CELL_SIZE = 25;
    private Maze maze;
    
    /**
     * Constructor for objects of class Cell
     */
    public Cell(int x, int y, Maze m){
     this.x = x;
     this.y = y;
     this.corridors = new ArrayList<Cell>();
     this.maze = m;
    }
    
    public int get_top(){
        return this.MAZE_TOP;
    }
    
    public int get_left(){
        return this.MAZE_LEFT;
    }
    
    public int get_cell_size(){
        return this.CELL_SIZE;
    }
    
    /**Returns the x coordinate of the cell
     * 
     */
    public int get_x(){
      return this.x;  
    }
    
    /**Returns the y coordinate of the cell
     * 
     */
    public int get_y(){
      return this.y;  
    }
    
    @Override
    public boolean equals(Object c){
        if (this instanceof Cell) {
          if ((this.x == ((Cell)c).get_x()) && (this.y == ((Cell)c).get_y())){
              return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.x + this.y * (this.maze.get_size());
    }

    /**
     * Returns all cells that are linked to this cell
     */
    public ArrayList<Cell> get_corridors()
    {
        Collections.sort(this.corridors);
        return this.corridors;
    }
    
    /**This exists only to be called by Maze.connect_cells()
     * It adds a connection between the object cell and the parameter cell
     */public void add_connection(Cell other){
       this.corridors.add(other);
       if(other.get_x() < this.x){
            this.left = true;
            //other.right = true;
        } else if (other.get_x() > this.x){
            this.right = true;
            //other.left = true; 
        } else if(other.get_y() < this.y){
            this.top = true;
            //other.bottom = true;
         } else if (other.get_y() > this.y){
            this.bottom = true;
            //other.top = true;
       }
    }
    
    /**A string representation of the cell, mostly for debugging purposes
     * 
     */public String toString(){
       String s = "(" + this.x + ", " + this.y + "): ";
       if(this.corridors.size() == 0){
           s = s + "[]";
           return s;
        } else {
            s = s + "[";
        }
       for (Cell c : this.corridors){
           s = s + "(" + c.x + ", " + c.y + ")";
           if (this.corridors.size() > 1 && this.corridors.indexOf(c) < (this.corridors.size() - 1)){
               s = s + ", ";
            }
        }
       s = s + "]";
       return s;
    }
    
    /**Does the work of drawing the cell
     * 
     */public void draw(){
        //UI.drawRect(this.MAZE_LEFT + this.x * cell_size, this.MAZE_TOP + this.y * cell_size, cell_size, cell_size);
        if (!top){
           UI.drawLine(this.MAZE_LEFT + this.x * this.CELL_SIZE, this.MAZE_TOP + this.y * this.CELL_SIZE,
           this.MAZE_LEFT + (this.x + 1) * this.CELL_SIZE, this.MAZE_TOP + this.y * this.CELL_SIZE);
        }
        if (!bottom){
           UI.drawLine(this.MAZE_LEFT + this.x * this.CELL_SIZE, this.MAZE_TOP + (this.y + 1) * this.CELL_SIZE,
           this.MAZE_LEFT + (this.x + 1) * this.CELL_SIZE, this.MAZE_TOP + (this.y + 1) * this.CELL_SIZE); 
        }
        if (!right){
           UI.drawLine(this.MAZE_LEFT + (this.x + 1) * this.CELL_SIZE, this.MAZE_TOP + (this.y)* this.CELL_SIZE,
           this.MAZE_LEFT + (this.x + 1) * this.CELL_SIZE, this.MAZE_TOP + (this.y + 1) * this.CELL_SIZE); 
        }
        if (!left){
           UI.drawLine(this.MAZE_LEFT + (this.x) * this.CELL_SIZE, this.MAZE_TOP + (this.y)* this.CELL_SIZE,
           this.MAZE_LEFT + (this.x) * this.CELL_SIZE, this.MAZE_TOP + (this.y + 1) * this.CELL_SIZE);   
        }
    }
    
    /**For sorting cells. Not properly implemented yet
     * 
     */public int compareTo(Cell c){
        if(this.x < c.get_x()){
            return -1;
        } else if (this.x > c.get_x()){
            return 1;
        } else {
            if(this.y < c.get_y()){
                return -1;
            } else if (this.y > c.get_y()){
                return 1;
            } else {
                return 0;
            }
        }
        
    }
    
    /**Makes the cell the entrance by removing the left wall
     * 
     */public void setEntrance(){
        this.left = true;
    }
    
    /**Makes cell the exit by removing right wall
     * 
     */public void setExit(){
        this.right = true;
    }
    
}
