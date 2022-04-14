import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
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
    private int dayLength = 600;
    private int nightLength = 300;
    private double maxDarkness = 100;
    
    // Foreground object for night effects
    private Foreground fg;
    
    private int numCherry;
    private int numPoisonIvy;
    private int startNumCherry = 5;
    private int startNumPoisonIvy = 5;
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        for(int i = 0; i<startNumCherry; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = 50+ random.nextInt(300);
            addObject(new Cherry(), xx, yy);
        }
        for(int i = 0; i<startNumPoisonIvy; i++)
        {
            Random random = new Random();
            int xx = 50 + random.nextInt(500);
            int yy = 50+ random.nextInt(300);
            addObject(new PoisonIvy(), xx, yy);
        }
        
        addObject(new Herbivore(), 100, 100);
        fg = new Foreground();
        addObject(fg,getWidth()/2,getHeight()/2);
    }

    
    public void act(){
        timeManager();
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
