import processing.core.*;

public class Item {

    private String itemName;
    private int price;
    private boolean itemLocked = true;
    private int xLocation;
    private int yLocation;
    private int color;

    public Item(String itemName, int price, boolean itemLocked, int x, int y) {
        this.itemName = itemName;
        this.price = price;
        this.itemLocked = itemLocked;
        xLocation = x;
        yLocation = y;
        color = 0;
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

    public void setItemName(String itemName) {}
    public String getItemName() {return itemName;}

    public void setPrice(int i){price = i;}
    public int getPrice(){return price;}

    public void setItemLocked(boolean itemLocked){
        this.itemLocked = itemLocked;
    }
    public boolean getItemLocked(){return itemLocked;}

    public void draw(PApplet p){
        // white background

        if (itemLocked) {
            p.fill(255, 100, 100);
        } else {
            p.fill(100, 255, 100);
        }
        p.text(toString(), xLocation,yLocation);
    }

    public String toString(){
        String s = "Item: " + itemName + ", Price: " + price + (itemLocked ? " (Locked)" : " (Unlocked)");
        return s;
    }

    public void runTests(){

        assert itemLocked == true;
        assert price > 0;
        assert itemName != null;
    }

}//end class
