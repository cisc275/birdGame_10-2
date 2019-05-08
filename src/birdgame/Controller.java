/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;
//I love git!
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller class will handle flow of game and will take user input.
 * @author crnis
 */
public class Controller implements KeyListener{

    protected Model model;
    protected View view;
   
public Controller(){
    view = new View();
    model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
}
public void start(){
    view.displayStartScreen();
    if(View.isOsprey){
        OspreyController o = new OspreyController();
        model = o.getModel();
        view = o.getView();
        o.start();
    }
    else{
        HarrierController h = new HarrierController();
        model = h.getModel();
        view = h.getView();
        h.start();
    }
    
}
//    public Controller() {
//        view = new View();
//        model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
//    }
//    /**
//     *start() will be called from the main() method in the Main class and will 
//     * have a loop to iterate through the game.
//     */
//    public void start() {
//    	model.spawnGamePieces();
//    	while(model.getPlayer().isAlive()){
//            model.handleTicks();
//            view.update(model.getPlayer().getX(), model.getPlayer().getY(),model.getCurrentGPs(), model.getDirection(),model.getPlayer().getHealth(), model.getPlayer().getScore());
//        }
//    }

    /**
     * keyTyped() will handle a KeyEvent that the user might perform.
     * @param e is a KeyEvent that a user might input (up, down). 
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * keyPressed() will handle the user input if they hold down the arrowkeys to 
     * move the bird.
     * @param e is a KeyEvent that a user might input (up, down).
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_UP){
            Model.setDirection(Direction.UP);
        }
        else if(key == KeyEvent.VK_DOWN){
            Model.setDirection(Direction.DOWN);
        }
    }

    /**
     * keyReleased will not be used for our game
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        Model.setDirection(null);
    }
}

//    public View getView() {
//    	return view;
//    }
//    
//    public Model getModel() {
//    	return model;
//    }
//}
