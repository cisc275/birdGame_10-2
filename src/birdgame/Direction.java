/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Direction is an enum that contains all the directions that our bird might
 * travel
 *
 * @author crnis
 */
public enum Direction {
    UP("up"),
    DOWN("down");
    private String name;

    /**
     *
     * @param s is a String direction that correlates with an existing direction
     * defined in the enum
     */
    private Direction(String s){
        this.name = s;
    }
    
    /**
     *
     * @return name variable corresponding to the direction
     */
    public String getName(){
        return name;
    }
}