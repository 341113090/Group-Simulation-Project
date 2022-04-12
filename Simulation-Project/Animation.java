import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;


class Animation {
    private GreenfootImage[] nonDirectionalImages;

    private boolean directional;

    private int directions;
    /**
     * Constructor for directional animations - should be a 2d array of GreenfootImage with
     * 4 directions (dimension 1) and at least one image per direction (dimension 2)
     *
     * @param images    2d array of images as described above
     * @param terminal  true if this animation is not intended to repeat
     */


    public Animation (GreenfootImage[] images){
        this.directional = false;
        nonDirectionalImages = images;

        directions = 1;
    }

    public void setImages (GreenfootImage[] images){
        nonDirectionalImages = images;
    }

    public boolean isDirectional (){
        return this.directional;
    }

    //public GreenfootImage getOneImage (AnimatedCharacter.Direction d, int frame){
    //    return directionalImages[d.getDirection()][frame];
    //}

    public GreenfootImage[] getNonDirectionalImages (){
        return nonDirectionalImages;
    }

}
