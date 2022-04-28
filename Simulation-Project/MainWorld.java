import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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
    
    public static int dayNumber = 1;
    private int currentTime = 0;
    private int dayLength = 600;
    private int nightLength = 800;
    private int nightTransitionTime = 60;
    private double maxDarkness = 100;

    public static boolean night;

    // Foreground object for night effects
    private Foreground fg;

    private int numCherry;
    private int numPoisonIvy;
    private int numHerbivore;
    private int numCarnivore;
    private int numShelter;
    private int startNumCherry = 0;
    private int startNumPoisonIvy = 0;
    private int startNumHerb = 0;
    private int startNumCarn = 0;
    private int startNumShelter = 0;

    private String[] leftLabels = new String[3];
    private String[] rightLabels = new String[3];
    Font funFont = new Font("Comic Sans MS", false, false, 16);
    Font boringFont = new Font("Times New Roman", false, false, 14);
    SuperTextBox bigLeftLabel;
    SuperTextBox bigRightLabel;

        
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
        Shelter.setNumShelters(0);
        // Label that is displayed on top of the screen
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        String shelterLabel = new String("Number of Shelters in the World: " + numShelter);
        String herbivoreLabel = new String("Number of Herbivores in the World: " + numHerbivore);
        String carnivoreLabel = new String("Number of Carnivores in the World: " + numCarnivore);
        String dayLabel = new String("Day number in the World: " + dayNumber);
        // sets the indexes of the plant label to each of the cherry and ivy label
        leftLabels[0] = cherryLabel;
        leftLabels[1] = poisonivyLabel;
        leftLabels[2] = shelterLabel;
        // sets the indexes of the animal label to each of the herbivore and carnivore label
        rightLabels[0] = herbivoreLabel;
        rightLabels[1] = carnivoreLabel;
        rightLabels[2] = dayLabel;
        // instantiate the textbox at the top
        bigLeftLabel = new SuperTextBox(leftLabels, boringFont, this.getWidth()/2, false);
        bigRightLabel = new SuperTextBox(rightLabels, boringFont, this.getWidth()/2, false);
        // values for location of the textbox
        int tempY = bigLeftLabel.getImage().getHeight() / 2;
        double tempX = bigRightLabel.getImage().getWidth();
        // actually puts the textbox on the world
        addObject(bigLeftLabel, (int)(tempX/2), tempY);
        addObject(bigRightLabel, (int)(tempX*1.5), tempY);
        // makes startNumCherry number of cherries on the screen in random locations
        for (int i = 0; i < startNumCherry; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);               
            int yy = bigLeftLabel.getImage().getHeight() / 2 + 30 + random.nextInt(400);
            addObject(new Cherry(), xx, yy);
        }
        // makes startNumCherry number of cherries on the screen in random locations
        for (int i = 0; i < startNumPoisonIvy; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);
            int yy = bigLeftLabel.getImage().getHeight() / 2 + 30 + random.nextInt(400);
            addObject(new PoisonIvy(), xx, yy);
        }
        // makes startNumHerb number of herbivores on the screen in random locations
        for (int i = 0; i < startNumHerb; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);
            int yy = 50 + random.nextInt(400);
            addObject(new Herbivore(), xx, yy);
        }
        // makes startNumCarn number of carnivores on the screen in random locations
        for (int i = 0; i < startNumCarn; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);
            int yy = 50 + random.nextInt(400);
            addObject(new Carnivore(), xx, yy);
        }
        // update the label
        updateLeftLabels();
        updateRightLabels();
        // spawning in shelters based on selected parameters
        if(shelter >= 2)
        {
            addObject(new Shelter(false), 50, 125);
            addObject(new Shelter(true), 725, 125);
        } 
        if(shelter >= 4)
        {
            addObject(new Shelter(false), 50, 200);
            addObject(new Shelter(true), 725, 325);
        } 
        if(shelter >= 6)
        {
            addObject(new Shelter(true), 50, 325);
            addObject(new Shelter(false), 725, 325);
        } 
        if(shelter >= 8)
        {
            addObject(new Shelter(false), 50, 425);
            addObject(new Shelter(true), 725, 425);
        }
        Random random = new Random();
        //addObject(new Herbivore(), getWidth() / 2, getHeight() / 2);
        fg = new Foreground();
        addObject(fg, getWidth() / 2, getHeight() / 2);
        dayNumber = 1;
    }
    
    /**
     * In the act method, the world keeps track of how many cherries and ivies
     * there are.
     */
    public void act() {
        // update the plant label
        updateLeftLabels();
        updateRightLabels();
        //manages the time values and also night time
        timeManager();
        //if the current time is zero, aka new day, the cherry seeds are allowed to sprout
        if(currentTime >= 0 && currentTime <= 200)
        {
            CherrySeed.toggleCanSprout(true);
        } 
        //if the current time is 450, the cherry seeds cannot sprout anymore
        else if(currentTime >= 450)
        {
            CherrySeed.toggleCanSprout(false);
        }
    }

    /**
     * This code updates the plant labels whenever. Uses the same instantiating
     * code but just gets the new values from the cherry and poison ivy class
     * instead of just being 0.
     */
    public void updateLeftLabels() {
        //update the counter for all plants and shelters
        numPoisonIvy = PoisonIvy.getNumPoisonIvy();
        numCherry = Cherry.getNumCherries();
        numShelter = Shelter.getNumShelters();
        //create new updated strings to be used in the display
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        String shelterLabel = new String("Number of Shelters in the World: " + numShelter);
        //update the display array to have the updated counters rather than the old ones
        leftLabels[0] = cherryLabel;
        leftLabels[1] = poisonivyLabel;
        leftLabels[2] = shelterLabel;
        //push the updated array into the big display
        bigLeftLabel.update(leftLabels);
    }
    
    /**
     * This code updates the animals labels whenever. Uses the same instantiating
     * code but just gets the new values from the harbivore and animals class
     * instead of just being 0.
     */
    public void updateRightLabels() {
        //update the counter for all animals
        numHerbivore = Herbivore.getNumHerbivores();
        numCarnivore = Carnivore.getNumCarnivores();
        //create new updated strings to be used in the display
        String carnivoreLabel = new String("Number of Carnivores in the World: " + numCarnivore);
        String herbivoreLabel = new String("Number of Herbivores in the World: " + numHerbivore);
        String dayLabel = new String("Day Number in the World: " + dayNumber);
        //update the display array to have the updated counters rather than the old ones
        rightLabels[0] = herbivoreLabel;
        rightLabels[1] = carnivoreLabel;
        rightLabels[2] = dayLabel;
        //push the updated array into the big display
        bigRightLabel.update(rightLabels);
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
                dayNumber++;
                
                List<Shelter> shelters = (List<Shelter>)getObjects(Shelter.class);
                for (int i = 0; i < shelters.size(); i++){
                    shelters.get(i).spawnNewAnimal();
                }
                
                // Get average speed attack, and size
                List<Herbivore> herbivores= (List<Herbivore>)getObjects(Herbivore.class);
                double speed = 0;
                double attack = 0;
                double size = 0;
                for (int i = 0; i < herbivores.size(); i++){
                    //System.out.println(herbivores.get(i).getSpeed());
                    speed += herbivores.get(i).getSpeed();
                    attack += herbivores.get(i).getAttack();
                    size += herbivores.get(i).getSize();
                }
                speed = speed/herbivores.size();
                attack = attack/herbivores.size();
                size = size/herbivores.size();
                System.out.println("Number of Herbivores: " +herbivores.size());
                System.out.println("Average Speed: "+speed);
                System.out.println("Average Attack: "+attack);
                System.out.println("Average Size: "+size);
                Herbivore.setNumHerbivores(herbivores.size());
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
    
    /**
     * method that returns the current time of the world
     */
    public int getCurrentTime()
    {
        return currentTime;
    }
}
