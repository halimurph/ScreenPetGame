import processing.core.*;

public class Dragon extends Pet {

    private boolean isClick = false;

    public Dragon(boolean petLocked, int x, int y) {
        super("Dragon", petLocked, x, y);
    }

    public void draw(PApplet p) {
        super.draw(p);

        p.fill(255,0,0);
        p.textSize(20);
        if (isClick) {
            p.text("Dragon flying", xLocation, yLocation);
        }else
            p.text("Dragon", xLocation, yLocation);
    }

    public void flies(PApplet p) {
        isClick = true;
    }
/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
}//end class