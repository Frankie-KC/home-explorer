
/**
 * Enumeration class Character
 * A character in the game.
 * 
 * @author Olaf Chitil
 * @version 4/2/2019
 */
public class Character
{
    private String description;
    private Item item = null;
    /**
     * Constructor initialising description and item.
     * @param desc, the description of the character
     * @param it, the item the character posesses
     */
    public Character(String desc, Item it)
    {
        this.description = desc;
        this.item = it;
    }

    /**
     * Return the description and description of item if it exists.
     */
    public String toString()
    {
        if (item != null) {
            return description + " having the item "+ item.toString();
        }
        else {
            return description;
        }
    }

    /**
     * Take the given item from the character if it has that item.
     * Return whether item was taken.
     * @param it, the item the user wants to try to take
     */
    public boolean take(Item it)
    {
        if (item == it) {
            this.item = null;
            return true;
        }
        return false;
    }
}
