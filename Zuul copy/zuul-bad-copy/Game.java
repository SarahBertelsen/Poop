/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Item item;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Creates the rooms and the items, that are placed in the rooms.
     */
    private void createRooms()
    {   
        Item lamp, map, pizza, book, potion, clue;
        
        lamp= new Item("There is a lamp in this room. It weighs grams ", 500);
        map = new Item("You spot a map in the room. It weighs grams ", 50);
        pizza = new Item("You smell fresh pizza. Are you hungry? Weight in g: ", 600);
        book = new Item("You spot an old book. It weighs grams ", 800);
        potion = new Item("A weird potion is sat on the desk. Do you dare? Weight in g: ", 250);
        clue = new Item("You spot a clue! It's a note that says: The potion gives you superpowers. Weight: ", 10);
        
        Room outside, theater, pub, lab, office, cellar;

        //create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");

        //initialise room exits
        outside.setExits("south", theater);
        outside.setExits("west", lab);
        outside.setExits("east", pub);
        outside.addItems(outside, map);

        theater.setExits("north", outside);
        theater.setExits("west", lab);
        theater.addItems(theater, lamp);

        pub.setExits("west", outside);
        pub.setExits("south", office);
        pub.addItems(pub, pizza);

        lab.setExits("east", outside);
        lab.setExits("south", theater);
        lab.addItems(lab, potion);

        office.setExits("west", theater);
        office.setExits("down", cellar);
        office.addItems(office, book);

        cellar.setExits("up", office);
        cellar.addItems(cellar, clue);

        currentRoom = outside; //start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        } 
        else if(commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("eat")){
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * A seperate printLocationInfo method. Prints the information of the location based on
     * which direction the player chooses to go.
     */
    private void printLocationInfo()
    {
        System.out.println("You are: " + currentRoom.getLongDescription());
    }

    /**
     * Prints the description of the room.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Implementing the eat command.
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry anymore.");
    }
    
    /**
     * Implementing the back command - takes the player back to the previous room.
     */
    private void back()
    {
        //TODO
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
