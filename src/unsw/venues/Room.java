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
        reservations = new ArrayList<Reservation>();
    }

    public String getName() {
        return Name;
    }

    public String getSize() {
        return Size;
    }

    public void addReservation(Reservation reservation) {
        /*boolean hasAdded = false;
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (reservation.getStart().isBefore(currRes.getStart())) {
                reservations.add(i, reservation);
                hasAdded = true;
            }
        }
        if (!hasAdded) reservations.add(reservation);*/
        reservations.add(reservation);
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public Reservation getResId(String id) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (currRes.getId().equals(id))
                return currRes;
        }
        return null;
    }

    public void removeReservation(String id) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (currRes.getId().equals(id))
                reservations.remove(currRes);
        }
    }
}