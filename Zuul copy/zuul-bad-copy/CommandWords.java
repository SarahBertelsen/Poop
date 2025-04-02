/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat", "back"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }
    
    /**
     * Print all valid commands to System.out.
     */
    public void showAll()
    {
        for(String command : validCommands) {
            System.out.println(command + " ");
        }
        System.out.println();
    }
    
    /**
     * Returns a String containing all command words instead of printing them out.
    */
    public String getCommandList()
    {
        String commands = null;
        for(int i = 0; i < validCommands.length; i++) {
            commands = validCommands[i];
        }
        return commands;
    } //HJÆLP -> den her returner vel bare den sidste??
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
