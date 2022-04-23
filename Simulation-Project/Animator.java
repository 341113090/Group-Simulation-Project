import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Animation system integrating Mr. Cohens Animation and AnimationManager
 * classes
 * 
 * @author (Lu-Wai)
 * @version (a version number or a date)
 */
public class Animator extends SuperSmoothMover

{
    protected static int fps = 6;

    protected Animation[] animations;
    private int curAnim; // index of current playing animation
    private int curFrame; // index of current frame
    private boolean playing;
    private int timer;

    /**
     * Constructor for objects of class Animator
     */
    public Animator() {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public void act() {
        if (playing) {
            // Playing currently set animations
            timer++;
            if (timer >= 60.0 / (double) fps) {
                System.out.println(curFrame);
                timer = 0;
                curFrame++;

                // Reset frame timer to zero if at end of animation
                if (curFrame < animations[curAnim].getFrames()) {
                    setImage(animations[curAnim].getFrame(curFrame));
                } else {
                    curFrame = 0;
                    setImage(animations[curAnim].getFrame(curFrame));
                }
            }
        }

    }

    public void playAnimation(String name) {
        playing = true;
        for (int i = 0; i < animations.length; i++) {
            // System.out.println(animations[i].getName().equals(name));
            if (animations[i].getName().equals(name)) {
                curAnim = i;
                break;
            }
        }
    }

    public void playAnimation(int index) {
        playing = true;
        if (index >= 0 && index < animations.length) {
            curAnim = index;
        }
    }

    public void addAnimation(Animation anim) {
        if (animations != null) {
            System.out.println("Added new");
            Animation[] temp = new Animation[animations.length + 1];
            for (int i = 0; i < animations.length; i++) {
                temp[i] = animations[i];
            }
            animations = temp;
            animations[animations.length - 1] = anim;
            System.out.println(temp.length);
        } else {
            System.out.println("first");
            animations = new Animation[1];
            animations[0] = anim;
        }
    }

    public void setAnimations(Animation[] anim) {
        animations = anim;
    }
}
