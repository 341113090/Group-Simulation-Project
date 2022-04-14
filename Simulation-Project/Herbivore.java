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
        
        ///// Setting up animations /////
        GreenfootImage img = new GreenfootImage("spritesheet.png");
        addAnimation(AnimationManager.createAnimation(img,3*16,8*16,1, 8,8,16,16, "Idle"));
        setImage(animations[0].getImage(0));
        //playAnimation(0);
        state=State.Waiting;
    }
    /**
     * Act - do whatever the Herbivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        // Add your action code here.
        
        playAnimation("Idle");    
        playAnimation(0);
    }
    
    public void started(){
        
    }
    
    public void stopped(){
        System.out.println("How");
    }
}
