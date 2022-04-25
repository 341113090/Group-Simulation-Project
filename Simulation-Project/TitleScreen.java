import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This is the titlescreen before you get into the simulation
 * You can change the number of each element you want inside before the sim starts
 * 
 * @author Nathan Thian
 * @version April 24, 2022
 */
public class TitleScreen extends World
{
    //the font that is used for everything
    Font boringFont = new Font ("Times New Roman", false, false, 14);
    
    //different options for selections
    //change as you see fit
    //options for cherry
    private Integer cherryOptOne = new Integer(5);
    private Integer cherryOptTwo = new Integer(10);
    private Integer cherryOptThree = new Integer(15);
    //options for poisonivy
    private Integer poisonIvyOptOne = new Integer(5);
    private Integer poisonIvyOptTwo = new Integer(10);
    private Integer poisonIvyOptThree = new Integer(15);
    //options for herbivores
    private Integer herbOptOne = new Integer(5);
    private Integer herbOptTwo = new Integer(10);
    private Integer herbOptThree = new Integer(15);
    //options for carnivores
    private Integer carnOptOne = new Integer(5);
    private Integer carnOptTwo = new Integer(10);
    private Integer carnOptThree = new Integer(15);
    //options for number of shelters
    private Integer shelterOptOne = new Integer(2);
    private Integer shelterOptTwo = new Integer(4);
    private Integer shelterOptThree = new Integer(6);
    private Integer shelterOptFour = new Integer(8);
    
    //these variables keep track of the mouse's position on the screen
    private int mx = 0;
    private int my = 0;
    
    //these variables keep track of the number that the user has selected for the components in the mian world
    private int cherryNum = 0;
    private int poisonIvyNum = 0;
    private int herbNum = 0;
    private int carnNum = 0;
    private int shelterNum = 0;
    
    //these arraylists keep track of all the differen types of option buttons for each type of componenet
    ArrayList<SuperTextBox> cherryTextBoxes;
    ArrayList<SuperTextBox> poisonIvyTextBoxes;
    ArrayList<SuperTextBox> carnTextBoxes;
    ArrayList<SuperTextBox> herbTextBoxes;
    ArrayList<SuperTextBox> shelterTextBoxes;
    
    //these rectangles are the shaded boxes that show up on selected options on the screen
    Rectangle cherrySelected;
    Rectangle poisonIvySelected;
    Rectangle carnSelected;
    Rectangle herbSelected;
    Rectangle shelterSelected;
    
    //this is the background for the titlescreen
    GreenfootImage background = new GreenfootImage("TitleBackground.png");
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        
        //sets the background
        setBackground(background);
        
        //gets all the items on screen ready
        prepare();
    }
    
    /**
     * The main world act loop
     * keeps track of everything that happens and allows user to interact
     */
    public void act()
    {
        //method that checks for user clicking options
        checkForSelection();
        //method that darkens options that have been selected
        showSelection();
        //starts game with selected options if user presses <space>
        startMethod();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the labels to show the name of the game
     * as well as how to start
     */
    private void prepare()
    {
        // labels for title
        Label label = new Label("Natural Selection Simulator", 50);
        addObject(label,getWidth()/2,22);
        label.setFillColor(Color.BLACK);
        Label label2 = new Label("Press <space> to start", 20);
        addObject(label2,getWidth()/2,getHeight()*8/9);
        label2.setFillColor(Color.BLACK);
        
        //tells users which components they are selecting for customization options
        Label cherry = new Label("Cherries", 20);
        cherry.setFillColor(Color.BLACK);
        addObject(cherry,getWidth()/6, getHeight()/5);
        Label poisonIvy = new Label("Poison Ivies", 20);
        poisonIvy.setFillColor(Color.BLACK);
        addObject(poisonIvy,getWidth()*2/6, getHeight()/5);
        Label herbivores = new Label("Herbivores", 20);
        herbivores.setFillColor(Color.BLACK);
        addObject(herbivores,getWidth()*3/6, getHeight()/5);
        Label carnivores = new Label("Carnivores", 20);
        carnivores.setFillColor(Color.BLACK);
        addObject(carnivores,getWidth()*4/6, getHeight()/5);
        Label shelters = new Label("Shelters", 20);
        shelters.setFillColor(Color.BLACK);
        addObject(shelters,getWidth()*5/6, getHeight()/5);
        
        //adding boxes that you can click for customizing
        //cherry buttons
        //sets buttons' values corresponding to the values set in the variables created during initialization
        SuperTextBox cherryButtonOne = new SuperTextBox(cherryOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonOne, getWidth()/6, getHeight()*2/5);
        //first option default to selected
        cherryButtonOne.setIsSelected(true);
        cherryButtonOne.setValue(cherryOptOne);
        SuperTextBox cherryButtonTwo = new SuperTextBox(cherryOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonTwo, getWidth()/6, getHeight()*3/5);
        cherryButtonTwo.setValue(cherryOptTwo);
        SuperTextBox cherryButtonThree = new SuperTextBox(cherryOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonThree, getWidth()/6, getHeight()*4/5);
        cherryButtonThree.setValue(cherryOptThree);
        //add each button to the corresponding arraylist
        cherryTextBoxes = new ArrayList<SuperTextBox>();
        cherryTextBoxes.add(cherryButtonOne);
        cherryTextBoxes.add(cherryButtonTwo);
        cherryTextBoxes.add(cherryButtonThree);
        //creates the grey box that signifies selection on top of the first button
        cherrySelected = new Rectangle(cherryButtonOne.getWidth(),cherryButtonOne.getHeight(), 128);
        addObject(cherrySelected,cherryButtonOne.getX(),cherryButtonOne.getY());
        
        //poison ivy buttons
        //sets buttons' values corresponding to the values set in the variables created during initialization
        SuperTextBox poisonIvyButtonOne = new SuperTextBox(poisonIvyOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonOne, getWidth()*2/6, getHeight()*2/5);
        //first option default to selected
        poisonIvyButtonOne.setIsSelected(true);
        poisonIvyButtonOne.setValue(poisonIvyOptOne);
        SuperTextBox poisonIvyButtonTwo = new SuperTextBox(poisonIvyOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonTwo, getWidth()*2/6, getHeight()*3/5);
        poisonIvyButtonTwo.setValue(poisonIvyOptTwo);
        SuperTextBox poisonIvyButtonThree = new SuperTextBox(poisonIvyOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonThree, getWidth()*2/6, getHeight()*4/5);
        poisonIvyButtonThree.setValue(poisonIvyOptThree);
        //add each button to the corresponding arraylist
        poisonIvyTextBoxes = new ArrayList<SuperTextBox>();
        poisonIvyTextBoxes.add(poisonIvyButtonOne);
        poisonIvyTextBoxes.add(poisonIvyButtonTwo);
        poisonIvyTextBoxes.add(poisonIvyButtonThree);
        //creates the grey box that signifies selection on top of the first button
        poisonIvySelected = new Rectangle(poisonIvyButtonOne.getWidth(),poisonIvyButtonOne.getHeight(), 128);
        addObject(poisonIvySelected,poisonIvyButtonOne.getX(),poisonIvyButtonOne.getY());
        
        //herbivore buttons
        //sets buttons' values corresponding to the values set in the variables created during initialization
        SuperTextBox herbButtonOne = new SuperTextBox(herbOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonOne, getWidth()*3/6, getHeight()*2/5);
        //first option default to selected
        herbButtonOne.setIsSelected(true);
        herbButtonOne.setValue(herbOptOne);
        SuperTextBox herbButtonTwo = new SuperTextBox(herbOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonTwo, getWidth()*3/6, getHeight()*3/5);
        herbButtonTwo.setValue(herbOptTwo);
        SuperTextBox herbButtonThree = new SuperTextBox(herbOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonThree, getWidth()*3/6, getHeight()*4/5);
        herbButtonThree.setValue(herbOptThree);
        //add each button to the corresponding arraylist
        herbTextBoxes = new ArrayList<SuperTextBox>();
        herbTextBoxes.add(herbButtonOne);
        herbTextBoxes.add(herbButtonTwo);
        herbTextBoxes.add(herbButtonThree);
        //creates the grey box that signifies selection on top of the first button
        herbSelected = new Rectangle(herbButtonOne.getWidth(),herbButtonOne.getHeight(), 128);
        addObject(herbSelected,herbButtonOne.getX(),herbButtonOne.getY());
        
        //carnivore buttons
        //sets buttons' values corresponding to the values set in the variables created during initialization
        SuperTextBox carnButtonOne = new SuperTextBox(carnOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonOne, getWidth()*4/6, getHeight()*2/5);
        //first option default to selected
        carnButtonOne.setIsSelected(true);
        carnButtonOne.setValue(carnOptOne);
        SuperTextBox carnButtonTwo = new SuperTextBox(carnOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonTwo, getWidth()*4/6, getHeight()*3/5);
        carnButtonTwo.setValue(carnOptTwo);
        SuperTextBox carnButtonThree = new SuperTextBox(carnOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonThree, getWidth()*4/6, getHeight()*4/5);
        carnButtonThree.setValue(carnOptThree);
        //add each button to the corresponding arraylist
        carnTextBoxes = new ArrayList<SuperTextBox>();
        carnTextBoxes.add(carnButtonOne);
        carnTextBoxes.add(carnButtonTwo);
        carnTextBoxes.add(carnButtonThree);
        //creates the grey box that signifies selection on top of the first button
        carnSelected = new Rectangle(carnButtonOne.getWidth(),carnButtonOne.getHeight(), 128);
        addObject(carnSelected,carnButtonOne.getX(),carnButtonOne.getY());
        
        //shelter buttons
        //sets buttons' values corresponding to the values set in the variables created during initialization
        SuperTextBox shelterButtonOne = new SuperTextBox(shelterOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonOne, getWidth()*5/6, getHeight()*2/6);
        //first option default to selected
        shelterButtonOne.setIsSelected(true);
        shelterButtonOne.setValue(shelterOptOne);
        SuperTextBox shelterButtonTwo = new SuperTextBox(shelterOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonTwo, getWidth()*5/6, getHeight()*3/6);
        shelterButtonTwo.setValue(shelterOptTwo);
        SuperTextBox shelterButtonThree = new SuperTextBox(shelterOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonThree, getWidth()*5/6, getHeight()*4/6);
        shelterButtonThree.setValue(shelterOptThree);
        SuperTextBox shelterButtonFour = new SuperTextBox(shelterOptFour.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonFour, getWidth()*5/6, getHeight()*5/6);
        shelterButtonFour.setValue(shelterOptFour);
        //add each button to the corresponding arraylist
        shelterTextBoxes = new ArrayList<SuperTextBox>();
        shelterTextBoxes.add(shelterButtonOne);
        shelterTextBoxes.add(shelterButtonTwo);
        shelterTextBoxes.add(shelterButtonThree);
        shelterTextBoxes.add(shelterButtonFour);
        //creates the grey box that signifies selection on top of the first button
        shelterSelected = new Rectangle(shelterButtonOne.getWidth(),shelterButtonOne.getHeight(), 128);
        addObject(shelterSelected,shelterButtonOne.getX(),shelterButtonOne.getY());
    }
    
    /**
     * This method with activate when the user clicks on a button they want to select
     * should darken option that has been selected and deselect the options that 
     * were not chose
     */
    public void checkForSelection()
    {
        //get all the buttons in the world
        ArrayList<SuperTextBox> allBoxes = (ArrayList<SuperTextBox>) getObjects(SuperTextBox.class);
        for(SuperTextBox here : allBoxes)
        {
            //cycles through all the buttons
            if(Greenfoot.mouseClicked(here))
            {
                //if the mouse is clicked, check where the mouse is
                MouseInfo mouse = Greenfoot.getMouseInfo();
                if(mouse!=null){
                   mx = mouse.getX();
                   my = mouse.getY();
                }
                //create a tiny invisible rectangle at the location of the mouse press 
                Rectangle checker = new Rectangle(1,1,0);
                addObject(checker,mx,my);
                //get all the buttons that the checker rectangle is currently touching; basically whatever the user is trying to select
                ArrayList<SuperTextBox> textBoxes = checker.getIntersectingTextBoxes();
                for(SuperTextBox box : textBoxes)
                {
                    //cycle thrugh every button that the checker rectangle is touching
                    for(SuperTextBox test : cherryTextBoxes)
                    {
                        //for every cherry button in the world check if that is the button being clicked by the user
                        if(test.equals(box))
                        {
                            //if this specific cherry button is being selected, deselect every cherry button in the world
                            for(SuperTextBox cherryLabels : cherryTextBoxes)
                            {
                                cherryLabels.setIsSelected(false);
                            }
                            //set the one selected cherry button to be the only selected one
                            box.setIsSelected(true);
                        }
                    }
                    //cycle thrugh every button that the checker rectangle is touching
                    for(SuperTextBox test : poisonIvyTextBoxes)
                    {
                        //for every poison ivy button in the world check if that is the button being clicked by the user
                        if(test.equals(box))
                        {
                            //if this specific poison ivy button is being selected, deselect every poison ivy button in the world
                            for(SuperTextBox poisonIvyLabels : poisonIvyTextBoxes)
                            {
                                poisonIvyLabels.setIsSelected(false);
                            }
                            //set the one selected poison ivy button to be the only selected one
                            box.setIsSelected(true);
                        }
                    }
                    //cycle thrugh every button that the checker rectangle is touching
                    for(SuperTextBox test : herbTextBoxes)
                    {
                        //for every herbivore button in the world check if that is the button being clicked by the user
                        if(test.equals(box))
                        {
                            //if this specific herbivore button is being selected, deselect every herbivore button in the world
                            for(SuperTextBox herbLabels : herbTextBoxes)
                            {
                                herbLabels.setIsSelected(false);
                            }
                            //set the one selected herbivore button to be the only selected one
                            box.setIsSelected(true);
                        }
                    }
                    //cycle thrugh every button that the checker rectangle is touching
                    for(SuperTextBox test : carnTextBoxes)
                    {
                        //for every carnivore button in the world check if that is the button being clicked by the user
                        if(test.equals(box))
                        {
                            //if this specific carnivore button is being selected, deselect every carnivore button in the world
                            for(SuperTextBox carnLabels : carnTextBoxes)
                            {
                                carnLabels.setIsSelected(false);
                            }
                            //set the one selected carnivore button to be the only selected one
                            box.setIsSelected(true);
                        }
                    }
                    //cycle thrugh every button that the checker rectangle is touching
                    for(SuperTextBox test : shelterTextBoxes)
                    {
                        //for every shelter button in the world check if that is the button being clicked by the user
                        if(test.equals(box))
                        {
                            //if this specific shelter button is being selected, deselect every shelter button in the world
                            for(SuperTextBox shelterLabels : shelterTextBoxes)
                            {
                                shelterLabels.setIsSelected(false);
                            }
                            //set the one selected shelter button to be the only selected one
                            box.setIsSelected(true);
                        }
                    }
                }
                //remove the rectangle doing the checking
                removeObject(checker);
            }
        }
    }
    
    public void showSelection()
    {
        //get all the buttons in the world
        ArrayList<SuperTextBox> allBoxes = (ArrayList<SuperTextBox>) getObjects(SuperTextBox.class);
        for(SuperTextBox textBox : allBoxes)
        {
            //cycle through every button in the world
            if(textBox.checkSelected())
            {
                //if this specific button is indeed selected...
                for(SuperTextBox yes : cherryTextBoxes)
                {
                    //if this specific button is a cherry button
                    if(yes.equals(textBox))
                    {
                        //move the cherry rectangle that darkens the selection to this specific button
                        cherrySelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : poisonIvyTextBoxes)
                {
                    //if this specific button is a poison ivy button
                    if(yes.equals(textBox))
                    {
                        //move the poison ivy rectangle that darkens the selection to this specific button
                        poisonIvySelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : herbTextBoxes)
                {
                    //if this specific button is a herbivore button
                    if(yes.equals(textBox))
                    {
                        //move the herbivore rectangle that darkens the selection to this specific button
                        herbSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : carnTextBoxes)
                {
                    //if this specific button is a carnivore button
                    if(yes.equals(textBox))
                    {
                        //move the carnivore rectangle that darkens the selection to this specific button
                        carnSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : shelterTextBoxes)
                {
                    //if this specific button is a shelter button
                    if(yes.equals(textBox))
                    {
                        //move the shelter rectangle that darkens the selection to this specific button
                        shelterSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
            }
        }
    }
    
    /**
     * this method will transition the simulation from title screen into the actual simulation when space bar is pressed
     */
    public void startMethod()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            //loop through every different array of the different components' buttons
            for(SuperTextBox cherryLabels : cherryTextBoxes)
            {
                //get the cherry option that is selected and set that button's 
                //value as the value for cherries that will be used in the main world
                if(cherryLabels.checkSelected())
                {
                    cherryNum = cherryLabels.getValue();
                }
            }
            for(SuperTextBox poisonIvyLabels : poisonIvyTextBoxes)
            {
                //get the poison ivy option that is selected and set that button's 
                //value as the value for poison ivies that will be used in the main world
                if(poisonIvyLabels.checkSelected())
                {
                    poisonIvyNum = poisonIvyLabels.getValue();
                }
            }
            for(SuperTextBox herbLabels : herbTextBoxes)
            {
                //get the herbivore option that is selected and set that button's 
                //value as the value for herbivores that will be used in the main world
                if(herbLabels.checkSelected())
                {
                    herbNum = herbLabels.getValue();
                }
            }
            for(SuperTextBox carnLabels : carnTextBoxes)
            {
                //get the carnivore option that is selected and set that button's 
                //value as the value for carnivores that will be used in the main world
                if(carnLabels.checkSelected())
                {
                    carnNum = carnLabels.getValue();
                }
            }
            for(SuperTextBox shelterLabels : shelterTextBoxes)
            {
                //get the shelter option that is selected and set that button's 
                //value as the value for shelters that will be used in the main world
                if(shelterLabels.checkSelected())
                {
                    shelterNum = shelterLabels.getValue();
                }
            }
            //instantiate a new world (which is the actual simulation world) using the options selected here
            MainWorld mainworld = new MainWorld(cherryNum, poisonIvyNum, herbNum, carnNum, shelterNum);
            //transition to that new world
            Greenfoot.setWorld(mainworld);
        }
    }
}
