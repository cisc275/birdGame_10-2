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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Controller class handles flow of game and will take user input.
 *
 * @author crnis
 */
public class Controller implements KeyListener, ActionListener, Serializable {

    final static int INITIAL_BIRD_HEALTH = 250;
    final static int INITIAL_BIRD_X_LOCATION = 30;

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
    private static boolean answered = false;
    private boolean nextRound = false;
    private boolean ospreyNested = false;
    private static boolean harrierNested = false;
    private boolean tutorialTried = false;
    private boolean userDone = false;
    private boolean userResponse = false;
    private boolean paused = false;
    private static int upArrowKeyTried = 0;
    private static int downArrowKeyTried = 0;

    private int tutorialHealth = 10000;
    private boolean runGame = true;
    private boolean answeredCorrect = false;
    /**
     * The constructor for Controller initializes many of the values, such as the different quiz options, the buttons, the view, the model, and it sets the frame to tutorial
     */
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

   
        view.setPanel("TUTORIAL");
    }

    /**
     * start contains the logic to run the game
     * it checks if the user has finished or died
     * start moves a finished or dead used onto the next round if there is one
     */
    void start() {
        while (!userDone) {
            if (runGame) {
                runGame();
                runGame = false;
            }
            if (!model.getPlayer().isAlive() && !nextRound) {
                runGame = false;
                if (birdsPlayed == 2) {
                    System.out.println("reached second if");
                    view.setPanel("GAME_OVER");
                    while (!userResponse) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    view.setPanel("START");
                }
            }
            nextRound = false;
        }
    }

    /**
     * runQuiz contains the logic to run and prepare a quiz for the end of a level
     */
    void runQuiz() {
        view.prepareQuiz();
        while (!Model.quizOver()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (answered && answeredCorrect) {
                answered = false;
                answeredCorrect = false;
                if (!Model.quizOver() && !Model.lastQuestion()) {
                    Model.incrQuestionNum();
                    view.prepareQuiz();
                    System.out.println(Model.getNumberOfQuestions());
                    System.out.println(Model.getQuestionNum());
                } 
                else {
                    Model.incrQuestionNum();
                }
                try {
                    Thread.sleep(250);
                    view.setFalse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
        Model.resetQuestionNum();
    }

    /**
     * runGame contains the logic for the individual ticks of a game. It controls when a player is still in the game or when they have died, and sets them to the correct round when need be.
     */
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
                System.out.println("quiz1?");
                view.setIsOspreyRound1Over(false); 
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
            }
            if (view.is2To3Transition()) {
                view.setPanel("MAP_2_TO_3");

            } else if (view.getIsHarrierRoundOver() && !harrierNested && !Model.isQuiz3Done()) {
                view.setPanel("QUIZ");
                runQuiz();
                model.setIsQuiz3Done(true);

            } else if (view.getIsHarrierRoundOver() && !harrierNested) {
                view.setPanel("HARRIER_NEST");
            }
        }

    }

    /**
     * resetAfterRound sets key model values that change to what they need to be in order to move to the next stage.
     */
    void resetAfterRound() {
        model.setFoodHit(false);
        model.setEnemyHit(false);
        model.getPlayer().setHealth(INITIAL_BIRD_HEALTH);
        model.getPlayer().setX(INITIAL_BIRD_X_LOCATION);
        model.getPlayer().setY(view.getFrameHeight() / 2);
    }

    /**
     * handleQuizButtonClick takes one parameter and checks if the answer was correct
     * 
     * @param choice is the string value of the player's answer
     */
    public void handleQuizButtonClick(String choice) {
        answered = true;
        if (Model.getCorrectAnswer().equals(choice)) {
            answeredCorrect = true;
            view.answeredCorrectly(true);
        } else {
            view.answeredCorrectly(false);
        }
    }

    /**
     * resetAfterGameOver resets view and model methods so that the next runthrough has them reset
     */
    public void resetAfterGameOver() {
        view.resetView();
        model.resetModel();
        ospreyNested = false;
        harrierNested = false;
    }

    /**
     * actionPreformed takes one parameter and handles the logic for what happens based on what action is done
     * This handles all button presses
     * 
     * @param ActionEvent e is the actionEvent that the user has done, which needs to be dealt with
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == QuizOptionA) {

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
            runGame = true;
            userResponse = true;

        } else if (e.getSource() == HarrierButton) {

            resetAfterRound();
            model.clearGP();
            model.generateHarrierQuestions();
            birdsPlayed++;
            model.spawnHarrierGamePieces();

            runGame = true;
            userResponse = true;
            view.setBackground(imgHarrier, imgHarrier2);
            view.setPanel("HARRIER_ROUND");
            HarrierButton.setVisible(false);
            model.setRound(3);

        }

        if (e.getSource() == Round1Button) {
            view.setPanel("OSPREY_ROUND_ONE");
            model.clearGP();
            view.setBackground(imgOsprey, imgOsprey2);
            model.spawnOspreyGamePieces();
            model.setRound(1);
            paused = false;
            runGame = true;
            userResponse = true;

        } else if (e.getSource() == Round2Button) {

            runGame = true;

            resetAfterRound();
            view.setIsOspreyRound1Over(false);

            view.set1To2Transition(false);
            model.setTotalLevelTicks(0);
            model.clearGP();
            view.setPanel("OSPREY_ROUND_TWO");
            model.clearQuestionsToAsk();
            model.generateOspreyQuestions2();
            model.spawnOspreyGamePieces();
            view.setBackground(imgOsprey3, imgOsprey4);
            model.setRound(2);

        }

        if (e.getSource() == ReturnToStart) {
            if (model.getRound() == 3) {
                harrierNested = true;
            }
            if ((harrierNested || ospreyNested) && birdsPlayed == 2) {
                view.setPanel("GAME_OVER");
            } else {
                view.setPanel("START");
            }
            model.setRound(0);
            model.getPlayer().setX(INITIAL_BIRD_X_LOCATION);

        }

        if (e.getSource() == ospreyNestButton) {
            ospreyNested = true;
            view.setPanel("OSPREY_NEST");
            view.setIsOspreyRound2Over(false);
            view.set2To3Transition(false);

        }

        if (e.getSource() == osprey1SaveGameButton || e.getSource() == osprey2SaveGameButton || e.getSource() == harrierSaveGameButton) {
            try {
                saveGame();
                view.setPanel("GAME_OVER");
            } catch (Exception d) {
                d.printStackTrace();
            }
        }

        if (e.getSource() == restartGameButton) {
            userDone = false;

            birdsPlayed = 0;
            model.getPlayer().setScore(0);
            resetAfterGameOver();
            view.setPanel("START");
            OspreyButton.setEnabled(true);
            HarrierButton.setEnabled(true);
            OspreyButton.setVisible(true);
            HarrierButton.setVisible(true);
            userResponse = true;
            userDone = false;
            runGame = true; 
            paused = false;

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
        model.setDirection(null);
    }

    /**
     * restartGame resets key variables in Controller, resets model and view, then calls start again to continue gameplay.
     */
    void restartGame() {
        OspreyButton.setVisible(true);
        HarrierButton.setVisible(true);
        birdsPlayed = 0;
        model.resetModel();
        view.resetView();
        start();
    }

    /**
     * getOspreyButton returns the button for the Osprey logic
     * 
     * @return JButton OspreyButton is the button for the Osprey logic
     */
    public JButton getOspreyButton() {
        return OspreyButton;
    }

    /**
     * getHarrierButton returns the button for the Harrier logic
     * 
     * @return JButton HarrierButton is the button for the Harrier logic
     */
    public JButton getHarrierButton() {
        return HarrierButton;
    }

    /**
     * getRound1Button returns the button for the Round1 logic
     * 
     * @return JButton Round1Button is the button for the Round1 logic
     */
    public JButton getRound1Button() {
        return Round1Button;
    }

    /**
     * getRound2Button returns the button for the Round2 logic
     * 
     * @return JButton Round2Button is the button for the Round2 logic
     */
    public static JButton getRound2Button() {
        return Round2Button;
    }

    /**
     * getReturnToStartButton returns the button for the return to start logic
     * 
     * @return JButton ReturnToStart is the button for the return to start logic
     */
    public static JButton getReturnToStartButton() {
        return ReturnToStart;
    }

    /**
     * getOspreyNestButton returns the button for the osprey nesting logic
     * 
     * @return JButton ospreyNestButton is the button for the osprey nesting logic
     */
    public static JButton getOspreyNestButton() {
        return ospreyNestButton;
    }

    /**
     * getRestartGameButton returns the button for restarting the game
     * 
     * @return JButton restartGameButton is the button for the restart game logic
     */
    public static JButton getRestartGameButton() {
        return restartGameButton;
    }

    /**
     * getExitGameButton returns the button for exiting the game
     * 
     * @return JButton exitGameButton is the button for the exit game logic
     */
    public static JButton getExitGameButton() {
        return exitGameButton;
    }

    /**
     * getView returns the current view
     * 
     * @return View view is the current view used by the Controller
     */
    public View getView() {
        return view;
    }

    /**
     * getModel returns the current model
     * 
     * @return Model model is the current view used by the Controller
     */
    public Model getModel() {
        return model;
    }

    /**
     * getOptionAButton() returns the button used for quiz option A.
     * 
     * @return JButton QuizOptionA is the button used for quiz option A logic
     */
    public static JButton getOptionAButton() {
        return QuizOptionA;
    }

    /**
     * getOptionBButton() returns the button used for quiz option B.
     * 
     * @return JButton QuizOptionB is the button used for quiz option B logic
     */
    public static JButton getOptionBButton() {
        return QuizOptionB;
    }

    /**
     * getOptionCButton() returns the button used for quiz option C.
     * 
     * @return JButton QuizOptionC is the button used for quiz option C logic
     */
    public static JButton getOptionCButton() {
        return QuizOptionC;
    }

    /**
     * getOptionDButton() returns the button used for quiz option D.
     * 
     * @return JButton QuizOptionD is the button used for quiz option D logic
     */
    public static JButton getOptionDButton() {
        return QuizOptionD;
    }

    /**
     * setHarrierNested takes one parameter and sets the harrierNested to the true or false value given
     * 
     * @param Boolean b is the true or false boolean for if the Harrier has nested yet or not
     */
    public static void setHarrierNested(Boolean b) {
        harrierNested = b;

    }

    /**
     * setAnswers takes one parameter and sets the possible quiz answers to the different parts of the answer array
     * 
     * @param String[] answers is the string array of possible quiz answers
     */
    public static void setAnswers(String[] answers) {
        QuizOptionA.setText(answers[0]);
        QuizOptionB.setText(answers[1]);
        QuizOptionC.setText(answers[2]);
        QuizOptionD.setText(answers[3]);
    }
    
    /**
     * saveGame writes the model object to file using Serializable
     */
    public void saveGame() throws Exception {
        FileOutputStream in = new FileOutputStream("gameState.txt");
        ObjectOutputStream ois = new ObjectOutputStream(in);

        ois.writeObject(model);
    }

    /**
     * getOsprey1SaveGameButton returns the button used for saving the osprey game 1
     * 
     * @return JButton osprey1SaveGameButton is the button used for save game logic in osprey stage 1
     */
    public JButton getOsprey1SaveGameButton() {
        return osprey1SaveGameButton;
    }

    /**
     * getOsprey2SaveGameButton returns the button used for saving the osprey game 2
     * 
     * @return JButton osprey2SaveGameButton is the button used for save game logic in osprey stage 2
     */
    public JButton getOsprey2SaveGameButton() {
        return osprey2SaveGameButton;
    }

    /**
     * getHarrierSaveGameButton returns the button used for saving the Harrier game
     * 
     * @return JButton harrierSaveGameButton is the button used for save game logic in the Harrier game
     */
    public JButton getHarrierSaveGameButton() {
        return harrierSaveGameButton;
    }

    /**
     * getUpArrowKeyTried returns the int representation of if the up arrow has been tried
     * 
     * @return int upArrowKeyTried is the int for if the up arrow has been tried. 0 represents not tried.
     */
    public static int getUpArrowKeyTried() {
        return upArrowKeyTried;
    }

    /**
     * getDownArrowKeyTried returns the int representation of if the down arrow has been tried
     * 
     * @return int downArrowKeyTried is the int for if the down arrow has been tried. 0 represents not tried.
     */
    public static int getDownArrowKeyTried() {
        return downArrowKeyTried;
    }
    
    /**
     * getTutorialMovingButton returns the button used for starting the tutorial's movement
     * 
     * @return JButton tutorialMovingButton is the button used for starting the movement in the tutorial
     */
    public static JButton getTutorialMovingButton() {
        return TutorialMovingButton;
    }
    
    /**
     * getTutorialButton returns the JButton for the tutorial
     * 
     * @return JButton used in tutorial
     */
    public JButton getTutorialButton() {
        return TutorialButton;
    }

}
