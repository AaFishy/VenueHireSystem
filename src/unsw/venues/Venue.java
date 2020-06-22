package unsw.venues;

import java.util.ArrayList;
import unsw.venues.Room;

public class Venue {
    private String Name;
    private int numSmall;
    private int numMed;
    private int numLarge;
    private ArrayList<Room> rooms;

    public Venue (String Name) {
        this.Name = Name;
        this.numSmall = 0;
        this.numMed = 0;
        this.numLarge = 0;
        this.rooms = new ArrayList<Room>();
    }

    public String getName() {
        return Name;
    }

    public void addNewRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public int getNumSmall() {
        return numSmall;
    }

    public int getNumMed() {
        return numMed;
    }

    public int getNumLarge() {
        return numLarge;
    }

    public void setSize(String size) {
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