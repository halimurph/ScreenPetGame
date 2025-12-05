import processing.core.*;
import java.lang.Math.*;

public class Coin {

    private int coinNumber;
    private int xLocation;
    private int yLocation;


    public Coin(int a, int xLocation, int yLocation) {
        coinNumber = a;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }
    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }
    public int getCoinNumber() {
        return coinNumber;
    }

    public void draw(PApplet p) {
        p.textSize(20);
        p.fill(0);
        p.text("C"+coinNumber, xLocation, yLocation);
    }

    public String toString() {
        return coinNumber + " " + xLocation + " " + yLocation;
    }


}
