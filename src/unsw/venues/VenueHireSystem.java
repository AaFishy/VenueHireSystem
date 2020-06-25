/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.venues.*;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    private ArrayList<Venue> venues;

    public VenueHireSystem() {
        // TODO Auto-generated constructor stub
        this.venues = new ArrayList<Venue>();
    }

    private void processCommand(JSONObject json) {
        // Common variables for "request" and "change"
        String id;
        LocalDate start;
        LocalDate end;
        int small;
        int medium;
        int large;
        JSONObject result;

        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            id = json.getString("id");
            start = LocalDate.parse(json.getString("start"));
            end = LocalDate.parse(json.getString("end"));
            small = json.getInt("small");
            medium = json.getInt("medium");
            large = json.getInt("large");

            result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        // TODO Implement other commands
        case "change":
            id = json.getString("id");
            start = LocalDate.parse(json.getString("start"));
            end = LocalDate.parse(json.getString("end"));
            small = json.getInt("small");
            medium = json.getInt("medium");
            large = json.getInt("large");

            result = request(id, start, end, small, medium, large);
            System.out.println(result.toString(2));
            break;

        case "cancel":
            id = json.getString("id");
            
            cancelRoom(id);
            break;

        case "list":
            venue = json.getString("venue");

            JSONArray resultArray = list(venue);
            System.out.println(resultArray.toString(2));
            break;
        }
    }

    public void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        // Is there a Venue with the same name already
        Venue foundVenue = null;
        for (int i = 0; i < venues.size(); i++) {
            // Found venue with same name
            if (venue == venues.get(i).getName()) {
                foundVenue = venues.get(i);
                break;
            }
        }
        // Case if there is no venue with same name... Create new venue
        if (foundVenue == null) {
            foundVenue = new Venue(venue);
            venues.add(foundVenue);
        }
        ArrayList<Room> rooms = foundVenue.getRooms();
        Room newRoom = new Room(room, size);
        rooms.add(newRoom);

        foundVenue.setSize(size);
    }

    public void cancelRoom(String id) {
        Room currRoom = findRoomId(id);
        ArrayList<Reservation> reservations = currRoom.getReservations();
        for (int i = 0; i < reservations.size(); i++) {
            Reservation currRes = reservations.get(i);
            if (currRes.getId() == id) reservations.remove(currRes);
        }
    }

    public JSONArray list (String venueName) {
        JSONArray arrayResults = new JSONArray();
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = venues.get(i);
            if (venue.getName() != venueName) continue;
            // Getting list of rooms
            ArrayList<Room> rooms = venue.getRooms();
            for (int j = 0; j < rooms.size(); j++) {
                JSONObject result = new JSONObject();
                Room currRoom = rooms.get(j);
                result.put("room", currRoom);
                // Getting list of reservations
                ArrayList<Reservation> currReservations = currRoom.getReservations();
                JSONArray reservationArray = new JSONArray();
                for (int k = 0; k < currReservations.size(); k++) {
                    JSONObject reserveObject = new JSONObject();
                    // Getting current reservation
                    Reservation currRes = currReservations.get(k);
                    reserveObject.put("id", currRes.getId());
                    reserveObject.put("start", currRes.getStart());
                    reserveObject.put("end", currRes.getEnd());

                    reservationArray.put(reserveObject);
                }
                result.put("reservations", reservationArray);
                arrayResults.put(result);
            }
        }
        return arrayResults;
    }

    private Room findRoomId(String id) {
        for (int i = 0; i < venues.size(); i++) {
            Venue currVenue = venues.get(i);
            ArrayList<Room> rooms = currVenue.getRooms();

            for (int j = 0; j < rooms.size(); j++) {
                Room currRoom = rooms.get(j);
                ArrayList<Reservation> currReservations = currRoom.getReservations();
                for (int k = 0; k < currReservations.size(); k++) {
                    Reservation currRes = currReservations.get(k);
                    if (currRes.getId() == id) return currRoom;
                }
            }
        }
        return null;
    }

    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();

        ArrayList<Room> goodRooms = new ArrayList<Room>();
        String status = "rejected";
        String venue = "";
        boolean foundReservation = false;
        boolean change = false;

        // TODO Process the request commmand
        Room room = findRoomId(id);
        // If there already exists the room - change command
        if (room != null) {
            ArrayList<Reservation> currReservations = room.getReservations();
            for (int i = 0; i < currReservations.size(); i++) {
                Reservation currRes = currReservations.get(i);
                // Check if there already exists a reservation - change it
                if (currRes.getId() == id) {
                    currReservations.remove(currRes);

                }
            }
        }

        for (int i = 0; i < venues.size(); i++) {
            Venue currVenue = venues.get(i);
            // The venue has enough rooms to accomodate
            if (currVenue.getNumSmall() >= small && currVenue.getNumMed() >= medium && currVenue.getNumLarge() >= large) {
                ArrayList<Room> rooms = currVenue.getRooms();
                // Getting the small rooms:
                int smallCount = 0;
                int mediumCount = 0;
                int largeCount = 0;
                for (int j = 0; j < rooms.size(); j++) {
                    Room currRoom = rooms.get(j);
                    // Determining if the reservation is clashing with another
                    ArrayList<Reservation> currReservations = currRoom.getReservations();
                    for (int k = 0; k < currReservations.size(); k++) {
                        Reservation currRes = currReservations.get(k);
                        // Check if there already exists a reservation - change it
                        if (end.isBefore(currRes.getStart()) || start.isAfter(currRes.getEnd())) {
                            foundReservation = true;
                        }
                    }
                    // Are there any reservations left
                    if (foundReservation) {
                        Reservation newRes = new Reservation(id, start, end);
                        currReservations.add(newRes);
                        // Determining room size and how many rooms to add
                        if (currRoom.getSize() == "small" && smallCount < small) {
                            goodRooms.add(currRoom);
                            smallCount++;
                        } else if (currRoom.getSize() == "medium" && smallCount < medium) {
                            goodRooms.add(currRoom);
                            mediumCount++;
                        } else if (currRoom.getSize() == "large" && smallCount < large) {
                            goodRooms.add(currRoom);
                            largeCount++;
                        }
                        status = "success";
                        venue = currVenue.getName();
                    }
                }
            }
        }

        // FIXME Shouldn't always produce the same answer
        result.put("status", status);
        if (status == "success") {
            result.put("venue", venue);
        }
        
        JSONArray rooms = new JSONArray();
        for (int i = 0; i < goodRooms.size(); i++) {
            rooms.put(goodRooms.get(i));
        }

        result.put("rooms", rooms);
        return result;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }
}
