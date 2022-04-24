import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    Font boringFont = new Font ("Times New Roman", false, false, 14);
    //different options for selections
    //change as you see fit
    
    private Integer cherryOptOne = new Integer(5);
    private Integer cherryOptTwo = new Integer(10);
    private Integer cherryOptThree = new Integer(15);
    private Integer poisonIvyOptOne = new Integer(5);
    private Integer poisonIvyOptTwo = new Integer(10);
    private Integer poisonIvyOptThree = new Integer(15);
    private Integer herbOptOne = new Integer(5);
    private Integer herbOptTwo = new Integer(10);
    private Integer herbOptThree = new Integer(15);
    private Integer carnOptOne = new Integer(5);
    private Integer carnOptTwo = new Integer(10);
    private Integer carnOptThree = new Integer(15);
    private Integer shelterOptOne = new Integer(5);
    private Integer shelterOptTwo = new Integer(10);
    private Integer shelterOptThree = new Integer(15);
    private int mx = 0;
    private int my = 0;
    private int cherryNum = 0;
    private int poisonIvyNum = 0;
    private int herbNum = 0;
    private int carnNum = 0;
    private int shelterNum = 0;
    ArrayList<SuperTextBox> cherryTextBoxes;
    ArrayList<SuperTextBox> poisonIvyTextBoxes;
    ArrayList<SuperTextBox> carnTextBoxes;
    ArrayList<SuperTextBox> herbTextBoxes;
    ArrayList<SuperTextBox> shelterTextBoxes;
    Rectangle cherrySelected;
    Rectangle poisonIvySelected;
    Rectangle carnSelected;
    Rectangle herbSelected;
    Rectangle shelterSelected;
    GreenfootImage background = new GreenfootImage("TitleBackground.png");
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        setBackground(background);
        prepare();
        
    }
    
    /**
     * The main world act loop
     */
    public void act()
    {
        checkForSelection();
        showSelection();
        //starts game if user presses <space>
        if(Greenfoot.isKeyDown("space"))
        {
            for(SuperTextBox cherryLabels : cherryTextBoxes)
            {
                if(cherryLabels.checkSelected())
                {
                    cherryNum = cherryLabels.getValue();
                }
            }
            for(SuperTextBox poisonIvyLabels : poisonIvyTextBoxes)
            {
                if(poisonIvyLabels.checkSelected())
                {
                    poisonIvyNum = poisonIvyLabels.getValue();
                }
            }
            for(SuperTextBox herbLabels : herbTextBoxes)
            {
                if(herbLabels.checkSelected())
                {
                    herbNum = herbLabels.getValue();
                }
            }
            for(SuperTextBox carnLabels : carnTextBoxes)
            {
                if(carnLabels.checkSelected())
                {
                    carnNum = carnLabels.getValue();
                }
            }
            for(SuperTextBox shelterLabels : shelterTextBoxes)
            {
                if(shelterLabels.checkSelected())
                {
                    shelterNum = shelterLabels.getValue();
                }
            }
            MainWorld mainworld = new MainWorld(cherryNum, poisonIvyNum, herbNum, carnNum, shelterNum);
            Greenfoot.setWorld(mainworld);
        }
        
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
        //labels for customization options
        label2.setFillColor(Color.BLACK);
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
        SuperTextBox cherryButtonOne = new SuperTextBox(cherryOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonOne, getWidth()/6, getHeight()*2/5);
        cherryButtonOne.setIsSelected(true);
        cherryButtonOne.setValue(cherryOptOne);
        SuperTextBox cherryButtonTwo = new SuperTextBox(cherryOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonTwo, getWidth()/6, getHeight()*3/5);
        cherryButtonTwo.setValue(cherryOptTwo);
        SuperTextBox cherryButtonThree = new SuperTextBox(cherryOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(cherryButtonThree, getWidth()/6, getHeight()*4/5);
        cherryButtonThree.setValue(cherryOptThree);
        cherryTextBoxes = new ArrayList<SuperTextBox>();
        cherryTextBoxes.add(cherryButtonOne);
        cherryTextBoxes.add(cherryButtonTwo);
        cherryTextBoxes.add(cherryButtonThree);
        cherrySelected = new Rectangle(cherryButtonOne.getWidth(),cherryButtonOne.getHeight(), 128);
        addObject(cherrySelected,cherryButtonOne.getX(),cherryButtonOne.getY());
        
        //poison ivy buttons
        SuperTextBox poisonIvyButtonOne = new SuperTextBox(poisonIvyOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonOne, getWidth()*2/6, getHeight()*2/5);
        poisonIvyButtonOne.setIsSelected(true);
        poisonIvyButtonOne.setValue(poisonIvyOptOne);
        SuperTextBox poisonIvyButtonTwo = new SuperTextBox(poisonIvyOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonTwo, getWidth()*2/6, getHeight()*3/5);
        poisonIvyButtonTwo.setValue(poisonIvyOptTwo);
        SuperTextBox poisonIvyButtonThree = new SuperTextBox(poisonIvyOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(poisonIvyButtonThree, getWidth()*2/6, getHeight()*4/5);
        poisonIvyButtonThree.setValue(poisonIvyOptThree);
        poisonIvyTextBoxes = new ArrayList<SuperTextBox>();
        poisonIvyTextBoxes.add(poisonIvyButtonOne);
        poisonIvyTextBoxes.add(poisonIvyButtonTwo);
        poisonIvyTextBoxes.add(poisonIvyButtonThree);
        poisonIvySelected = new Rectangle(poisonIvyButtonOne.getWidth(),poisonIvyButtonOne.getHeight(), 128);
        addObject(poisonIvySelected,poisonIvyButtonOne.getX(),poisonIvyButtonOne.getY());
        
        //herbivore buttons
        SuperTextBox herbButtonOne = new SuperTextBox(herbOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonOne, getWidth()*3/6, getHeight()*2/5);
        herbButtonOne.setIsSelected(true);
        herbButtonOne.setValue(herbOptOne);
        SuperTextBox herbButtonTwo = new SuperTextBox(herbOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonTwo, getWidth()*3/6, getHeight()*3/5);
        herbButtonTwo.setValue(herbOptTwo);
        SuperTextBox herbButtonThree = new SuperTextBox(herbOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(herbButtonThree, getWidth()*3/6, getHeight()*4/5);
        herbButtonThree.setValue(herbOptThree);
        herbTextBoxes = new ArrayList<SuperTextBox>();
        herbTextBoxes.add(herbButtonOne);
        herbTextBoxes.add(herbButtonTwo);
        herbTextBoxes.add(herbButtonThree);
        herbSelected = new Rectangle(herbButtonOne.getWidth(),herbButtonOne.getHeight(), 128);
        addObject(herbSelected,herbButtonOne.getX(),herbButtonOne.getY());
        
        //carnivore buttons
        SuperTextBox carnButtonOne = new SuperTextBox(carnOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonOne, getWidth()*4/6, getHeight()*2/5);
        carnButtonOne.setIsSelected(true);
        carnButtonOne.setValue(carnOptOne);
        SuperTextBox carnButtonTwo = new SuperTextBox(carnOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonTwo, getWidth()*4/6, getHeight()*3/5);
        carnButtonTwo.setValue(carnOptTwo);
        SuperTextBox carnButtonThree = new SuperTextBox(carnOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(carnButtonThree, getWidth()*4/6, getHeight()*4/5);
        carnButtonThree.setValue(carnOptThree);
        carnTextBoxes = new ArrayList<SuperTextBox>();
        carnTextBoxes.add(carnButtonOne);
        carnTextBoxes.add(carnButtonTwo);
        carnTextBoxes.add(carnButtonThree);
        carnSelected = new Rectangle(carnButtonOne.getWidth(),carnButtonOne.getHeight(), 128);
        addObject(carnSelected,carnButtonOne.getX(),carnButtonOne.getY());
        
        //shelter buttons
        SuperTextBox shelterButtonOne = new SuperTextBox(shelterOptOne.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonOne, getWidth()*5/6, getHeight()*2/5);
        shelterButtonOne.setIsSelected(true);
        shelterButtonOne.setValue(shelterOptOne);
        SuperTextBox shelterButtonTwo = new SuperTextBox(shelterOptTwo.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonTwo, getWidth()*5/6, getHeight()*3/5);
        shelterButtonTwo.setValue(shelterOptTwo);
        SuperTextBox shelterButtonThree = new SuperTextBox(shelterOptThree.toString(),Color.WHITE, Color.BLACK, boringFont,true,this.getWidth()/30,1,Color.BLACK); 
        addObject(shelterButtonThree, getWidth()*5/6, getHeight()*4/5);
        shelterButtonThree.setValue(shelterOptThree);
        shelterTextBoxes = new ArrayList<SuperTextBox>();
        shelterTextBoxes.add(shelterButtonOne);
        shelterTextBoxes.add(shelterButtonTwo);
        shelterTextBoxes.add(shelterButtonThree);
        shelterSelected = new Rectangle(shelterButtonOne.getWidth(),shelterButtonOne.getHeight(), 128);
        addObject(shelterSelected,shelterButtonOne.getX(),shelterButtonOne.getY());
    }
    
    public void checkForSelection()
    {
        ArrayList<SuperTextBox> allBoxes = (ArrayList<SuperTextBox>) getObjects(SuperTextBox.class);
        for(SuperTextBox here : allBoxes)
        {
            if(Greenfoot.mouseClicked(here))
            {
                MouseInfo mouse = Greenfoot.getMouseInfo();
                if(mouse!=null){
                   mx = mouse.getX();
                   my = mouse.getY();
                }
                Rectangle checker = new Rectangle(1,1,0);
                addObject(checker,mx,my);
                ArrayList<SuperTextBox> textBoxes = checker.getIntersectingTextBoxes();
                for(SuperTextBox box : textBoxes)
                {
                    for(SuperTextBox test : cherryTextBoxes)
                    {
                        if(test.equals(box))
                        {
                            for(SuperTextBox cherryLabels : cherryTextBoxes)
                            {
                                cherryLabels.setIsSelected(false);
                            }
                            box.setIsSelected(true);
                        }
                    }
                    for(SuperTextBox test : poisonIvyTextBoxes)
                    {
                        if(test.equals(box))
                        {
                            for(SuperTextBox poisonIvyLabels : poisonIvyTextBoxes)
                            {
                                poisonIvyLabels.setIsSelected(false);
                            }
                            box.setIsSelected(true);
                        }
                    }
                    for(SuperTextBox test : herbTextBoxes)
                    {
                        if(test.equals(box))
                        {
                            for(SuperTextBox herbLabels : herbTextBoxes)
                            {
                                herbLabels.setIsSelected(false);
                            }
                            box.setIsSelected(true);
                        }
                    }
                    for(SuperTextBox test : carnTextBoxes)
                    {
                        if(test.equals(box))
                        {
                            for(SuperTextBox carnLabels : carnTextBoxes)
                            {
                                carnLabels.setIsSelected(false);
                            }
                            box.setIsSelected(true);
                        }
                    }
                    for(SuperTextBox test : shelterTextBoxes)
                    {
                        if(test.equals(box))
                        {
                            for(SuperTextBox shelterLabels : shelterTextBoxes)
                            {
                                shelterLabels.setIsSelected(false);
                            }
                            box.setIsSelected(true);
                        }
                    }
                }
                removeObject(checker);
            }
        }
    }
    
    public void showSelection()
    {
        ArrayList<SuperTextBox> allBoxes = (ArrayList<SuperTextBox>) getObjects(SuperTextBox.class);
        for(SuperTextBox textBox : allBoxes)
        {
            if(textBox.checkSelected())
            {
                for(SuperTextBox yes : cherryTextBoxes)
                {
                    if(yes.equals(textBox))
                    {
                        cherrySelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : poisonIvyTextBoxes)
                {
                    if(yes.equals(textBox))
                    {
                        poisonIvySelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : herbTextBoxes)
                {
                    if(yes.equals(textBox))
                    {
                        herbSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : carnTextBoxes)
                {
                    if(yes.equals(textBox))
                    {
                        carnSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
                for(SuperTextBox yes : shelterTextBoxes)
                {
                    if(yes.equals(textBox))
                    {
                        shelterSelected.setLocation(yes.getX(),yes.getY());
                    }
                }
            }
        }
    }
}
