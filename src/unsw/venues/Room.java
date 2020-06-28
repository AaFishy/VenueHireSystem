package unsw.venues;

import java.util.ArrayList;

import unsw.venues.Reservation;

/**
 * Room class stores necessary information for a room
 */
public class Room {
    /**
     * @param Name Name of room
     * @param Size Size of room
     * @param reservation List of reservations
     */
    private String Name;
    private String Size;
    private ArrayList<Reservation> reservations;

    /**
     * Creates a room with name "Name" and size "Size", with a new reservations arraylist
     */
    public Room(String Name, String Size) {
        this.Name = Name;
        this.Size = Size;
        reservations = new ArrayList<Reservation>();
    }

    /**
     * Gets name of room
     * @return String name
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets size of room
     * @return String size
     */
    public String getSize() {
        return Size;
    }

    /**
     * Adds a reservation to reservations array
     * @param reservation The reservation to be added
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Gets list of reservations
     * @return resevations - List of reservations
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Gets reservation with id "id"
     * @param id Id of reservation we want to find
     * @return Reservation with id "id"
     */
    public Reservation getResId(String id) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (currRes.getId().equals(id))
                return currRes;
        }
        return null;
    }

    /**
     * Removes reservation with id "id"
     * @param id String id of reservation to remove
     */
    public void removeReservation(String id) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (currRes.getId().equals(id))
                reservations.remove(currRes);
        }
    }
}