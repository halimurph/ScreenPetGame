import jdk.jfr.Frequency;
import processing.core.*;


public class Pig extends Pet {

    private boolean isClick = false;
    int startTime = 4;
    double timeStart = 0;
    boolean timerStarted = false;
    int timer = startTime;

    public Pig(boolean petLocked, int x, int y) {
        super("Pig", petLocked, x, y);
    }

    public void draw(PApplet p) {
        super.draw(p);

        p.fill(255, 0, 0);
        p.textSize(20);
        p.text("Pig", xLocation, yLocation);

        if (isClick == true) {//add a timer for 5secs
            if (timerStarted) {

            int current = (int) (p.millis() - timeStart) / 1000;
            timer = startTime - current;

                p.frameRate(150);

                xTarget = p.mouseX;
                yTarget = p.mouseY;
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

        public void chaseMouse(PApplet p) {
            isClick = true;
        }
/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
    }//end class
