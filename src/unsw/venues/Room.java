package unsw.venues;

import java.util.ArrayList;
import unsw.venues.Reservation;

public class Room {
    private String Name;
    private String Size;
    private ArrayList<Reservation> reservations;

    public Room(String Name, String Size) {
        this.Name = Name;
        this.Size = Size;
    }

    public String getName() {
        return Name;
    }

    public String getSize() {
        return Size;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}