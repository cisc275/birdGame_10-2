/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 * */
package lab6;

//collision logic
//decide direction
//updateLocationandDirection (if statement to detect collision
class Model {

    int fWidth;
    int fHeight;
    int imgWidth;
    int imgHeight;
    final int xIncr = 8;
    final int yIncr = 2;
    int xloc = 0;
    int yloc = 0;
    Direction direction = Direction.NORTHEAST;

    public Model(int frameWidth, int frameHeight, int imageWidth, int imageHeight) {
        fWidth = frameWidth;
        fHeight = frameHeight;
        imgWidth = imageWidth;
        imgHeight = imageHeight;
    }

    public void updateLocationAndDirection() {
        if (View.paused) {
            xloc += 0;
            yloc += 0;
        } else {
            if (xloc > (fWidth - imgWidth)) {
                if (direction == Direction.SOUTHEAST) { // southeast
                    direction = Direction.SOUTHWEST; //southwest
                    xloc -= xIncr;
                    yloc += yIncr;
                } else if (direction == Direction.NORTHEAST) { // northeast
                    direction = Direction.NORTHWEST; //northwest
                    xloc -= xIncr;
                    yloc -= yIncr;
                } else { // east
                    direction = Direction.WEST; // west
                    xloc -= xIncr;
                    yloc += 0;
                }

            } else if (xloc < 0) {
                if (direction == Direction.SOUTHWEST) { //southwest
                    direction = Direction.SOUTHEAST; // southeast
                    xloc += xIncr;
                    yloc += yIncr;
                } else if (direction == Direction.NORTHWEST) { //northwest
                    direction = Direction.NORTHEAST; //northeast
                    xloc += xIncr;
                    yloc -= yIncr;
                } else { //west
                    direction = Direction.EAST; //east
                    xloc += xIncr;
                    yloc += 0;
                }
            } else if (yloc > (fHeight - imgHeight)) {
                if (direction == Direction.SOUTHEAST) { //southeast
                    direction = Direction.NORTHEAST; //northeast
                    xloc += xIncr;
                    yloc -= yIncr;
                } else if (direction == Direction.SOUTHWEST) { //southwest
                    direction = Direction.NORTHWEST; //northwest
                    xloc -= xIncr;
                    yloc -= yIncr;
                } else { // south
                    direction = Direction.NORTH; //north
                    xloc += 0;
                    yloc -= yIncr;
                }
            } else if (yloc < 0) {
                if (direction == Direction.NORTHEAST) { //northeast
                    direction = Direction.SOUTHEAST;//southeast
                    xloc += xIncr;
                    yloc += yIncr;
                } else if (direction == Direction.NORTHWEST) { //northwest
                    direction = Direction.SOUTHWEST; // southwest
                    xloc -= xIncr;
                    yloc += yIncr;
                } else { //north
                    direction = Direction.SOUTH; //south
                    xloc += 0;
                    yloc += yIncr;
                }
            } else {
                if (direction == Direction.EAST) { // east
                    xloc += xIncr;
                    yloc += 0;
                } else if (direction == Direction.NORTH) { //north
                    xloc += 0;
                    yloc -= yIncr;
                } else if (direction == Direction.SOUTH) { // south
                    xloc += 0;
                    yloc += yIncr;
                } else if (direction == Direction.WEST) { //west
                    xloc -= xIncr;
                    yloc += 0;
                } else if (direction == Direction.NORTHEAST) { //northeast
                    xloc += xIncr;
                    yloc -= yIncr;
                } else if (direction == Direction.NORTHWEST) { //northwest
                    xloc -= xIncr;
                    yloc -= yIncr;
                } else if (direction == Direction.SOUTHEAST) { //southeast
                    xloc += xIncr;
                    yloc += yIncr;
                } else { //southwest
                    xloc -= xIncr;
                    yloc += yIncr;
                }
            }
        }

    }

    public int getX() {
        return xloc;
    }

    public int getY() {
        return yloc;
    }

    public Direction getDirect() {
        return direction;
    }
}
