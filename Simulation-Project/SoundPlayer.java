import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that is used to play all sounds in the simulation
 * 
 * 
 * 
 * @author Nathan Thian
 * @version March 30, 2022
 */
public class SoundPlayer extends Actor
{
    private GreenfootSound wind = new GreenfootSound("wind.wav");
    private GreenfootSound ambience = new GreenfootSound("ambience.wav");
    private GreenfootSound[] vroomSounds;
    private int vroomSoundsIndex;
    private GreenfootSound[] honkSounds;
    private int honkSoundsIndex;
    private GreenfootSound[] crushSounds;
    private int crushSoundsIndex;
    private GreenfootSound[] explosionSounds;
    private int explosionSoundsIndex;
    
    /**
     * When the sound player is crated, it makes arrays for all the sounds that may be played at the same time
     */
    public SoundPlayer()
    {
        //all index starts at 0 and all arrays have 20 copies of the sound
        vroomSoundsIndex = 0;
        vroomSounds = new GreenfootSound[20];
        for(int i = 0;i < vroomSounds.length; i++)
        {
            vroomSounds[i] = new GreenfootSound("vroom.wav");
        }
        honkSoundsIndex = 0;
        honkSounds = new GreenfootSound[20];
        for(int i = 0;i < honkSounds.length; i++)
        {
            honkSounds[i] = new GreenfootSound("honk.wav");
        }
        crushSoundsIndex = 0;
        crushSounds = new GreenfootSound[20];
        for(int i = 0;i < crushSounds.length; i++)
        {
            crushSounds[i] = new GreenfootSound("crush.wav");
        }
        explosionSoundsIndex = 0;
        explosionSounds = new GreenfootSound[20];
        for(int i = 0;i < explosionSounds.length; i++)
        {
            explosionSounds[i] = new GreenfootSound("explosion.wav");
        }
    }
    
    /**
     * Sound player does not do anything in its act method
     */
    public void act()
    {
        
    }
    
    
    /**
     * Will play the ambience sound in an infinite loop
     */
    public void playAmbience()
    {
        ambience.playLoop();
    }
    
    /**
     * Will play the wind sound once
     */
    public void playWind()
    {
        wind.play();
    }
    
    /**
     * Will play the vroom sound whenever called
     */
    public void playVroom()
    {
        vroomSounds[vroomSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        vroomSoundsIndex++;
        if(vroomSoundsIndex > vroomSounds.length - 1)
        {
            vroomSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the crush sound whenever called
     */
    public void playCrush()
    {
        crushSounds[crushSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        crushSoundsIndex++;
        if(crushSoundsIndex > crushSounds.length - 1)
        {
            crushSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the explosion sound whenever called
     */
    public void playExplosion()
    {
        explosionSounds[explosionSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        explosionSoundsIndex++;
        if(explosionSoundsIndex > explosionSounds.length - 1)
        {
            explosionSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the honk sound whenever called
     */
    public void playHonk()
    {
        honkSounds[honkSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        honkSoundsIndex++;
        if(honkSoundsIndex > honkSounds.length - 1)
        {
            honkSoundsIndex = 0;
        }
    }
}