import processing.core.*;

import java.util.Random;
import java.util.ArrayList;

public class ScreenPetGame {

    private int totalCoins;
    private int collectedCoinCount = 0;
    private ArrayList<Coin> coins = new ArrayList<Coin>();
    private String message = "";

    private float babyDuckX = 250;
    private float babyDuckY = 250;
    private boolean showBabyDuck = false;
    private boolean showGentlemanHat  = false;
    private boolean showSantaHat   = false;
    private boolean showSunglasses = false;
    private boolean showCowboyHat = false;
    private boolean showCollar = false;
    private boolean showWindMillHat = false;

    private long lastCoinGenerationTime = 0;

//    private boolean isGentlemanHatUnlocked = false;
//    private boolean isCowboyHatUnlocked = false;
//    private boolean isWindMillHatUnlocked = false;
//    private boolean isBabyDuckUnlocked = false;
//    private boolean isCollarUnlocked = false;
    //private boolean isSantaHatUnlocked = false;
    //private boolean isSunglassesUnlocked = false;

    Random rand = new Random();

    Store theStore = new Store();

    Pet thePet = new Cat(true, 400, 190);
    Dragon theDragon = new Dragon(true, 400, 230);
    Chicken theChicken = new Chicken(true, 400, 250);
    Horse theHorse = new Horse(true, 400, 270);
    Pig thePig = new Pig(true, 400, 290);
    Cow theCow = new Cow(true, 400, 210);

    Pet currentPet = thePet; //the cat
    private Coin coin;

    public ScreenPetGame() {
        createAvatar();
        createCoins();
        createStore();
    }

    public void createAvatar() {
        thePet.getName();
        thePet.isPetLocked(true);
    }

    public void createCoins() {
        if(coins.size()>20){
            coins.clear();
        }

        totalCoins = rand.nextInt(20) + 1; // Random number between 1 and 20
        for(int i = 1; i <= totalCoins; i++) {
            int randX = rand.nextInt(800);
            int randY = rand.nextInt(600);
            Coin c = new Coin(i,randX,randY);
            coins.add(c);
        }

    }

    public void regenerateCoinsIfNeeded() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCoinGenerationTime >= 30000) { // 30 seconds
            createCoins(); // Regenerate coins
            lastCoinGenerationTime = currentTime; // Update the last coin generation time
        }
    }

    public void moveTowardsCoin() {
        if (!coins.isEmpty()) {
            Coin nearestCoin = null;
            float shortestDistance = Float.MAX_VALUE;
            float radius = 100;

            for (Coin c : coins) {
                float distance = PApplet.dist(currentPet.xLocation, currentPet.yLocation, c.getxLocation(), c.getyLocation());

                if (distance < shortestDistance && distance <= radius) {
                    shortestDistance = distance;
                    nearestCoin = c;
                }
            }

            if (nearestCoin != null) {
                currentPet.xTarget = nearestCoin.getxLocation();
                currentPet.yTarget = nearestCoin.getyLocation();

                if (currentPet.getXLocation() == nearestCoin.getxLocation() &&
                        currentPet.getYLocation() == nearestCoin.getyLocation()) {
                    coins.remove(nearestCoin);
                    collectedCoinCount++;
                }
            }
        }
    }

    public void createStore() {
        theStore.createItems();
    }

    public int getCollectedCoinCount() {return collectedCoinCount;}
    public void setCollectedCoinCount(int x) {collectedCoinCount = x;}

    public void draw(PApplet p) {
        babyDuck(p);
        theStore.draw(p);
        currentPet.draw(p);
        displayTextMenu(p);
        for(Coin aCoin : coins){
           aCoin.draw(p);
        }

        gentlemanHat(p);
        cowboyHat(p);
        collar(p);
        windMillHat(p);
        santaHat(p);

        p.fill(0);
        p.textSize(20);
        p.text("Collected " + collectedCoinCount + " coins", 100,500);

        moveTowardsCoin();

        p.fill(0);
        p.textSize(20);
        p.text(message, 250,250);

        regenerateCoinsIfNeeded();
        //unlockPetsBasedOnItems();
    }

//    public void unlockPetsBasedOnItems() {
//        // Unlock pets when certain items are unlocked
//        if (isCowboyHatUnlocked) {
//            thePig.isPetLocked(false);  // Unlock pig when cowboy hat is unlocked
//        }
//        if (isGentlemanHatUnlocked) {
//            theDragon.isPetLocked(false);  // Unlock dragon when gentleman hat is unlocked
//        }
//        if (isWindMillHatUnlocked) {
//            theChicken.isPetLocked(false);  // Unlock chicken when windmill hat is unlocked
//        }
//        if(isCollarUnlocked){
//            theHorse.isPetLocked(false);
//        }
//        if(isSantaHatUnlocked){
//            theChicken.isPetLocked(false);
//        }
//        if(isSunglassesUnlocked){
//            theCow.isPetLocked(false);
//        }
//    }

    public void displayTextMenu(PApplet p) {
        p.fill(0);
        String s = "Options:\n";
        s += "Key 1: Change animal to Cat\n";
        s += "Key 2: Change animal to Cow\n";
        s += "Key 3: Change animal to Chicken\n";
        s += "Key 4: Change animal to Horse\n";
        s += "Key 5: Change animal to Pig\n";
        s += "Key 6: Change animal to Dragon\n";
        s += "Mouse Click/Drag: Drag unlocked Cat\n";
        s += "Mouse click on Chicken: scare Chicken\n";
        s += "Key A: Cow poops\n";
        s += "Space: Horse turns into unicorn\n";
        s += "Mouse click on Pig: Pig chases mouse\n";
        s += "Key D: Dragon flies\n";
        s += "Pets will Collect coins\n";
        s += "Click to buy store items\n";
        s += "Unlock Pets when unlock items\n";
        s += "Pet can wear unlocked items\n";
        p.textSize(10);
        p.text(s,20,60);
    }

    public void mouseClicked(PApplet p) {
            thePig.mouseClicked(p);
            thePig.chaseMouse(p);
            theChicken.mouseClicked(p);
            theChicken.runAway(p);
    }

    public void mousePressed(PApplet p, int x, int y) {
        thePet.mousePressed(p, x, y);

        Item foundItem = theStore.findItem(x,y);
        if(!foundItem.getItemName().equals("NOTFOUND")) {

            System.out.println("FOUND: " + foundItem.getItemName());
            if (foundItem.getItemLocked() && getCollectedCoinCount() >= foundItem.getPrice()) {
                foundItem.setItemLocked(false);
                collectedCoinCount -= foundItem.getPrice();
                message = foundItem.getItemName() + " unlocked!";
            } else if (foundItem.getItemLocked()) {
                message = "Not enough coins to unlock " + foundItem.getItemName();
            }
            if (!foundItem.getItemLocked()) {
                boolean isAlreadyOn = false;

                switch (foundItem.getItemName()) {
                    case "Baby Duck": isAlreadyOn = showBabyDuck; break;
                    case "Gentleman Hat": isAlreadyOn = showGentlemanHat; break;
                    case "Santa Hat": isAlreadyOn = showSantaHat; break;
                    case "Cowboy Hat": isAlreadyOn = showCowboyHat; break;
                    case "Collar": isAlreadyOn = showCollar; break;
                    case "Windmill Hat": isAlreadyOn = showWindMillHat; break;
                }

                // Turn all off first
                showBabyDuck = false;
                showGentlemanHat = false;
                showSantaHat = false;
                showCowboyHat = false;
                showCollar = false;
                showWindMillHat = false;

                // If it wasn't already on, turn it on
                if (!isAlreadyOn) {
                    switch (foundItem.getItemName()) {
                        case "Baby Duck": showBabyDuck = true; break;
                        case "Gentleman Hat": showGentlemanHat = true; break;
                        case "Santa Hat": showSantaHat = true; break;
                        case "Cowboy Hat": showCowboyHat = true; break;
                        case "Collar": showCollar = true; break;
                        case "Windmill Hat": showWindMillHat = true; break;
                    }
                }
            }

        }
    }

    public void windMillHat(PApplet p) {
        if(showWindMillHat) {
            p.fill(0);
            p.textSize(10);
            p.text("Windmill Hat", currentPet.xLocation, currentPet.yLocation);
        }
    }

    public void collar(PApplet p) {
        if(showCollar) {
            p.fill(0);
            p.textSize(10);
            p.text("Collar", currentPet.xLocation, currentPet.yLocation);
        }
    }

    public void cowboyHat(PApplet p) {
        if(showCowboyHat) {
            p.fill(0);
            p.textSize(10);
            p.text("Cowboy Hat", currentPet.xLocation, currentPet.yLocation);
        }
    }

    public void santaHat(PApplet p) {
        if(showGentlemanHat) {
            p.fill(0);
            p.textSize(10);
            p.text("Santa Hat", currentPet.xLocation, currentPet.yLocation);
        }
    }

    public void gentlemanHat(PApplet p) {
        if(showGentlemanHat) {
            p.fill(0);
            p.textSize(10);
            p.text("Gentleman Hat", currentPet.xLocation, currentPet.yLocation);
        }
    }

    public void babyDuck(PApplet p) {
        if (showBabyDuck) {
            float easing = 0.05f;

            float dx = currentPet.getXLocation() - babyDuckX;
            babyDuckX += dx * easing;

            float dy = currentPet.getYLocation() - babyDuckY;
            babyDuckY += dy * easing;

            p.fill(255, 255, 0); // yellow
            p.ellipse(babyDuckX, babyDuckY, 10, 10);
        }
    }


   public void mouseDragged(PApplet p, int x, int y){
        thePet.mouseDragged(p, x, y);
    }
    public void mouseReleased(PApplet p, int x, int y){
        thePet.mouseReleased(p, x, y);
    }

    public void keyPressed(PApplet p) {

        if(p.key == 'A' || p.key == 'a')
            theCow.poops();

        if(p.key == '1')
            currentPet = thePet;

        if(p.key == '2')
            currentPet = theCow;

        if(p.key == '3')
            currentPet = theChicken;

        if(p.key == '4')
            currentPet = theHorse;

        if(p.key == '5')
            currentPet = thePig;

        if(p.key == '6')
            currentPet = theDragon;

        else if(p.key == ' ') {
            theHorse.unicorn(p);
        }else if(p.key == 'D' || p.key == 'd')
            theDragon.flies(p);

    }


    public String toString(){
        String s = "Game:\n";
        s += "Total Coins Generated: " + coins.size() + "\n";
        s += "Collected Coins Generated: " + collectedCoinCount + "\n";

        s += theStore;

        s += "Pet: " + thePet + "\n";
        if(thePet.isPetLocked(false))
             s += "Pet is unlocked\n";
        else
            s += "Pet is locked\n";

        return s;

        }

    public void runTests(){

        assert collectedCoinCount == 0;
        assert coins.size() > 0 && coins.size() <= 20;
        assert coins.get(0) != null;
        assert coins.get(coins.size()-1) != null;
        assert totalCoins == coins.size();

        theStore.runTests();
        thePet.runTests();

        }

}