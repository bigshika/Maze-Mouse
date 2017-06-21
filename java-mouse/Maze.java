import ecs100.*;
import java.util.*;
import java.lang.Math.*;
import java.awt.*;

/**
 * This makes a grid maze using Prim's Algorithm.
 * 
 * @author (Sana Oshika) 
 * @version (0.1)
 */
public class Maze
{
    // instance variables - replace the example below with your own
    private int size;
    private Cell[] maze;
    private ArrayList<Cell> inside_list;
    private ArrayList<Cell> outside_list;
    
    /**
     * Constructor for objects of class Maze
     * Builds the maze and draws it
     */
    public Maze(int size) {
        this.size = size;
        this.build_maze();
        this.draw();
    }
    
    /**
     * Returns the size of the maze
     */
    public int get_size() {
        return this.size;
    }
    
    /**
     * This method gets the exit square of the maze
     */
    public Cell get_exit() {
       return this.maze[((this.size) * (this.size) - 1)];
    }

    /**
     * This method draws the maze.
     */
    public void draw()
    {
       for(Cell cell : maze){
           cell.draw();
       }
    }
    
    /**
     * Returns an arraylist of all the cells above, below, left and right of the cell that is the 
     * parameter
     */
    public ArrayList<Cell> get_neighbours(Cell c)
    {
        //blank list to add neighbours to.
        ArrayList<Cell> neighbours = new ArrayList<Cell>();
        //adding Left neighbour. If the cell is already in the maze, it is added,
        //otherwise a new cell object is made and added to neighbours
        int[] numbers = [-1, 0, 1
        
        if (c.get_x() > 0){
          int x = c.get_x() - 1;
          int y = c.get_y();
          Cell cell = this.get_cell(x, y) != null ? this.get_cell(x, y) : new Cell(x, y, this);
          neighbours.add(cell);
        }
        //adding Right neighbour
        if (c.get_x() < (this.size - 1)) {
          int x = c.get_x() + 1;
          int y = c.get_y();
          
            if (this.get_cell(c.get_x() + 1, c.get_y()) == null){
              neighbours.add(new Cell((c.get_x() + 1), c.get_y(), this)); 
          } else {
              neighbours.add(this.get_cell((c.get_x() + 1), c.get_y())); 
            }
        }
        //adding Top neighbour
        if (c.get_y() > 0){
          if (this.get_cell(c.get_x(), c.get_y() - 1) != null){
             neighbours.add(this.get_cell(c.get_x(), (c.get_y() - 1))); 
          } else {
             neighbours.add(new Cell(c.get_x(), (c.get_y() - 1), this)); 
            }
        }
        //adding Bottom neighbour
        if (c.get_y() < (this.size - 1)){
          if (this.get_cell(c.get_x(), c.get_y() + 1) != null){
             neighbours.add(this.get_cell(c.get_x(), (c.get_y() + 1))); 
          } else {
             neighbours.add(new Cell(c.get_x(), (c.get_y() + 1), this)); 
            }
        }
        return neighbours;
    }
    
    /**
     * This will remove a random item from a list and return it
     * 
     */public Cell pop_random_element(ArrayList<Cell> list){
        int rand_num = (int)(Math.random() * list.size());     
        Cell chosen = list.remove(rand_num);
        return chosen;
    }
    
    /**
     * This will get a cell from the maze upon being given the coordinates
     * 
     */public Cell get_cell(int x, int y){
      return this.maze[(y * this.size + x)];
    }
    
    /**This method creates the connection between the two cells and if either cell is not
     * in the maze, the cell is then added to the maze
     * 
     */public void connect_cells(Cell cell1, Cell cell2){
        cell1.add_connection(cell2);
        cell2.add_connection(cell1);
        Collections.sort(cell1.get_corridors());
        Collections.sort(cell2.get_corridors());
    }
    
    /**
     * This method iterates throughs the arraylist source and sees if the cells are 
     * already in arraylist destination. If they are not, they are added to destination.
     * TODO: Rewrite this when .equals and .hashCode are fixed
     */
    public ArrayList<Cell> add_unique(ArrayList<Cell> source, ArrayList<Cell> destination) {
        for (Cell c : source){
            boolean contains = false;
            if(!destination.contains(c)) {
                destination.add(c);
            }
        }
        return destination;
    }
    
    /**
     * This method separates out the cells in input_list into whether they are currently
     * in the maze or not, and saves them as inside_list and outside_list
     */
    public void partition_cells(ArrayList<Cell> input_list){
        this.inside_list = new ArrayList<Cell>();
        this.outside_list = new ArrayList<Cell>();
        for(Cell c: input_list){
            if (this.get_cell(c.get_x(), c.get_y()) == null){
                this.outside_list.add(c);
            } else {
                this.inside_list.add(c);
            }
        }
    }
    
    /**
     * This prints the string representation of the cells, mostly for debugging
     * 
     */
    void printList(Cell[] list){
        for (Cell c: list){
            UI.println(c);
        }
    }
    
    /**
     * Calculates the index of the cell in the maze
     * 
     */
    public int index(Cell c){
       return c.get_y() * (this.size) + c.get_x();
    }
    
    /**
     * Adds a cell to the maze
     * 
     */
    public void addToMaze(Cell c){
        this.maze[this.index(c)] = c;
    }
    
    /**
     * This is the method that does the work of building the maze, using Prim's algorithm.
     * 
     */
    public void build_maze() {
        // Create a new empty ArrayList to store the maze 
        this.maze = new Cell[this.size * this.size];
       for(int y = 0; y < this.size; y++){
            for (int x = 0; x < this.size; x++){
                this.maze[(y * this.size + x)] = null;
            }
        }
        
        //Add the location (0,0) to the maze with no cells connected to it
       this.addToMaze(new Cell(0, 0, this));
        
       //Create a frontier list containing the neighbouring cells of (0,0)
       ArrayList<Cell> frontier_list = this.get_neighbours(this.get_cell(0, 0));

        // while the frontier list is not empty
        while(!frontier_list.isEmpty()){
        // pick a random cell from the frontier list

        Cell picked_cell = this.pop_random_element(frontier_list);

        // the picked cell will be the next one we add to the maze    
    
        this.addToMaze(picked_cell); 
        frontier_list.remove(picked_cell);
        
        // get the cells neighbouring the cell we picked
        ArrayList<Cell> picked_neighbours = this.get_neighbours(picked_cell);
        
        // divide the adjacent cells into a list of cells that are already 
        // inside the maze, and cells that are not in the maze (outside)  
        this.partition_cells(picked_neighbours);

        // pick a random cell from the list of cells that are in the maze   
        Cell random_inside = this.pop_random_element(this.inside_list);
        
        // add a connection between the cell that we wanted to add, and 
        // the randomly chosen cell from within the maze
        this.connect_cells(picked_cell, random_inside);
            
        // add the list of cells that was not in the maze (the adjacent cells
        // that are outside the maze) to the frontier list if they are not 
        //already in that list. */
        frontier_list = this.add_unique(this.outside_list, frontier_list);
      }
      
      //Set the entrance and exit
      this.maze[0].setEntrance();
      this.get_cell(this.size -1, this.size -1).setExit();
    }
    

}
