package birdgame;

import java.io.Serializable;


/**
 * SpecialFood is food that may appear larger on screen with some glowing and
 * will offer more of a score increment to the player provided they answer the
 * question that is prompted correctly.
 * 
 * Sprite is an enum for the exact animal (or plane) that a gamepiece is
 * This makes it easier to communicate with View to correctly draw a GamePiece
 *
 * @author crnis
 */

public enum Sprite implements Serializable {
    BUNNY("bunny"),
    SNAKE("snake"),
    MOUSE("mouse"),
    REDFOX("redfox"),
    EAGLE("eagle"),
    PLANE("plane"),
    FISH("fish"),
    RACCOON("raccoon"),
    NORTHERN_HARRIER("northern harrier"),
    OSPREY("osprey");
    String name;

    /**
     * Sprite constructor is private, sets the name to the given string
     *
     * @param s is a String Sprite that correlates with an existing Sprite
     * defined in the enum
     */
    private Sprite(String s) {
        this.name = s;
    }

    /**
     * getName returns the name of the Sprite
     * 
     * @return String name is the name of the Sprite
     */
    public String getName() {
        return name;
    }
}
