import processing.core.*;

public class ScreenPetUI extends PApplet {

    private ScreenPetGame screenPet = new ScreenPetGame();

    public void settings(){
        size(800, 600);
    }

    public void setup() {

    }

    public void draw(){
       background(255);

       screenPet.draw(this);
    }
    /*

    public void mouseClicked(){
        screenPet.mouseClicked(this, mouseX, mouseY);
    }

     */

    public void mouseClicked(){
        screenPet.mouseClicked(this);
    }

    public void keyPressed() {
        screenPet.keyPressed(this);
    }

    public void mousePressed() {
        screenPet.mousePressed(this, mouseX, mouseY);
    }

    public void mouseDragged(){
        screenPet.mouseDragged(this, mouseX, mouseY);
    }

    public void mouseReleased(){
        screenPet.mouseReleased(this, mouseX, mouseY);
    }

    public static void main(String[] args) {
        PApplet.main("ScreenPetUI");
    }
}//end class
