import ecs100.*;
/**
 * This class describes the mouse, which is used in Game.
 * 
 * @author (Sana Oshika) 
 * @version (0.1)
 */
public class Mouse
{
    // instance variables - replace the example below with your own
    private Cell current;
    private int head = 7;
    private int body = 11;
    private int x_tail = 5;
    private int y_tail = 3;
    private boolean facingRight = true;
    
    /**
     * Constructor for objects of class Mouse
     */
    public Mouse(Cell c) {
        this.current = c;
        this.draw();
    }
    
    /**Constructs a mouse that is in the origin square. Mostly used for debugging.
     * 
     */Mouse(){
       //this.current = new Cell(0, 0);
       this.draw(); 
    }
    
    /**Gets the current cell the mouse is in.
     * 
     */public Cell get_current(){
        return this.current;
    }
    
    /**Moves the mouse to the given cell
     * 
     */public void move(Cell c){
        this.current = c;
    }
    
    /**
     * Turns the mouse left
     * 
     */
    public void turn_left(){
        this.facingRight = facingRight ? false : false;
    }
    
    /**
     * Turns the mouse right
     * 
     */
    public void turn_right(){
       this.facingRight = !facingRight ? true : true;
    }

    /**
     * Draws the mouse using images ratleft.png and ratright.png
     */
    public void draw(){
      int x_offset = current.get_left() + this.current.get_x() * current.get_cell_size();
      int y_offset = current.get_top() + this.current.get_y() * current.get_cell_size();
      
      if(!this.facingRight){
         UI.drawImage("ratleft.png", x_offset + 1, y_offset + 1);
        } else {
         UI.drawImage("ratright.png", x_offset + 1, y_offset + 1);   
        }
    }
    
    /**Erases the mouse
     * 
     */public void erase(){
      UI.eraseRect(Cell.MAZE_LEFT + this.current.get_x() * Cell.CELL_SIZE + 1, Cell.MAZE_TOP + this.current.get_y() * Cell.CELL_SIZE + 1, Cell.CELL_SIZE -2, Cell.CELL_SIZE -2); 
    }
}
