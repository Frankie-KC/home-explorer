import java.util.ArrayList;
/**
 *  This class is the central class of the "World of Home" application. 
 *  "World of Home" is a very simple, text based travel game.  Users 
 *  can walk around some house. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 4/2/2019
 */

public class Game 
{
    private Room currentRoom;
    private boolean finished;
    private int timelimit = 0;
    private ArrayList<Item> items;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        finished = false;
        createRooms();
        items = new ArrayList<Item>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room front, hall, kitchen, livingroom, garden, cloakroom, stairs, bathroom, 
        master, ensuite, guest;

        // create the rooms
        front = new Room("in front of the house");
        hall = new Room("in the hallway");
        kitchen = new Room("in the kitchen");
        livingroom = new Room("in the livingroom");
        garden = new Room("in the garden");
        cloakroom = new Room("in the cloakroom");
        stairs = new Room("at the top of the stairs");
        bathroom = new Room("in the bathroom");
        master = new Room("in the master bedroom");
        ensuite = new Room("in the ensuite");
        guest = new Room("in the guest bedroom");

        // initialise room exits
        front.setExit(Direction.NORTH, hall);
        hall.setExit(Direction.SOUTH, front);
        hall.setExit(Direction.UP, stairs);
        hall.setExit(Direction.WEST, cloakroom);
        hall.setExit(Direction.EAST, kitchen);
        hall.setExit(Direction.NORTH, livingroom);
        kitchen.setExit(Direction.WEST, hall);
        kitchen.setExit(Direction.NORTH, livingroom);
        cloakroom.setExit(Direction.EAST, hall);
        livingroom.setExit(Direction.SOUTH, hall);
        livingroom.setExit(Direction.EAST, kitchen);
        livingroom.setExit(Direction.NORTH, garden);
        garden.setExit(Direction.SOUTH, livingroom);
        stairs.setExit(Direction.DOWN, hall);
        stairs.setExit(Direction.EAST, bathroom);
        stairs.setExit(Direction.SOUTH, guest);
        stairs.setExit(Direction.NORTH, master);
        bathroom.setExit(Direction.WEST, stairs);
        guest.setExit(Direction.NORTH, stairs);
        master.setExit(Direction.SOUTH, stairs);
        master.setExit(Direction.EAST, ensuite);
        ensuite.setExit(Direction.WEST, master);
        
        Character mother, father, daughter, son;
        
        mother =  new Character("mother", Item.FLOUR);
        father =  new Character("father", Item.EGG);
        daughter =  new Character("daughter", Item.SUGAR);
        son =  new Character("son", null);
        
        kitchen.addCharacter(mother);
        garden.addCharacter(son);
        livingroom.addCharacter(father);
        bathroom.addCharacter(daughter);
        
        currentRoom = front;  // start game at the front of the house
    }
    
    /**
     * Return the current room.
     * Post-condition: not null.
     */
    public Room getCurrent()
    {
        assert currentRoom != null : "Current room is null.";
        return currentRoom;
    }

    /**
     * Return whether the game has finished or not.
     */
    public boolean finished()
    {
        return finished;
    }

    /**
     * Opening message for the player.
     */
    public String welcome()
    {
        return "\nWelcome to the World of Home!\n" +
        "World of Home is a new game.\n" +
        currentRoom.getLongDescription() + "\n";
    }

    // implementations of user commands:
    /**
     * Give some help information.
     */
    public String help() 
    {
        return "You are lost. You are alone. You wander around the home.\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room and return its long description; check if time limit has run out
     * or ensuite has been reached, if not then return an error message.
     * @param direction The direction in which to go.
     * Pre-condition: direction is not null.
     */
    public String goRoom(Direction direction) 
    {
        assert direction != null : "Game.goRoom gets null direction";
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no exit in that direction!";
        }
        else if (nextRoom.getShortDescription() == "in the ensuite") {
            finished = true;
            return currentRoom.getLongDescription() +"\nCongratulations! You reached the goal.\nThank you for playing.  Good bye.";
        }
        else {
            timelimit += 1;
            if (timelimit == 12) {
                finished = true;
                return currentRoom.getLongDescription() +"\nLost! You ran out of time.\nThank you for playing.  Good bye.";
            }
            currentRoom = nextRoom;
            return currentRoom.getLongDescription();
        }
    }


    /**
     * Execute quit command.
     */
    public String quit()
    {
        finished = true;
        return "Thank you for playing.  Good bye.";
    }

    /**
     * Execute look command.
     */
    public String look()
    {
        return currentRoom.getLongDescription(); // TO DO
    }

    /**
     * Execute take command.
     * @param item The item to take.
     * Pre-condition: item is not null.
     */
    public String take(Item item)
    {
        assert item != null : "Game.take item is null";
        if(currentRoom.take(item) == true) {
            items.add(item);
            return "Item taken.";
        }
        return "Item not in this room.";
    }

    /**
     * Execute cook command.
     * @return game winning message or failed to cook as appropriate
     */
    public String cook()
    {
        if ((currentRoom.getShortDescription() == "in the kitchen") && items.contains(Item.EGG) && items.contains(Item.FLOUR) && items.contains(Item.SUGAR)) {
            return "Congratulations! You have won.\nThank you for playing.  Good bye.";
        }
        return "You cannot cook yet.";
    }
}
