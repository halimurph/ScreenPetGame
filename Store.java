import processing.core.*;

import java.util.ArrayList;

public class Store {

    private ArrayList<Item> items = new ArrayList<Item>(7);
    private ScreenPetGame game;
    private String itemName = " ";

    public Store() {
        createItems();
    }

    public void createItems() {
        items.add(new Item("Gentleman Hat", 5, true, 400, 50));
        items.add(new Item("Santa Hat", 10, true, 400, 70));
        items.add(new Item("Sunglasses", 15, true, 400, 90));
        items.add(new Item("Cowboy Hat", 7, true, 400, 110));
        items.add(new Item("Baby Duck", 1, true, 400, 130));
        items.add(new Item("Collar", 12, true, 400, 150));
        items.add(new Item("Windmill Hat", 20, true, 400, 170));
    }

    public void draw(PApplet p) {
        for (Item item : items) {
            item.draw(p);
        }
        p.fill(0);
        p.text("Store:", 400, 30);
    }

    public Item findItem(int x, int y)
    {
        Item tempItem = new Item("NOTFOUND", 0, false, 0, 0);

        for (Item item : items) {

            int xLeft = item.getxLocation();
            int xRight = xLeft + 100;
            int yTop = item.getyLocation();
            int yBottom = yTop - 20;
            if (x > xLeft && x < xRight && y < yTop && y > yBottom)
                tempItem = item;
        }
    return tempItem;
}

    public String toString() {
        String s = "Store:\n";

        for (Item item : items) {
            s += item.toString() + "\n";
        }
        return s;
        }

    public void runTests(){
        assert items.size() == 7;
        for(Item b : items)
            b.runTests();
     }
}
//end class
