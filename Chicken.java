import processing.core.*;

public class Chicken extends Pet {

    private boolean isClick = false;
    int startTime = 4;
    double timeStart = 0;
    boolean timerStarted = false;
    int timer = startTime;

    public Chicken(boolean petLocked, int x, int y) {
        super("Chicken", petLocked, x, y);
    }

    public void draw(PApplet p) {
        super.draw(p);

        p.fill(255,0,0);
        p.textSize(20);
        p.text("Chicken", xLocation, yLocation);

        if (isClick == true) {//add a timer for 5secs
            if (timerStarted) {

                int current = (int) (p.millis() - timeStart) / 1000;
                timer = startTime - current;

//            float targetX = -p.mouseX; //repell by the mouse
//            float dx = targetX + xLocation;
//            xLocation += dx * easing;
//            float targetY = -p.mouseY;
//            float dy = targetY + yLocation;
//            yLocation += dy * easing;

                p.frameRate(100);

                if (xLocation < xTarget) { //scurry fast around the screen
                    xLocation++;
                } else if (xLocation > xTarget) {
                    xLocation--;
                }
                if (yLocation < yTarget) {
                    yLocation++;
                } else if (yLocation > yTarget) {
                    yLocation--;
                }
                if(xLocation == xTarget && yLocation == yTarget)
                    moveSomewhere();
            }
        }
        if (timer == 0) {
            timerStarted = false;
        }
    }

    public void mouseClicked(PApplet p) {
        timeStart = p.millis();
        timerStarted = true;
    }

    public void runAway(PApplet p) {
        isClick = true;
    }

/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
}//end class