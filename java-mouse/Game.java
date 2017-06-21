import ecs100.*;
import java.awt.*;
/**
 * This handles the game component. The mouse movement and generating mazes is controlled here,
 * and the event handler is here.
 * 
 * @author (Sana Oshika) 
 * @version (0.1)
 */
public class Game implements UIKeyListener
{
    // instance variables - replace the example below with your own
    private Maze maze;
    private Mouse mouse;

    /**
     * Constructor for objects of class Game
     */
    public Game() {
      UI.setKeyListener(this);
      UI.clearGraphics();
      UI.setImmediateRepaint(false);
      boolean newGame = true;
      while(newGame) {
         this.startGame();
         newGame = UI.askBoolean("Would you like to play again?");
      }
    }
    
    /**Starts the game by building a new maze, making a new mouse object and controlling the 
     * win condition
     */public void startGame(){
        int size = 0;
        UI.clearGraphics();
        while(size < 1){
            size = UI.askInt("What size maze would you like to make?");
        }
        this.maze = new Maze(size);
        this.mouse = new Mouse(maze.get_cell(0, 0));
        UI.repaintGraphics();
        boolean game = true;
        while(game){
           Cell now = this.mouse.get_current();
           if (now.equals(maze.get_exit())){
               UI.println("Congratulations! You won!");
               game = false;
           }
        }
    }
    
    /**The key listener, which controls the rat movement
     * 
     */public void keyPerformed(String key) {
        if (key.equals("w")) {
            Cell above = new Cell(mouse.get_current().get_x(), mouse.get_current().get_y() - 1, maze);
            for (Cell c : mouse.get_current().get_corridors()){
                if(above.equals(c)){
                   mouse.erase();
                   mouse.move(c);
                }
            }
            } else if (key.equals("s")) {
            Cell below = new Cell(mouse.get_current().get_x(), mouse.get_current().get_y() + 1, maze);
            for (Cell c : mouse.get_current().get_corridors()){
                if(below.equals(c)){
                    mouse.erase();
                    mouse.move(c);
                }
            }    
            
        }  else if (key.equals("a")){
           Cell left = new Cell(mouse.get_current().get_x() - 1, mouse.get_current().get_y(), maze);
           for (Cell c : mouse.get_current().get_corridors()){
                if(left.equals(c)){
                    mouse.erase();
                    mouse.turn_left();
                    mouse.move(c);
                }
            }   
        } else if (key.equals("d")){
           Cell right = new Cell(mouse.get_current().get_x() + 1, mouse.get_current().get_y(), maze);
           for (Cell c : mouse.get_current().get_corridors()){
                if(right.equals(c)){
                   mouse.erase();
                   mouse.turn_right();
                   mouse.move(c);
                }
            }   
        }
        mouse.draw();
        UI.repaintGraphics();
    }
    
  public static void main(String[] arguments) {
    Game game = new Game();
  }
} 
