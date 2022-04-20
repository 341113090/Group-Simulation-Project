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
    
    ///// DAY NIGHT CYCLE /////
    private int dayNumber;
    private int currentTime;
    private int dayLength;
    private int nightLength;
    private double maxDarkness = 100;
    
    // Foreground object for night effects
    private Foreground fg;
    
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
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        bigPlantLabel = new SuperTextBox(plantLabels,boringFont,this.getWidth(),false); 
        int tempY = bigPlantLabel.getImage().getHeight()/2;
        int tempX = bigPlantLabel.getImage().getWidth()/2;
        addObject(bigPlantLabel, tempX, tempY);
        for(int i = 0; i<startNumCherry; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = bigPlantLabel.getImage().getHeight()/2 + 30 + random.nextInt(300);
            addObject(new Cherry(), xx, yy);
        }
        for(int i = 0; i<startNumPoisonIvy; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = 50+ random.nextInt(300);
            addObject(new PoisonIvy(), xx, yy);
        }
        
        
        addObject(fg,0,0);
    }
    
    /**
     * In the act method, the world keeps track of how many cherries and ivies 
     * there are.
     */
    public void act()
    {
        //Make an arraylist filled with cherries
        ArrayList<Cherry> cherries = (ArrayList<Cherry>) this.getObjects(Cherry.class);
        int numIterationsCherry = 0;
        for(Cherry cherry : cherries)
        {
            //iterate through every cherry. For every cherry keep track of how many
            numIterationsCherry++;
        }
        //current number of cherries is the number of iterations
        numCherry = numIterationsCherry;
        //Make an arraylist filled with ivy
        ArrayList<PoisonIvy> ivies = (ArrayList<PoisonIvy>) this.getObjects(PoisonIvy.class);
        int numIterationsIvy = 0;
        for(PoisonIvy ivy : ivies)
        {
            //iterate through every ivy. For every ivy keep track of how many
            numIterationsIvy++;
        }
        numPoisonIvy = numIterationsIvy;
        String cherryLabel = new String("Number of Cherries in the World: " + numCherry);
        String poisonivyLabel = new String("Number of Poison Ivy in the World: " + numPoisonIvy);
        plantLabels[0] = cherryLabel;
        plantLabels[1] = poisonivyLabel;
        bigPlantLabel.update(plantLabels);
    }
    
    private void timeManager(){
        currentTime++;
        
        if (currentTime > dayLength){
            double transparency = (double)(currentTime-dayLength)/(double)nightLength;
            drawNight(transparency*maxDarkness);
            
            if (currentTime > dayLength+nightLength) currentTime = 0;
        }      else {
            drawNight(0);
        }
    }
    
    private GreenfootImage drawNight(double transparency){
        GreenfootImage dark = new GreenfootImage (getWidth(), getHeight());
        dark.setColor(Color.BLACK);
        dark.fillRect(0,0,dark.getWidth(),dark.getHeight());
        dark.setTransparency((int)transparency);

        fg.setImage(dark);
        return dark;
    }
}
