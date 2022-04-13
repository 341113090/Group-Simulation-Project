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
    private int dayNumber;
    private int numCherry;
    private int numPoisonIvy;
    private int startNumCherry = 4;
    private int startNumPoisonIvy = 9;
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
    }
}
