import processing.core.*;

public class Horse extends Pet {

    private boolean isClick = false;

    public Horse(boolean petLocked, int x, int y) {
        super("Horse", petLocked, x, y);
    }

    public void draw(PApplet p) {
        super.draw(p);

        p.fill(255,0,0);
        p.textSize(20);
        if (isClick) {
            p.text("Unicorn", xLocation, yLocation);
        }else
            p.text("Horse", xLocation, yLocation);
    }

    public void unicorn(PApplet p) {
        isClick = true;
    }
/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
}//end class