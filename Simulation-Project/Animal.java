import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animal extends Animator
{
    public enum State{Waiting, Searching, Attacking, Running}
    
    private State state;
    
    
    // Animal variables
    protected double speed; // Animal movement speed, increases more health decay when moving
    protected int attack;  // Animal attack damage, decreases health
    protected int health; // Animal health/hp, decreases speed
    protected double senseRange; // How far animal can detect threats/food, increases health decay
    protected double healthDecay; // How fast animals health goes down, hunger
    protected double altruism; // Chance of animal giving up its spot
    
    /**
     * Act - do whatever the Animal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        // Add your action code here.
        // Super basic awful statemanager
        
        switch (state){
            case Waiting:
                Waiting();
                break;
            case Searching:
                Searching();
                break;
        }
        
        Behaviour();
    }
    
    protected void Behaviour(){
        
    }
    
    ///// STATES /////
    protected void Waiting(){
        
    }
    
    protected void Searching(){
        
    }
}
