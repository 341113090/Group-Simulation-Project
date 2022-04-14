import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plants are eaten by animals and provide them with nutrients. Plants also try and 
 * survive until the end of the day so that they can reproduce. Poisonous plants 
 * reproduce by surviving and edible plants reproduce by having their seeds eaten.
 * 
 * @author Nathan and Max
 * @version April 12, 2022
 */
public class Plant extends Animator
{
    //getter required
    protected double toughness;
    protected int totalSeeds;
    protected int health;
    protected int healthPerTick;
    protected boolean isToxic;
    protected boolean wantsCarry;
    //getter not needed
    protected int healthLimit;
    protected int selfHealSpeed;
    protected boolean isGettingEaten;
    ////////////////////Need to instalize all these vars above in the subclass
    
    protected SuperStatBar hpBar;
    //misc
    protected GreenfootImage Plants = new GreenfootImage("Plants.png");
    public Plant()
    {
        hpBar = new SuperStatBar(health,health,this,48,4,10,Color.GREEN,Color.GREEN,false,Color.BLACK,1);
        isGettingEaten = false;
    }
    
    public void addedToWorld (World w)
    {
        w.addObject (hpBar, getX(), getY());
        hpBar.update(health);
    }
    
    /**
     * Act - do whatever the Plant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        hpBar.update(health);
        if(!isGettingEaten){
            growing();
        }
        deathCheck();
    }
    
    //next methods actually are being used
    /**
     * This method checks if the plant has no more health and removes itself.
     */
    public void deathCheck(){
        if(health == 0){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * The method for how much damage is being taken when being eaten and returns
     * the amount of health the herbervoire regenerates.
     * 
     * 
     * Note: needs to take in percentage of health and determine if they should
     * give a seed or not to the herbarvoire
     */
    public double takeDamage(){
        isGettingEaten = true;
        double actualTick = healthPerTick*toughness;//might be a problem later for statbar
        if(health>=actualTick){
            health-=actualTick;//temporary, will depend on plant health
            return actualTick;
        }else{
            int lastHealth = health;
            health = 0;
            return lastHealth;
        }
    }
    
    /**
     * This method is called when the plant is not being eaten and it is healing
     */
    public void growing(){
        if(health<=healthLimit-selfHealSpeed){ // so it doesn't go over limit
            health += selfHealSpeed;
        }
    }
   
    //these methods are all getters for other classes    
    /**
     * returns the health of plant
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * returns the health given by the plant
     */
    public int giveHealth(){
        return healthPerTick;
    }
    
    /**
     * returns the number of seeds of the plant
     */
    public int getSeeds(){
        return totalSeeds;
    }
    
    /**
     * return the toughness of the plant
     */
    public double getToughness(){
        return toughness;
    }
    
    /**
     * returns whether the plant is toxic or not
     */
    public boolean isToxic(){
        return isToxic;
    }
    
    /**
     * returns whether the plant wants animals to eat it to carry seeds or not
     */
    public boolean wantsCarry(){
        return wantsCarry;
    }
    
    /**
     * Setter method for if the plant is being eaten or not. Use this method in
     * the herbovoire class
     */
    public void setEating(boolean a){
        isGettingEaten = a; 
    }
}
