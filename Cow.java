import processing.core.*;
import java.util.ArrayList;

public class Cow extends Pet {

    private boolean isPoop = false;
    private ArrayList<PVector> poops = new ArrayList<PVector>(); // List to store the positions of poop

    public Cow(boolean petLocked, int x, int y) {
        super("Cow", petLocked, x, y);
    }

    public void draw(PApplet p) {
        super.draw(p);

        for (PVector poop : poops) {
            p.fill(139, 69, 19);
            p.noStroke();
            p.circle(poop.x + 20, poop.y, 5); // Use the stored position for drawing
        }

        p.fill(255,0,0);
        p.textSize(20);
        p.text("Cow", xLocation, yLocation);
    }

    public void poops() {
        poops.add(new PVector(xLocation, yLocation));
    }
/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
}//end class
