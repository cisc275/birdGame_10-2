/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Controller class will handle flow of game and will take user input.
 *
 * @author crnis
 */
public class Controller implements KeyListener, ActionListener, Serializable {

    private initialNumbers initNums = new initialNumbers();
    private View view;
    private Model model;
    private JButton OspreyButton;
    private JButton HarrierButton;
    private JButton Round1Button;
    private JButton TutorialButton;
    private static JButton TutorialMovingButton;
    private static JButton QuizOptionA;
    private static JButton QuizOptionB;
    private static JButton QuizOptionC;
    private static JButton QuizOptionD;
    private static JButton Round2Button;
    private static JButton ReturnToStart;
    private static JButton ospreyNestButton;
    private static JButton osprey1SaveGameButton;
    private static JButton osprey2SaveGameButton;
    private static JButton harrierSaveGameButton;

    private static JButton restartGameButton;
    private static JButton exitGameButton;
    private AbstractAction arrowKeyAction;
    private ImageIcon imgOsprey = new ImageIcon("images/BirdImages/OspreyBackground2.jpg");
    private ImageIcon imgOsprey2 = new ImageIcon("images/BirdImages/OspreyBackground2Mirror.jpg");
    private ImageIcon imgOsprey3 = new ImageIcon("images/BirdImages/OspreyBackground.png");
    private ImageIcon imgOsprey4 = new ImageIcon("images/BirdImages/OspreyBackgroundMirror.png");
    private ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
    private ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");
    private int birdsPlayed = 0;
    private boolean answered = false;
    private boolean nextRound = false;
    private boolean ospreyNested = false;
    private static boolean harrierNested = false;
    private boolean tutorialTried = false;
    private boolean userDone = false;
    private boolean userResponse = false;
    private boolean reachedHarrierEnd = false;
    private boolean reachedOspreyEnd = false;
    private boolean paused = false;
    private static int upArrowKeyTried = 0;
    private static int downArrowKeyTried = 0;
    private int tutorialHealth = 10000;
    private boolean startButton = true;

    public Controller() {
        QuizOptionA = new JButton("A");
        QuizOptionB = new JButton("B");
        QuizOptionC = new JButton("C");
        QuizOptionD = new JButton("D");
        QuizOptionA.setBackground(Color.BLUE);
        QuizOptionB.setBackground(Color.RED);
        QuizOptionC.setBackground(Color.YELLOW);
        QuizOptionD.setBackground(Color.GREEN);
        QuizOptionA.setFont(new Font("Agency FB", Font.BOLD, view.FRAME_WIDTH / 33));
        QuizOptionB.setFont(new Font("Agency FB", Font.BOLD, view.FRAME_WIDTH / 33));
        QuizOptionC.setFont(new Font("Agency FB", Font.BOLD, view.FRAME_WIDTH / 33));
        QuizOptionD.setFont(new Font("Agency FB", Font.BOLD, view.FRAME_WIDTH / 33));
        QuizOptionA.addActionListener(this);
        QuizOptionB.addActionListener(this);
        QuizOptionC.addActionListener(this);
        QuizOptionD.addActionListener(this);

        OspreyButton = new JButton("Play as Osprey");
        HarrierButton = new JButton("Play as Harrier");
        Round1Button = new JButton("Ready to Play Level 1");
        Round2Button = new JButton("Ready to Play Level 2");
        ReturnToStart = new JButton("Return to Start Screen");
        ospreyNestButton = new JButton("Continue");
        osprey1SaveGameButton = new JButton("Save Game");
        osprey2SaveGameButton = new JButton("Save Game");
        harrierSaveGameButton = new JButton("Save Game");
        restartGameButton = new JButton("Restart");
        exitGameButton = new JButton("Exit");
        TutorialButton = new JButton("Click Here for the Tutorial");
        TutorialMovingButton = new JButton("Ready to Play?");
        OspreyButton.addActionListener(this);
        HarrierButton.addActionListener(this);
        Round1Button.addActionListener(this);
        Round2Button.addActionListener(this);
        ReturnToStart.addActionListener(this);
        ospreyNestButton.addActionListener(this);
        osprey1SaveGameButton.addActionListener(this);
        osprey2SaveGameButton.addActionListener(this);
        harrierSaveGameButton.addActionListener(this);

        restartGameButton.addActionListener(this);
        exitGameButton.addActionListener(this);
        TutorialButton.addActionListener(this);
        TutorialMovingButton.addActionListener(this);

        view = new View(this);
        model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getBirdWidth(), view.getBirdHeight());
        //view.setPanel("TUTORIAL");
        view.setPanel("START");
        
    }

    public void resetForGameOver() {

    }

    public void resetForNewLevel() {

    }

    void start() {

        while (!userDone) {
            //System.out.println("start reached");
//        if(!tutorialTried){
//            view.setPanel("TUTORIAL");
//        }
            //if () {
            if (startButton) {
                System.out.println("Running game");
                runGame();
                startButton = false;
            }

            //}
            if (!model.getPlayer().isAlive() && !nextRound) {

                startButton = false;
                System.out.println("reached first if");

                if (birdsPlayed == 2){ //&& !model.getPlayer().isAlive()) { // <-comment this out
                    System.out.println("reached second if");
                    view.setPanel("GAME_OVER");
                    // paused = true;
                    while (!userResponse) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }//<-comment this out
                //comment this out below
                //Player loses first round
                else {

                    view.setPanel("START");
                }
//               paused = true;
//               while (!userResponse) {
//               	try {
//                       Thread.sleep(50);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                 }

            }
            // resetAfterRound();
            nextRound = false;
        }

    }

    void runQuiz() {
        view.prepareQuiz();
        while (!Model.quizOver()) {
            //	System.out.println(Model.getQuestionNum());
            //	System.out.println(Model.getNumberOfQuestions());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (answered) {
                answered = false;
                if (!Model.quizOver() && !Model.lastQuestion()) {
                    Model.incrQuestionNum();
                    view.prepareQuiz();
                } else {
                    Model.incrQuestionNum();
                }
            }
        }
        Model.resetQuestionNum();

    }

    void runGame() {
        while (model.getPlayer().isAlive() && !paused) {
            model.handleTicks();

            model.updateNumberOfQuestions();
            view.update(model.getPlayer().getX(), model.getPlayer().getY(),
                    model.getCurrentGPs(), model.getDirection(),
                    model.getPlayer().getHealth(), model.getPlayer().getScore());
            if (model.getPlayer().isAlive() == false) {
                view.setIsOspreyRound1Over(false);
                view.setIsOspreyRound2Over(false);
                view.setIsHarrierRoundOver(false);
            }
            if (view.getIsOspreyRound1Over() && !Model.isQuiz1Done()) {
                view.setIsOspreyRound1Over(false); //Might not be neeeded
                view.set1To2Transition(true);
                view.setPanel("QUIZ");
                runQuiz();
                model.setIsQuiz1Done(true);
            }

            if (view.is1To2Transition()) {
                view.setPanel("MAP_1_TO_2");
            } else if (view.getIsOspreyRound2Over() && !ospreyNested && !Model.isQuiz2Done()) {
                view.setPanel("QUIZ");
                runQuiz();
                view.set2To3Transition(true);
                model.setIsQuiz2Done(true);

                //view.setPanel("OSPREY_NEST");
            }
            if (view.is2To3Transition()) {
                view.setPanel("MAP_2_TO_3");
            } else if (view.getIsHarrierRoundOver() && !harrierNested && !Model.isQuiz3Done()) {
                //might need some code in here later to stop this else from triggering
                //while playing osprey if you play harrier first
                view.setPanel("QUIZ");
                runQuiz();
                model.setIsQuiz3Done(true);
                System.out.println("harrier round over");
                //might need some code in here later 

            } else if (view.getIsHarrierRoundOver() && !harrierNested) {
                view.setPanel("HARRIER_NEST");
            }
        }

    }

    void resetAfterRound() {
        model.getPlayer().setHealth(initNums.birdHealth());
        model.getPlayer().setX(initNums.birdXLocation());
        model.getPlayer().setY(view.getFrameHeight() / 2);
    }

    public void handleQuizButtonClick(String choice) {
        answered = true;
        if (Model.getCorrectAnswer().equals(choice)) {
            view.answeredCorrectly(true);
        } else {
            view.answeredCorrectly(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == QuizOptionA) {
//    		answered = true;
//    		 if (Model.getCorrectAnswer().equals("A")){
//    			 view.answeredCorrectly(true);
//    		 }
//    		 
//    		 else {
//    			 view.answeredCorrectly(false);
//    		 }
            handleQuizButtonClick("A");

        }

        if (e.getSource() == QuizOptionB) {
            handleQuizButtonClick("B");

        }
        if (e.getSource() == QuizOptionC) {
            handleQuizButtonClick("C");
        }
        if (e.getSource() == QuizOptionD) {
            handleQuizButtonClick("D");
        }
        if (e.getSource() == TutorialButton) {
            view.setBackground(imgOsprey, imgOsprey2);
            model.getPlayer().setHealth(tutorialHealth);
            view.setPanel("MOVING_SCREEN");

        }
        if (e.getSource() == TutorialMovingButton) {
            view.setPanel("START");

        }

        if (e.getSource() == OspreyButton) {
            resetAfterRound();
            paused = false;
            birdsPlayed++;
            model.generateOspreyQuestions();

            view.setBackground(imgOsprey, imgOsprey2);
            view.setPanel("INITIAL_MAP");
            OspreyButton.setVisible(false);
            startButton = true;
            userResponse = true;
        } else if (e.getSource() == HarrierButton) {

            resetAfterRound();
            model.clearGP();
            model.generateHarrierQuestions();
            birdsPlayed++;
            model.spawnHarrierGamePieces();

            startButton = true;
            //paused = false;
            userResponse = true;

            view.setBackground(imgHarrier, imgHarrier2);
            view.setPanel("HARRIER_ROUND");
            HarrierButton.setVisible(false);
            model.setRound(3);

        }

        if (e.getSource() == Round1Button) {
            startButton = true;
            view.setPanel("OSPREY_ROUND_ONE");
            model.clearGP();
            view.setBackground(imgOsprey, imgOsprey2);
            model.spawnOspreyGamePieces();
            model.setRound(1);
            paused = false;
            userResponse = true;
        } else if (e.getSource() == Round2Button) {
            startButton = true;
            resetAfterRound();
            view.setIsOspreyRound1Over(false);

            view.set1To2Transition(false);
            model.setTotalLevelTicks(0);
            model.clearGP();
            view.setPanel("OSPREY_ROUND_TWO");
            // model.generateOspreyQuestions();
            model.generateOspreyQuestions2();
            model.spawnOspreyGamePieces();
            view.setBackground(imgOsprey3, imgOsprey4);
            model.setRound(2);
            //nextRound = true;
        }

        if (e.getSource() == ReturnToStart) {
            reachedHarrierEnd = true;
            //view.setIsOspreyRound2Over(false);
            if(harrierNested && ospreyNested){
                view.setPanel("GAME_OVER");
            }
            else{
                view.setPanel("START");
            }
            model.setRound(0);
            model.getPlayer().setX(30);

        }

        if (e.getSource() == ospreyNestButton) {
            reachedOspreyEnd = true;
            view.setPanel("OSPREY_NEST");
            view.setIsOspreyRound2Over(false);
            view.set2To3Transition(false);
            ospreyNested = true;
        }

        if (e.getSource() == osprey1SaveGameButton || e.getSource() == osprey2SaveGameButton || e.getSource() == harrierSaveGameButton) {
            try {
                saveGame();
            } catch (Exception d) {
                d.printStackTrace();
            }
        }

        if (e.getSource() == restartGameButton) {
            userDone = false;
            startButton = true;
            birdsPlayed = 0;
            model.getPlayer().setScore(0);
            userResponse = true;
            view.setPanel("START");
            OspreyButton.setEnabled(true);
            HarrierButton.setEnabled(true);
            OspreyButton.setVisible(true);
            HarrierButton.setVisible(true);
            paused = false;
            //  restartGame();

        }
        if (e.getSource() == exitGameButton) {
            userDone = true;
            userResponse = true;
            view.getFrame().dispose();
        }

    }

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
            view.getUpLabel().setVisible(false);
            upArrowKeyTried++;

        } else if (key == KeyEvent.VK_DOWN) {
            Model.setDirection(Direction.DOWN);
            view.getDownLabel().setVisible(false);
            downArrowKeyTried++;
        }
        if (upArrowKeyTried >= 1 && downArrowKeyTried >= 1 && view.drawFish) {
            view.getFoodLabel().setVisible(true);
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
        model.setDirection(null);
    }

    void restartGame() {
        //OspreyButton.setEnabled(true);
        //HarrierButton.setEnabled(true);
        OspreyButton.setVisible(true);
        HarrierButton.setVisible(true);
        birdsPlayed = 0;
        model.resetModel();
        view.resetView();
        start();
    }

    public JButton getOspreyButton() {
        return OspreyButton;
    }

    public JButton getHarrierButton() {
        return HarrierButton;
    }

    public JButton getRound1Button() {
        return Round1Button;
    }

    public static JButton getRound2Button() {
        return Round2Button;
    }

    public static JButton getReturnToStartButton() {
        return ReturnToStart;
    }

    public static JButton getOspreyNestButton() {
        return ospreyNestButton;
    }

    public static JButton getRestartGameButton() {
        return restartGameButton;
    }

    public static JButton getExitGameButton() {
        return exitGameButton;
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public static JButton getOptionAButton() {
        return QuizOptionA;
    }

    public static JButton getOptionBButton() {
        return QuizOptionB;
    }

    public static JButton getOptionCButton() {
        return QuizOptionC;
    }

    public static JButton getOptionDButton() {
        return QuizOptionD;
    }

    public static void setHarrierNested(Boolean b) {
        harrierNested = b;

    }

    public static void setAnswers(String[] answers) {
        QuizOptionA.setText(answers[0]);
        QuizOptionB.setText(answers[1]);
        QuizOptionC.setText(answers[2]);
        QuizOptionD.setText(answers[3]);
    }

    public void saveGame() throws Exception {
        FileOutputStream in = new FileOutputStream("gameState.txt");
        ObjectOutputStream ois = new ObjectOutputStream(in);

        ois.writeObject(model);
    }

    public JButton getOsprey1SaveGameButton() {
        return osprey1SaveGameButton;
    }

    public JButton getOsprey2SaveGameButton() {
        return osprey2SaveGameButton;
    }

    public JButton getHarrierSaveGameButton() {
        return harrierSaveGameButton;
    }

    public static int getUpArrowKeyTried() {
        return upArrowKeyTried;
    }

    public static int getDownArrowKeyTried() {
        return downArrowKeyTried;
    }

    public static JButton getTutorialMovingButton() {
        return TutorialMovingButton;
    }

    public JButton getTutorialButton() {
        return TutorialButton;
    }
}
