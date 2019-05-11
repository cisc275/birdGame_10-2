/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//I love git!
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Controller class will handle flow of game and will take user input.
 *
 * @author crnis
 */
public class Controller implements KeyListener, ActionListener {

    private View view;
    private Model model;
    private JButton OspreyButton;
    private JButton HarrierButton;
    private AbstractAction arrowKeyAction;
    private ImageIcon imgOsprey = new ImageIcon("DNERRGameBackground.jpg");
    private ImageIcon imgOsprey2 = new ImageIcon("DNERRGameBackgroundMirror.jpg");
    private ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
    private ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");

//    protected Model OspreyModel;
//    protected View OspreyView;
//    protected Model HarrierModel;
//    protected View HarrierView;
    public Controller(){
        OspreyButton = new JButton("Play as Osprey");
        HarrierButton = new JButton("Play as Harrier");
        OspreyButton.addActionListener(this);
        HarrierButton.addActionListener(this);
        view = new View(this);
        model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getBirdWidth(), view.getBirdHeight());
        view.setPanel("START");
        arrowKeyAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                while(model.getPlayer().isAlive()){
                    model.handleTicks();
                    view.update(model.getPlayer().getX(), model.getPlayer().getY(), 
                        model.getCurrentGPs(), model.getDirection(), 
                        model.getPlayer().getHealth(), model.getPlayer().getScore());
                }
            }
        };
    }
    
    
    public void start() {
    	//model.spawnGamePieces();
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == OspreyButton){
            model.spawnOspreyGamePieces();
            view.setBackground(imgOsprey, imgOsprey2);
            view.setPanel("OSPREY");
            
        }
        else if(e.getSource() == HarrierButton){
            model.spawnHarrierGamePieces();
            view.setBackground(imgHarrier, imgHarrier2);
            view.setPanel("HARRIER");
        }
    }
//    public void start() {
//        model.spawnGamePieces();
//    }

//    public Controller() {
//        view = new View();
//        model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
//    }
//    /**
//     *start() will be called from the main() method in the Main class and will 
//     * have a loop to iterate through the game.
//     */
//    public void start() {
//    	m.spawnGamePieces();
//    	while(m.getPlayer().isAlive()){
//            model.handleTicks();
//            view.update(model.getPlayer().getX(), model.getPlayer().getY(),model.getCurrentGPs(), model.getDirection(),model.getPlayer().getHealth(), model.getPlayer().getScore());
//        }
//    }
    /**
     * keyTyped() will handle a KeyEvent that the user might perform.
     *
     * @param e is a KeyEvent that a user might input (up, down).
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * keyPressed() will handle the user input if they hold down the arrowkeys
     * to move the bird.
     *
     * @param e is a KeyEvent that a user might input (up, down).
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            Model.setDirection(Direction.UP);
        } else if (key == KeyEvent.VK_DOWN) {
            Model.setDirection(Direction.DOWN);
        }
    }

    /**
     * keyReleased will not be used for our game
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        Model.setDirection(null);
    }

    public JButton getOspreyButton(){
        return OspreyButton;
    }
    public JButton getHarrierButton(){
        return HarrierButton;
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
