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

/**
 * Controller class will handle flow of game and will take user input.
 *
 * @author crnis
 */
public class Controller implements KeyListener, ActionListener {

    protected View view;
    protected Model model;
    private JButton OspreyButton;
    private JButton HarrierButton;

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
        model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
    }

    public void start() {

        view.displayStartScreen();

        View.osprey.addActionListener(ae -> {

            System.out.println("you clicked osprey");
            View previousView = view;
            view = new OspreyView();
            model = new OspreyModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
            System.out.println("osprey view/model made");
            //previousView.frame2.dispose();
            view.displayLevelStartScreen();

            previousView.frame2.dispose();

        });
        View.harrier.addActionListener(ae -> {

            System.out.println("you clicked harrier");
            View previousView = view;
            view = new HarrierView();
            model = new HarrierModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
            System.out.println("Harrier view/model made");

            previousView.frame2.dispose();

        });

        model.spawnGamePieces();

        while (model.getPlayer().isAlive()) {
            model.handleTicks();
            view.update(model.getPlayer().getX(), model.getPlayer().getY(), model.getCurrentGPs(), model.getDirection(), model.getPlayer().getHealth(), model.getPlayer().getScore());

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

    public void getClick() {

        View.osprey.addActionListener(ae -> {
            System.out.println("you clicked osprey");
            view = new OspreyView();
            model = new OspreyModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
            System.out.println("osprey view/model made");
            view.displayLevelStartScreen();

            view.frame2.dispose();

        });
        View.harrier.addActionListener(ae -> {
            System.out.println("you clicked harrier");
            view = new HarrierView();
            model = new HarrierModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
            System.out.println("Harrier view/model made");

            view.frame2.dispose();

        });
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
