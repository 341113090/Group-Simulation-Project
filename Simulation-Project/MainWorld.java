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
    private int dayLength;
    private int nightLength;
    
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
        addObject(fg,0,0);
        drawNight();
    }

    
    public void act(){
        
    }
    
    private void timeManager(){
        currentTime++;
        
        if (currentTime > dayLength){
            
        }
    }
    
    private GreenfootImage drawNight(){
        GreenfootImage dark = new GreenfootImage (getWidth(), getHeight());
        dark.setColor(Color.BLACK);
        dark.fillRect(0,0,dark.getWidth(),dark.getHeight());

        fg.setImage(dark);
        return dark;
    }
}
