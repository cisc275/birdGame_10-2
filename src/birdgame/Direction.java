/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.io.Serializable;

/**
 * Direction is an enum that contains all the directions that our bird might
 * travel
 *
 * @author crnis
 */
public enum Direction implements Serializable {
    UP("up"),
    DOWN("down");
    private String name;

    /**
     * Direction constructor is private, sets the name to the given string
     *
     * @param s is a String direction that correlates with an existing direction
     * defined in the enum
     */
    private Direction(String s) {
        this.name = s;
    }

    /**
     * getName returns the name of the direction
     *
     * @return name is the variable corresponding to the direction
     */
    public String getName() {
        return name;
    }
}
