import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shelter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shelter extends Actor
{
    /**
     * Act - do whatever the Shelter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int limitAnimal;
    private Animal typeAnimal;
    private boolean isFull;
    
    public Shelter(){
        limitAnimal = 2;
        isFull = false;
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public boolean limitHit(){
        if(isFull){
            return true;
        }
        return false;
    }
    
    
}
