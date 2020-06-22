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
            break;

        case "cancel":
            id = json.getString("id");
            

        }
    }

    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        // Is there a Venue with the same name already
        Venue foundVenue;
        for (int i = 0; i < venues.size(); i++) {
            // Found venue with same name
            if (venue ==venues.get(i).getName()) {
                foundVenue = venues.get(i);
                break;
            }
        }
        // Case if there is no venue with same name... Create new venue
        if (foundVenue == null) {
            foundVenue = new Venue(venue);
            venues.add(foundVenu);
        }

        Room newRoom = new Room(room, size);
        rooms.add(foundRoom);

        foundVenue.setSize(size);
        /* Finding room with same name
        ArrayList<Room> rooms = foundVenue.getRooms();
        for (int j = 0; j < rooms.size(); j++) {
            // Found room with same name
            Room currRoom = rooms.get(j);
            if (room == currRoom.getName()) {
                foundRoom = currRoom;
                break;
            }
        }*/
        // Case if there is no room with that name... Create new room
    }

    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();

        // TODO Take into account reservations
        // TODO Process the request commmand
        // Try find a room of right size/s
        ArrayList<Room> goodRooms;
        String status = "failure";
        for (int i = 0; i < venues.size(); i++) {
            Venue currVenue = venues.get(i);
            if (currVenue.getNumSmall() >= small && currVenue.getNumMed() >= medium && currVenue.getNumLarge() >= large) {
                ArrayList<Room> rooms = currVenue.getRooms();
                // Getting the small rooms:
                int smallCount = 0;
                int mediumCount = 0;
                int largeCount = 0;
                for (int j = 0; j < rooms.size(); j++) {
                    Room currRoom = rooms.get(j);
                    //Reservation currReservation = currRoom.getReservations
                    //if (end < currRoom.getReservations().getSt)
                    if (currRoom.getSize() == "small" && smallCount < small) {
                        goodRooms.add(currRoom);
                        smallCount++;
                    } else if (currRoom.getSize() == "medium" && smallCount < medium) {
                        goodrooms.add(currRoom);
                        mediumCount++;
                    } else if (currRoom.getSize() == "large" && smallCount < large) {
                        goodrooms.add(currRoom);
                        largeCount++;
                    }
                }
                status = "success";
            }
        }

        // FIXME Shouldn't always produce the same answer
        result.put("status", status);
        result.put("venue", venue);

        JSONArray rooms = new JSONArray();
        for (int i = 0; i < goodRooms.size(); i++) {
            rooms.add(goodRooms.get(i));
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
