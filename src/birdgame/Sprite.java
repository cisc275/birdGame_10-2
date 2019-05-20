package birdgame;

import java.io.Serializable;

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

    private Sprite(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
