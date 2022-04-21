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

    ///// DAY NIGHT CYCLE /////
    private int dayNumber;
    private int currentTime = 0;
    private int dayLength = 600;
    private int nightLength = 300;  
    private double maxDarkness = 100;

    public static boolean night;

    // Foreground object for night effects
    private Foreground fg;

    private int numCherry;
    private int numPoisonIvy;
    private int startNumCherry = 4;
    private int startNumPoisonIvy = 9;
    private String[] plantLabels = new String[2];
    Font funFont = new Font("Comic Sans MS", false, false, 16);
    Font boringFont = new Font("Times New Roman", false, false, 14);
    SuperTextBox bigPlantLabel;

    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        bigPlantLabel = new SuperTextBox(plantLabels, boringFont, this.getWidth(), false);
        int tempY = bigPlantLabel.getImage().getHeight() / 2;
        int tempX = bigPlantLabel.getImage().getWidth() / 2;
        addObject(bigPlantLabel, tempX, tempY);
        for (int i = 0; i < startNumCherry; i++) {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = bigPlantLabel.getImage().getHeight() / 2 + 30 + random.nextInt(300);
            addObject(new Cherry(), xx, yy);
        }
        for (int i = 0; i < startNumPoisonIvy; i++) {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = 50 + random.nextInt(300);
            addObject(new PoisonIvy(), xx, yy);
        }

        fg = new Foreground();
        addObject(fg, getWidth() / 2, getHeight() / 2);
        
        addObject(new Herbivore(), getWidth()/2, getHeight()/2);
    }

    /**
     * In the act method, the world keeps track of how many cherries and ivies
     * there are.
     */
    public void act() {
        timeManager();
        // Make an arraylist filled with cherries
        ArrayList<Cherry> cherries = (ArrayList<Cherry>) this.getObjects(Cherry.class);
        int numIterationsCherry = 0;
        for (Cherry cherry : cherries) {
            // iterate through every cherry. For every cherry keep track of how many
            numIterationsCherry++;
        }
        // current number of cherries is the number of iterations
        numCherry = numIterationsCherry;
        // Make an arraylist filled with ivy
        ArrayList<PoisonIvy> ivies = (ArrayList<PoisonIvy>) this.getObjects(PoisonIvy.class);
        int numIterationsIvy = 0;
        for (PoisonIvy ivy : ivies) {
            // iterate through every ivy. For every ivy keep track of how many
            numIterationsIvy++;
        }
        numPoisonIvy = numIterationsIvy;
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
            if (currentTime - dayLength < nightLength / 2) {
    
                transparency = (double) (currentTime - dayLength) / (double) nightLength*2;
                //System.out.println("Before halfway");
            } else {
                //System.out.println("After halfway");
                transparency = 1 - (double) (currentTime - dayLength - nightLength / 2.0) / (double) nightLength*2 ;
            }
            System.out.println(transparency);
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
}
