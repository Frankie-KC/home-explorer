
/**
 * Enumeration class Item
 * The items in the game.
 * 
 * @author Olaf Chitil
 * @version 4/2/2019
 */
public enum Item
{
    FLOUR("flour"), 

    SUGAR("sugar"), 

    EGG("egg");

    private String name;
    
    /**
     * initialise the names of the items
     * @param name of the item
     */
    private Item(String name)
    {
        this.name = name;
    }
    
    /**
     * Return the description of the item.
     */
    public String toString()
    {
        return name;
    }
}
