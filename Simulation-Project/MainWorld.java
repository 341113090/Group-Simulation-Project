import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class MainWorld here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainWorld extends World {
    
    // World Positions;
    private static int minX = 50;
    private static int maxX = 750;
    private static int minY = 50;
    private static int maxY = 450;
    
    private int dayNumber;
    private int currentTime = 0;
    private int dayLength = 600;
    private int nightLength = 300;
    private int nightTransitionTime = 60;
    private double maxDarkness = 100;

    public static boolean night;

    // Foreground object for night effects
    private Foreground fg;

    private int numCherry;
    private int numPoisonIvy;
    private int numHerbivore;
    private int numCarnivore;
    private int startNumCherry = 0;
    private int startNumPoisonIvy = 0;
    private int startNumHerb = 0;
    private int startNumCarn = 0;
    private int startNumShelter = 0;

    private String[] plantLabels = new String[2];
    Font funFont = new Font("Comic Sans MS", false, false, 16);
    Font boringFont = new Font("Times New Roman", false, false, 14);
    SuperTextBox bigPlantLabel;

        
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld(int cherry, int poisonIvy, int herb, int carn, int shelter) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        startNumCherry = cherry;
        startNumPoisonIvy = poisonIvy;
        startNumHerb = herb;
        startNumCarn = carn;
        startNumShelter = shelter;
        GreenfootImage image = new GreenfootImage("Background.png");
        this.setBackground(image);

        // Set the cherry and poison ivy count to 0 every reset
        Cherry.setNumCherries(0);
        PoisonIvy.setNumPoisonIvy(0);
        Herbivore.setNumHerbivores(0);
        Carnivore.setNumCarnivores(0);
        // Label that is displayed on top of the screen
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        // sets the indexes of the plant label to each of the cherry and ivy label
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        // instantiate the textbox at the top
        bigPlantLabel = new SuperTextBox(plantLabels, boringFont, this.getWidth(), false);
        // values for location of the textbox
        int tempY = bigPlantLabel.getImage().getHeight() / 2;
        int tempX = bigPlantLabel.getImage().getWidth() / 2;
        // actually puts the textbox on the world
        addObject(bigPlantLabel, tempX, tempY);
        // makes startNumCherry number of cherries on the screen in random locations
        for (int i = 0; i < startNumCherry; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);               
            int yy = bigPlantLabel.getImage().getHeight() / 2 + 30 + random.nextInt(400);
            addObject(new Cherry(), xx, yy);
        }
        // makes startNumCherry number of cherries on the screen in random locations
        for (int i = 0; i < startNumPoisonIvy; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);
            int yy = 50 + random.nextInt(400);
            addObject(new PoisonIvy(), xx, yy);
        }
        // update the label
        updatePlantLabels();
<<<<<<< HEAD
        // spawning in shelters
        addObject(new Shelter(), 50, 125);// add more shelter when theres an image
        addObject(new Shelter(), 50, 200);
        // addObject(new Shelter(), 50, 275);
        addObject(new Shelter(), 50, 350);
        addObject(new Shelter(), 50, 425);
        // shelters on the left
        addObject(new Shelter(), 750, 125);// add more shelter when theres an image
        addObject(new Shelter(), 750, 200);
        // addObject(new Shelter(), 750, 275);
        addObject(new Shelter(), 750, 350);
        addObject(new Shelter(), 750, 425);
        
        Random random = new Random();
        // Spawn animals
        for (int i = 0; i < 8; i++){
            
            addObject(new Herbivore(), random.nextInt(700)+50, random.nextInt(500)+50);
        }
        //addObject(new Herbivore(), getWidth() / 2, getHeight() / 2);
=======
        // spawning in shelters based on selected parameters
        if(shelter == 2)
        {
            addObject(new Shelter(), 50, 125);
            addObject(new Shelter(), 725, 125);
        } else if(shelter == 4)
        {
            addObject(new Shelter(), 50, 125);
            addObject(new Shelter(), 725, 125);
            addObject(new Shelter(), 50, 200);
            addObject(new Shelter(), 725, 325);
        } else if(shelter == 6)
        {
            addObject(new Shelter(), 50, 125);
            addObject(new Shelter(), 725, 125);
            addObject(new Shelter(), 50, 200);
            addObject(new Shelter(), 725, 200);
            addObject(new Shelter(), 50, 325);
            addObject(new Shelter(), 725, 325);
        } else if(shelter == 8)
        {
            addObject(new Shelter(), 50, 125);
            addObject(new Shelter(), 725, 125);
            addObject(new Shelter(), 50, 200);
            addObject(new Shelter(), 725, 200);
            addObject(new Shelter(), 50, 325);
            addObject(new Shelter(), 725, 325);
            addObject(new Shelter(), 50, 425);
            addObject(new Shelter(), 725, 425);
        }
        addObject(new Herbivore(), getWidth() / 2, getHeight() / 2);
>>>>>>> bd7e7db804a3e7735629275b2bfe50ceffc7e5b2
        fg = new Foreground();
        addObject(fg, getWidth() / 2, getHeight() / 2);

    }

    /**
     * In the act method, the world keeps track of how many cherries and ivies
     * there are.
     */
    public void act() {
        // update the plant label
        updatePlantLabels();

        timeManager();
    }

    /**
     * This code updates the plant labels whenever. Uses the same instantiating
     * code but just gets the new values from the cherry and poison ivy class
     * instead of just being 0.
     */
    public void updatePlantLabels() {
        numPoisonIvy = PoisonIvy.getNumPoisonIvy();
        numCherry = Cherry.getNumCherries();
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        bigPlantLabel.update(plantLabels);
    }

    private void timeManager() {
        currentTime++;

        if (currentTime > dayLength) {
            night = true;
            double transparency = 0;
            if (currentTime - dayLength < nightTransitionTime) {
                transparency = (double) (currentTime - dayLength) / (double) nightTransitionTime;
            } else if (currentTime-dayLength > nightLength-nightTransitionTime) {
                transparency = 1 - (double) (currentTime - dayLength - (nightLength-nightTransitionTime)) / (double) nightTransitionTime;
            }   else {
                transparency = 1;
            }
            drawNight(transparency * maxDarkness);

            if (currentTime > dayLength + nightLength) {
                currentTime = 0;
            }
        } else {
            night = false;
            drawNight(0);
        }
    }

    private GreenfootImage drawNight(double transparency) {
        if (transparency <=0) transparency = 0;
        GreenfootImage dark = new GreenfootImage(getWidth(), getHeight());
        dark.setColor(Color.BLACK);
        dark.fillRect(0, 0, dark.getWidth(), dark.getHeight());
        dark.setTransparency((int) transparency);

        fg.setImage(dark);
        return dark;
    }

    public static float getDistance(Actor a, Actor b) {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float) distance;
    }
    
    public static boolean onEdge(int x, int y){
        if (x >= maxX || x <= minX){
            return true;    
        }   else if (y >= maxY || y <= minY){
            return true;
        }
        return false;
    }
    public static void PlaceOnEdge (Actor obj){
        int x = obj.getX();
        int y = obj.getY();
        if (x >= maxX || x <= minX){
            x = (x>=maxX)?maxX:minX;    
        }   else if (y >= maxY || y <= minY){
            y = (y>=maxY)?maxY:minY;   
        }
        
        obj.setLocation(x,y);
    }
}
