import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Harmful here.
 * 
 * @author (Max) 
 * @version (a version number or a date)
 */
public class PoisonIvy extends Plant
{
    private static int numPoisonIvy = 0;
    GreenfootImage ivy = AnimationManager.getSlice(Plants,12, 3);
    private double toxicityTakenAway = 10;
    private int day = MainWorld.dayNumber;
    private int nextDay = day + 1;
    /**
     * Sets basic variables for health, toughness, toxicity, health limit
     * and heal speed.
     */
    public PoisonIvy()
    {
        numPoisonIvy++;
        this.setImage(ivy);
        toughness = .5;//poison ivey is weaker than cherry
        health = 1000;
        maxHealth = health;
        healthPerTick = 8;
        isToxic = true;
        wantsCarry = false;
        //getter not needed
        healthLimit = 750;
        selfHealSpeed = 3;
    }
    
    /**
     * Returns the number of Poison Ivy in the world. 
     */
    public static int getNumPoisonIvy()
    {
        return numPoisonIvy;
    }
    
    /**
     * Sets the number of Poison Ivy in the world.
     */
    public static void setNumPoisonIvy(int xx)
    {
        numPoisonIvy = xx;
    }
    
    /**
     * Checks if this instance of Poison Ivy is eaten and subtracts one from
     * the overall number of Poison Ivy in the world.
     */
    public void deathCheck()
    {
        if(health == 0){
            numPoisonIvy--;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * This method allows the Poison Ivy to reproduce every day cycle until it caps
     * at around 30. 
     */
    public void reproduce(){
        if(numPoisonIvy > 30){
            return;
        }
        if(day == nextDay){
            nextDay++;
            if(this.getX() >= 650 || this.getY() <= 100){ // too right and too high
                getWorld().addObject(new PoisonIvy(), this.getX() - 50, this.getY()+50);
            } else if(this.getX() >= 650 || this.getY() <= 400){//too right and too low
                getWorld().addObject(new PoisonIvy(), this.getX() - 50, this.getY()-50);
            } else if(this.getX() <= 150 || this.getY() <= 100){//too left and too high
                getWorld().addObject(new PoisonIvy(), this.getX() + 50, this.getY()+50);
            } else if(this.getX() >= 150 || this.getY() <= 400){//too left and too low
                getWorld().addObject(new PoisonIvy(), this.getX() + 50, this.getY()-50);
            } else{
                getWorld().addObject(new PoisonIvy(), this.getX() + 50, this.getY()-50);
            }            
        }
    }
    
    /**
     * Calls superclass act().
     */
    public void act()
    {
        day = MainWorld.dayNumber;
        reproduce();
        super.act();
    }
}
