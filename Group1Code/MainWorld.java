import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The main world that the sim takes place in.
 * 
 * World constructor(inital spawns for everything)
 * as well as ui (trackers) on top and bottom of the sim 
 * made by Nathan
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
    private int nightLength = 1000;
    private int nightTransitionTime = 60;
    private double maxDarkness = 100;

    public static boolean night;

    // Foreground object for night effects
    private Foreground fg;

    
    //values used in the initialization for the world
    //num... counts the number of these currently
    private int numCherry;
    private int numPoisonIvy;
    private int numHerbivore;
    private int numCarnivore;
    private int numShelter;
    //initialization values
    private int startNumCherry = 0;
    private int startNumPoisonIvy = 0;
    private int startNumHerb = 0;
    private int startNumCarn = 0;
    private int startNumShelter = 0;

    //string arrays used for the labels on the top and bottom of the sim
    private String[] leftLabels = new String[3];
    private String[] rightLabels = new String[3];
    private String[] herbivoreLabels = new String[3];
    private String[] carnivoreLabels = new String[3];
    
    //fonts used in the labels
    Font boringFont = new Font("Times New Roman", false, false, 11);
    
    //the actual big labels used on the top and bottom of the sim
    SuperTextBox bigLeftLabel;
    SuperTextBox bigRightLabel;
    SuperTextBox bigHerbivoreLabel;
    SuperTextBox bigCarnivoreLabel;

    /**
     * Constructor for objects of class MainWorld. Adds all the ui and also
     * the components into the world
     */
    public MainWorld(int cherry, int poisonIvy, int herb, int carn, int shelter) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        //sets the starting number of each component in accordance with parameters set
        startNumCherry = cherry;
        startNumPoisonIvy = poisonIvy;
        startNumHerb = herb;
        startNumCarn = carn;
        startNumShelter = shelter;
        
        //sets the image of the world
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
        String herbSpeedLabel = new String("Average Speed of Herbivores: " + 0);
        String herbAttackLabel = new String("Average Attack Power of Herbivores: " + 0);
        String herbSizeLabel = new String("Average Size fo Herbivores: " + 0);
        String carnSpeedLabel = new String("Average Speed of Carnivores: " + 0);
        String carnAttackLabel = new String("Average Attack Power of Carnivores: " + 0);
        String carnSizeLabel = new String("Average Size of Carnivores: " + 0);
        
        // sets the indexes of the plant label to each of the cherry and ivy label
        leftLabels[0] = cherryLabel;
        leftLabels[1] = poisonivyLabel;
        leftLabels[2] = shelterLabel;
        
        // sets the indexes of the animal label to each of the herbivore and carnivore label
        rightLabels[0] = herbivoreLabel;
        rightLabels[1] = carnivoreLabel;
        rightLabels[2] = dayLabel;
        
        //sets the indexes of the herbivore string array to each of the labels
        herbivoreLabels[0] = herbSpeedLabel;
        herbivoreLabels[1] = herbAttackLabel;
        herbivoreLabels[2] = herbSizeLabel;
        
        //sets the indexes of the carnivore string array to each of the labels
        carnivoreLabels[0] = carnSpeedLabel;
        carnivoreLabels[1] = carnAttackLabel;
        carnivoreLabels[2] = carnSizeLabel;
        
        // instantiate the textbox at the top
        bigLeftLabel = new SuperTextBox(leftLabels, boringFont, this.getWidth()/2, false);
        bigRightLabel = new SuperTextBox(rightLabels, boringFont, this.getWidth()/2, false);
        bigHerbivoreLabel = new SuperTextBox(herbivoreLabels, boringFont, this.getWidth()/2, false);
        bigCarnivoreLabel = new SuperTextBox(carnivoreLabels, boringFont, this.getWidth()/2, false);
        
        // values for location of the textbox
        int tempY = bigLeftLabel.getImage().getHeight() / 2;
        double tempX = bigRightLabel.getImage().getWidth();
        
        //note tempY and tempX are used for both the left and right labels on top
        int herbTempY = bigHerbivoreLabel.getImage().getHeight() / 2;
        double herbTempX = bigHerbivoreLabel.getImage().getWidth();
        int carnTempY = bigCarnivoreLabel.getImage().getHeight() / 2;
        double carnTempX = bigCarnivoreLabel.getImage().getWidth();
        
        // actually puts the textbox on the world
        addObject(bigLeftLabel, (int)(tempX/2), tempY);
        addObject(bigRightLabel, (int)(tempX*1.5), tempY);
        addObject(bigHerbivoreLabel, (int)(herbTempX/2), this.getHeight()-herbTempY);
        addObject(bigCarnivoreLabel, (int)(carnTempX*1.5), this.getHeight()-carnTempY);
        
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
            int yy = 50 + random.nextInt(350);
            addObject(new Herbivore(), xx, yy);
        }
        
        // makes startNumCarn number of carnivores on the screen in random locations
        for (int i = 0; i < startNumCarn; i++) {
            Random random = new Random();
            int xx = 100 + random.nextInt(600);
            int yy = 50 + random.nextInt(350);
            addObject(new Carnivore(), xx, yy);
        }
        
        // update the label
        updateLeftLabels();
        updateRightLabels();
        updateBigHerbivoreLabels();
        updateBigCarnivoreLabels();
        
        // spawning in shelters based on selected parameters
        if(shelter >= 2)
        {
            addObject(new Shelter(false), 50, 125);
            addObject(new Shelter(true), 725, 125);
        } 
        if(shelter >= 4)
        {
            addObject(new Shelter(true), 50, 200);
            addObject(new Shelter(false), 725, 225);
        } 
        if(shelter >= 6)
        {
            addObject(new Shelter(false), 50, 325);
            addObject(new Shelter(true), 725, 325);
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
     * In the act method, the world keeps track of how many components 
     * there are in the world and also keeps track of time and manages 
     * seed spawnings
     */
    public void act() {
        //manages the time values and also night time
        timeManager();
        // update the plant label
        updateLeftLabels();
        updateRightLabels();
        updateBigHerbivoreLabels();
        updateBigCarnivoreLabels();
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

    /**
     * This code updates the herbivore labels whenever. Uses the same instantiating 
     * code but just gets new values from the herbivore class
     */
    public void updateBigHerbivoreLabels()
    {
        // Get average speed attack, and size
        
        //Following code gets values from all herbivores in the world for the label
        //created by Lu Wai
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

        //back to labels made by Nathan
        String herbSpeedLabel = new String("Average Speed of Herbivores: " + speed);
        String herbAttackLabel = new String("Average Attack Power of Herbivores: " + attack);
        String herbSizeLabel = new String("Average Size of Herbivores: " + size);
        //updates herbivore label string arrays to the updated values
        herbivoreLabels[0] = herbSpeedLabel;
        herbivoreLabels[1] = herbAttackLabel;
        herbivoreLabels[2] = herbSizeLabel;
        //actually updates the big ui counters
        bigHerbivoreLabel.update();
    }
    
    /**
     * This code updates the carnivore labels whenever. Uses the same instantiating 
     * code but just gets new values from the carnivore class
     */
    public void updateBigCarnivoreLabels()
    {
        //Following code gets values from all carnivores in the world for the label
        //created by Lu Wai
        List<Carnivore> carnivores= (List<Carnivore>)getObjects(Carnivore.class);
        double speed = 0;
        double attack = 0;
        double size = 0;
        for (int i = 0; i < carnivores.size(); i++){
            //System.out.println(herbivores.get(i).getSpeed());
            speed += carnivores.get(i).getSpeed();
            attack += carnivores.get(i).getAttack();
            size += carnivores.get(i).getSize();
        }
        speed = speed/carnivores.size();
        attack = attack/carnivores.size();
        size = size/carnivores.size();

        //back to labels made by Nath
        String carnSpeedLabel = new String("Average Speed of Carnivores: " + speed);
        String carnAttackLabel = new String("Average Attack Power of Carnivores: " + attack);
        String carnSizeLabel = new String("Average Size of Carnivores: " + size);
        //updates herbivore label string arrays to the updated values
        carnivoreLabels[0] = carnSpeedLabel;
        carnivoreLabels[1] = carnAttackLabel;
        carnivoreLabels[2] = carnSizeLabel;
        //actually updates the big ui counters
        bigCarnivoreLabel.update();
    }

    private void timeManager() {
        currentTime++;
        if (currentTime > dayLength) {
            night = true;
            double transparency = 0;
            SoundPlayer.instance.playNightCycleSounds();
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
                Herbivore.setNumHerbivores(getObjects(Herbivore.class).size());
                Carnivore.setNumCarnivores(getObjects(Carnivore.class).size());
            }
        } else {
            night = false;
            drawNight(0);
            SoundPlayer.instance.stopNightCycleSounds();
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
     * Method that returns the current time of the world
     */
    public int getCurrentTime()
    {
        return currentTime;
    }
    
    public void stopped () {
        SoundPlayer.instance.stopBackgroundMusic();
        SoundPlayer.instance.stopNightCycleSounds();
    }
}
