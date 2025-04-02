import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits; //stores exits of this room
    private HashMap<Room, Item> items; //stores the items of this room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }
    
    //Exits:
        /**
     * Alternative set method for the exits. With this method, the exits of the 
     * room can be set one exit at a time, and any diretion can be used for an
     * exit.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Get method that access the information about the exits.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * @return a description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a description of the room's exits, for example "Exits: north west."
     * @return a description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }

    // Items:
    /**
     * Set the items of the room one at a time. 
     */
    public void addItems(Room room, Item item)
    {
        items.put(room, item);
    }
    
    
}
