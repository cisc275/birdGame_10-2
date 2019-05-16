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
    private JButton Round1Button;
    private static JButton Round2Button;
    private static JButton ReturnToStart;
    private AbstractAction arrowKeyAction;
    private ImageIcon imgOsprey = new ImageIcon("images/BirdImages/OspreyBackground2.jpg");
    private ImageIcon imgOsprey2 = new ImageIcon("images/BirdImages/OspreyBackground2Mirror.jpg");
    private ImageIcon imgOsprey3 = new ImageIcon("images/BirdImages/OspreyBackground.png");
    private ImageIcon imgOsprey4 = new ImageIcon("images/BirdImages/OspreyBackgroundMirror.png");
    private ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
    private ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");
    private int birdsPlayed=0;
    private boolean nextRound = false;
    
    public Controller(){
        OspreyButton = new JButton("Play as Osprey");
        HarrierButton = new JButton("Play as Harrier");
        Round1Button = new JButton("Ready to Play Level 1");
        Round2Button = new JButton("Ready to Play Level 2");
        ReturnToStart = new JButton("Return to Start Screen");
        OspreyButton.addActionListener(this);
        HarrierButton.addActionListener(this);
        Round1Button.addActionListener(this);
        Round2Button.addActionListener(this);
        ReturnToStart.addActionListener(this);
        view = new View(this);
        model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getBirdWidth(), view.getBirdHeight());
        view.setPanel("START");
//        if(view.getPanel().equals("OSPREY_ROUND_ONE") || view.getPanel().equals("HARRIER_ROUND")){
//            start();
//        }


        
    }
    
    
    void start() {
        //System.out.println("start reached");
    	while(model.getPlayer().isAlive() && !nextRound){
            //System.out.println("enters while loop");
            model.handleTicks();
            view.update(model.getPlayer().getX(), model.getPlayer().getY(), 
                       model.getCurrentGPs(), model.getDirection(), 
                       model.getPlayer().getHealth(), model.getPlayer().getScore());
            if(model.getPlayer().alive==false) {
        		view.setIsOspreyRound1Over(false);
        		view.setIsOspreyRound2Over(false);
        	}
            if(view.getIsOspreyRound1Over()){
                //System.out.println("before map 1 to 2");
                view.setPanel("MAP_1_TO_2");
                //view.setIsOspreyRound1Over(false);
                //System.out.println("after map 1 to 2");
            }
            else if(view.getIsOspreyRound2Over()){
            	//System.out.println("Hello");
            	
                view.setPanel("MAP_2_TO_3");
                //view.setPanel("START");
                //view.setIsOspreyRound2Over(false);
            }
        }
    	if(model.getPlayer().getHealth()<=0 && !nextRound) {
    		//System.out.println(model.getPlayer().getHealth());
    		//comment these lines out for Game Over Screen after bird dies
    		if(birdsPlayed==2) { // <-comment this out
    			view.setPanel("GAME_OVER");
    		}//<-comment this out
    		//comment this out below
    		else {
    			view.setPanel("START");
    		}
        }
    	resetAfterRound();
    	nextRound = false;
    	if (birdsPlayed == 1) {
    		start();
    	}
    }

    
    void resetAfterRound(){
        //view = new View(this);
        //model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getBirdWidth(), view.getBirdHeight());
        model.getPlayer().setHealth(250);
        model.getPlayer().setX(30);
        model.getPlayer().setY(view.getFrameHeight()/2);
        //model.clearGP();
    }
    void resetAfterGameOver(){
        
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == OspreyButton){
            
            model.generateOspreyQuestions();
            birdsPlayed++;
            
            view.setBackground(imgOsprey, imgOsprey2);
            view.setPanel("INITIAL_MAP");
            OspreyButton.setVisible(false);
        }
        else if(e.getSource() == HarrierButton){
        	resetAfterRound();
            model.generateHarrierQuestions();
            birdsPlayed++;
            model.spawnHarrierGamePieces();
            
            view.setBackground(imgHarrier, imgHarrier2);
            view.setPanel("HARRIER_ROUND");
            HarrierButton.setVisible(false);
        }
        
        if(e.getSource() == Round1Button){
            view.setPanel("OSPREY_ROUND_ONE");
            view.setBackground(imgOsprey, imgOsprey2);
            model.spawnOspreyGamePieces();
            model.setRound(1);
        }
        else if(e.getSource() == Round2Button){
            view.setIsOspreyRound1Over(false);
            model.clearGP();
            view.setPanel("OSPREY_ROUND_TWO");
            model.generateOspreyQuestions();
            model.spawnOspreyGamePieces();
            view.setBackground(imgOsprey3, imgOsprey4);
            model.setRound(2);
            nextRound = true;
            
            
            //System.out.println("after");
        }
        
        if(e.getSource() == ReturnToStart) {
            view.setIsOspreyRound2Over(false);
            view.setPanel("START");
            model.setRound(0);
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
    public JButton getRound1Button(){
        return Round1Button;
    }
    public static JButton getRound2Button(){
        return Round2Button;
    }
    
    public static JButton getReturnToStartButton(){
        return ReturnToStart;
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
