import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Animation system integrating Mr. Cohens Animation and AnimationManager classes
 * 
 * @author (Lu-Wai) 
 * @version (a version number or a date)
 */
public class Animator extends SuperSmoothMover

{
    protected static int fps = 12;
    
    private Animation[] animations;
    private int curAnim; // index of current playing animation
    private int curFrame; // index of current frame
    private boolean playing;
    private int timer;

    /**
     * Constructor for objects of class Animator
     */
    public Animator()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void act(){
        if (playing){
            
            // Playing currently set animations
            timer++;
            if (timer >= fps){
                curFrame++;
                
                // Reset frame timer to zero if at end of animation
                if (curFrame < animations[curAnim].getFrames()){
                    setImage(animations[curAnim].getFrame(curFrame));
                }   else {
                    curFrame = 0;
                }
            }
        }
    }
    
    public void playAnimation(String name){
        for (int i =0 ; i < animations.length; i++){
            if (animations[i].getName().equals(name)){
                curAnim = i;
            }
        }
    }
    public void playAnimation(int index){
        if (index >= 0 && index < animations.length){
            curAnim = index;
        }
    }
    
    public void addAnimation(Animation anim){
        if (animations != null){
            Animation[] temp = new Animation[animations.length];
            for (int i =0;i < animations.length; i++){
                temp[i] = animations[i];
            }
            animations = temp;
            animations[animations.length-1] = anim;
        }
        else {
            animations = new Animation[1];
            animations[0] = anim;
        }
    }
    
    public void setAnimations(Animation[] anim){
        animations = anim;
    }
}