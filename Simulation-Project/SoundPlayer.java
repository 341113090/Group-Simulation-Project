import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that is used to play all sounds in the simulation
 * 
 * Credits: 
 * backgroundMusic sound from 1pjiadd2 on https://freesound.org/people/1pjladd2/sounds/155362/
 * seedDrop sound from darkorochi on https://freesound.org/people/DarkOrochi/sounds/322246/
 * plantGrowth sound from yxmusic on https://freesound.org/people/YXMusic/sounds/423653/
 * herbivoreEating sound from herrabilbo on https://freesound.org/people/HerraBilbo/sounds/360685/
 * carnivoreEating sound from eminyildrim on https://freesound.org/people/EminYILDIRIM/sounds/536078/
 * nightCycle is a combination of ambience1CricketsAtNight sound by messyacousticapocalypse666 on https://freesound.org/people/MessyAcousticApocalypse666/sounds/594779/ and  * owlHoot sound from breviceps https://freesound.org/people/Breviceps/sounds/465697/
 *  
 * 
 * @author Nathan Thian and Ethan Wong
 * @version April 28, 2022
 */
public class SoundPlayer extends Actor
{
    public static SoundPlayer instance;
    
    private GreenfootSound backgroundMusic = new GreenfootSound("backgroundMusic.wav");
    private GreenfootSound nightCycleSounds = new GreenfootSound("nightCycle.wav");
    
    private GreenfootSound[] seedDropSounds;
    private int seedDropSoundsIndex;
    private GreenfootSound[] plantGrowthSounds;
    private int plantGrowthSoundsIndex;
    private GreenfootSound[] herbivoreEatingSounds;
    private int herbivoreEatingSoundsIndex;
    private GreenfootSound[] carnivoreEatingSounds;
    private int carnivoreEatingSoundsIndex;
    
    /**
     * When the sound player is crated, it makes arrays for all the sounds that may be played at the same time
     */
    public SoundPlayer()
    {
        instance = this;
        
        //all index starts at 0 and all arrays have 20 copies of the sound
        seedDropSoundsIndex = 0;
        seedDropSounds = new GreenfootSound[20];
        for(int i = 0;i < seedDropSounds.length; i++)
        {
            seedDropSounds[i] = new GreenfootSound("seedDrop.mp3");
        }
        plantGrowthSoundsIndex = 0;
        plantGrowthSounds = new GreenfootSound[20];
        for(int i = 0;i < plantGrowthSounds.length; i++)
        {
            plantGrowthSounds[i] = new GreenfootSound("plantGrowth.wav");
        }
        herbivoreEatingSoundsIndex = 0;
        herbivoreEatingSounds = new GreenfootSound[20];
        for(int i = 0;i < herbivoreEatingSounds.length; i++)
        {
            herbivoreEatingSounds[i] = new GreenfootSound("herbivoreEating.wav");
        }
        carnivoreEatingSoundsIndex = 0;
        carnivoreEatingSounds = new GreenfootSound[20];
        for(int i = 0;i < carnivoreEatingSounds.length; i++)
        {
            carnivoreEatingSounds[i] = new GreenfootSound("carnivoreEating.wav");
        }
    }
    
    /**
     * Sound player does not do anything in its act method
     */
    public void act()
    {
        
    }
    
    
    /**
     * Will play the backgroundMusic sound in an infinite loop
     */
    public void playBackgroundMusic()
    {
        backgroundMusic.playLoop();
    }
    
    public void stopBackgroundMusic()
    {
        backgroundMusic.stop();
    }
    
    /**
     * Will play the nightCycleSounds once
     */
    public void playNightCycleSounds()
    {
        nightCycleSounds.playLoop();
    }
    
    public void stopNightCycleSounds()
    {
        nightCycleSounds.stop();
    }
    
    /**
     * Will play the seedDropSounds whenever called
     */
    public void playSeedDropSounds()
    {
        seedDropSounds[seedDropSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        seedDropSoundsIndex++;
        if(seedDropSoundsIndex > seedDropSounds.length - 1)
        {
            seedDropSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the herbivoreEatingSounds whenever called
     */
    public void playHerbivoreEatingSounds()
    {
        herbivoreEatingSounds[herbivoreEatingSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        herbivoreEatingSoundsIndex++;
        if(herbivoreEatingSoundsIndex > herbivoreEatingSounds.length - 1)
        {
            herbivoreEatingSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the carnivoreEatingSounds whenever called
     */
    public void playCarnivoreEatingSounds()
    {
        carnivoreEatingSounds[carnivoreEatingSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        carnivoreEatingSoundsIndex++;
        if(carnivoreEatingSoundsIndex > carnivoreEatingSounds.length - 1)
        {
            carnivoreEatingSoundsIndex = 0;
        }
    }
    
    /**
     * Will play the plantGrowthSounds whenever called
     */
    public void playPlantGrowthSounds()
    {
        plantGrowthSounds[plantGrowthSoundsIndex].play();
        //once played once, the index switches to another copy so the sounds can be played simultaneously if needed
        plantGrowthSoundsIndex++;
        if(plantGrowthSoundsIndex > plantGrowthSounds.length - 1)
        {
            plantGrowthSoundsIndex = 0;
        }
    }
}