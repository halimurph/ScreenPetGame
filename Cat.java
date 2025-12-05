import processing.core.*;

public class Cat extends Pet {

    public Cat(boolean petLocked, int x ,int y) {
        super("Cat", petLocked, x, y);
    }

    public void draw(PApplet p){
        super.draw(p);

        p.fill(13,133,4);
        p.textSize(20);
        p.text("Cat", xLocation, yLocation);
    }


/*
    @Override
    public void playItems() {
        super.playItems();
    }
 */
}//end class
