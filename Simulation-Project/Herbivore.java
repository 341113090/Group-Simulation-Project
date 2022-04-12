import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Herbivore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Herbivore extends Animal
{
    public Herbivore(){
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        setImage(AnimationManager.getSlice(img, 0,0,16,16));
    }
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
