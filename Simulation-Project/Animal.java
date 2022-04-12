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
    
    /**
     * Act - do whatever the Animal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
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
