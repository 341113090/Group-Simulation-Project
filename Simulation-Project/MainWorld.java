import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class MainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends World
{
    private int dayNumber;
    private int numCherry;
    private int numPoisonIvy;
    private int startNumCherry = 4;
    private int startNumPoisonIvy = 9;
    private String[] plantLabels = new String[2];
    Font funFont = new Font ("Comic Sans MS", false, false, 16);
    Font boringFont = new Font ("Times New Roman", false, false, 14);
    SuperTextBox bigPlantLabel;
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        //Set the chrry and poison ivy count to 0 every reset
        Cherry.setNumCherries(0);
        PoisonIvy.setNumPoisonIvy(0);
        //Label that is displayed on top of the screen
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        //sets the indexes of the plant label to each of the cherry and ivy label
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        //instantiate the textbox at the top
        bigPlantLabel = new SuperTextBox(plantLabels,boringFont,this.getWidth()/2,false); 
        //values for location of the textbox
        int tempY = bigPlantLabel.getImage().getHeight()/2;
        int tempX = bigPlantLabel.getImage().getWidth()/2;
        //actually puts the textbox on the world
        addObject(bigPlantLabel, tempX, tempY);
        //makes startNumCherry number of cherries on the screen in random locations
        for(int i = 0; i<startNumCherry; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = bigPlantLabel.getImage().getHeight()/2 + 30 + random.nextInt(300);
            addObject(new Cherry(), xx, yy);
        }
        //makes startNumCherry number of cherries on the screen in random locations
        for(int i = 0; i<startNumPoisonIvy; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = 50+ random.nextInt(300);
            addObject(new PoisonIvy(), xx, yy);
        }
        //update the label
        updatePlantLabels();
    }
    
    /**
     * In the act method, the world keeps track of how many cherries and ivies 
     * there are.
     */
    public void act()
    {
        //update the plant label
        updatePlantLabels();
    }
    
    /**
     * This code updates the plant labels whenever. Uses the same instantiating 
     * code but just gets the new values from the cherry and poison ivy class 
     * instead of just being 0
     */
    public void updatePlantLabels()
    {
        numPoisonIvy = PoisonIvy.getNumPoisonIvy();
        numCherry = Cherry.getNumCherries();
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        bigPlantLabel.update(plantLabels);
    }
}
