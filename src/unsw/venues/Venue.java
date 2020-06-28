package unsw.venues;

import java.util.ArrayList;
import unsw.venues.Room;

/**
 * Venue Class stores the information needed in a venue
 */
public class Venue {
    /**
     * @param Name Name of the Venue
     * @param numSmall Number of small rooms in the venue
     * @param numMed Number of medium rooms in the venue
     * @param numLarge Number of large rooms in the venue
     * @param rooms ArrayList of rooms in that venue
     */
    private String Name;
    private int numSmall;
    private int numMed;
    private int numLarge;
    private ArrayList<Room> rooms;

    /**
     * Creates venue with name "Name" and 0 rooms of each size with a new rooms array
     */
    public Venue (String Name) {
        this.Name = Name;
        this.numSmall = 0;
        this.numMed = 0;
        this.numLarge = 0;
        this.rooms = new ArrayList<Room>();
    }

    /**
     * Returns the venue name
     * @return String Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Get the list of rooms
     * @return ArrayList of Rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the number of small rooms
     * @return Int numSmall
     */
    public int getNumSmall() {
        return numSmall;
    }

    /**
     * Gets the number of medium rooms
     * @return Int numMed
     */
    public int getNumMed() {
        return numMed;
    }

    /**
     * Gets the number of large rooms
     * @return Int numLarge
     */
    public int getNumLarge() {
        return numLarge;
    }

    /**
     * Adds a new room to venue
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Increments the numSizes given what the String size is
     * @param size String dictating size of room
     */
    public void addSize(String size) {
        switch (size) {
            case "small":
                numSmall++;
                break;
            case "medium":
                numMed++;
                break;
            case "large":
                numLarge++;
                break;
            default:
                break;
        }
    }
}