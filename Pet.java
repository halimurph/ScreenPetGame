import processing.core.PApplet;
import java.util.Random;

public class Pet {

    protected String petName;
    protected boolean petLocked = true;
    protected int xLocation;
    protected int yLocation;
    protected int coordinates;
    protected String message = "";

    protected int xTarget;
    protected int yTarget;

    protected float easing = 0.05F;
    protected boolean isBeingDragged = false;

    protected double timeStart;
    protected boolean moving = true;

    public Pet(String s, boolean petLocked, int x, int y) {
        this.petName = s;
        this.petLocked = petLocked;
        xLocation = x;
        yLocation = y;
        xTarget = x;
        yTarget = y;
        moveSomewhere();
    }

    public void setName(String s) {
        petName = s;
    }

    public String getName() {
        return petName;
    }

    public boolean isPetLocked(boolean b) {
        petLocked = b;
        return b;
    }
    public void poops() {

    }

    public void setLocation(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    //public void collectMoney(int i) {}

    //public void wearItems(){}

    Random random = new Random();


 public void moveSomewhere() {
     xTarget = random.nextInt(800);
      yTarget = random.nextInt(600);
 }

    public void draw(PApplet p){
        p.fill(13,133,4);
        // p.fill(0);
        p.textSize(20);
        p.text(message, 100, 450);

        if(moving) {
            if (xLocation < xTarget) {
                xLocation++;
            } else if (xLocation > xTarget) {
                xLocation--;
            }
            if (yLocation < yTarget) {
                yLocation++;
            } else if (yLocation > yTarget) {
                yLocation--;
            }
        }
            if(xLocation == xTarget && yLocation == yTarget)
               moveSomewhere();
    }


    public void mousePressed(PApplet p, int x, int y) {
        int clickableRadius = 30; // Adjust this for how "big" the pet feels

        float d = PApplet.dist(x, y, xLocation, yLocation);

        if (d < clickableRadius) {

            moving = false;
            isBeingDragged = true;
            timeStart = p.millis();
            message = "Mouse pressed at " + x + "/" + y;
        }


    }


    public void mouseDragged(PApplet p, int x, int y){

        xLocation = x;
        yLocation = y;
    }
    public void mouseReleased(PApplet p, int x, int y){

        int clickableRadius = 30; // same radius as used in mousePressed

        float d = PApplet.dist(x, y, xLocation, yLocation);

        if (d < clickableRadius) {
            moving = true;

            xLocation = x;
            yLocation = y;
            double time = p.millis() - timeStart;

            String s1 = x < 0 ? "Left" : "Right";
            String s2 = y < 0 ? "Up" : "Down";
            String s = "Mouse moved " + s1 + " and " + s2;
            s += " released at " + p.mouseX + "/" + p.mouseY;
            s += " after " + time + " milliseconds";
            //or
            message = "Mouse released at (" + getXLocation() + ", " + getYLocation() + ")";
        }
    }


    public String toString()
    {

        String s = petName;
        return s;
    }

    public void runTests(){
        assert petLocked == true;
        assert petName != null;
    }

}//end class
