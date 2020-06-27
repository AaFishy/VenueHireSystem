/* TEST FOR CHECKING VENUES AND ROOMS
    for (int i = 0; i < venues.size(); i++) {
        Venue currVenue = venues.get(i);
        System.out.println("Venue = " + currVenue.getName());
        System.out.println("        numSmall = " + currVenue.getNumSmall());
        System.out.println("        numMed = " + currVenue.getNumMed());
        System.out.println("        numLarge = " + currVenue.getNumLarge());
        for (int j = 0; j < currVenue.getRooms().size(); j++) {
            Room currRoom = currVenue.getRooms().get(j);
            System.out.println("    Room = " + currRoom.getName());
            System.out.println("        Size = " + currRoom.getSize());
        }
    }*/


/* TODO
        Fix change function
*/
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
    protected ArrayList<Venue> venues;

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
            if (venue.equals(venues.get(i).getName())) {
                foundVenue = venues.get(i);
                break;
            }
        }
        // Case if there is no venue with same name... Create new venue
        if (foundVenue == null) {
            foundVenue = new Venue(venue);
            venues.add(foundVenue);
        }
        Room newRoom = new Room(room, size);
        foundVenue.addRoom(newRoom);
        foundVenue.addSize(size);
    }

    public void cancelRoom(String id) {
        ArrayList<Room> rooms = findRoomsId(id);
        if (rooms == null) return;
        for (int i = 0; i < rooms.size(); i++) {
            Room currRoom = rooms.get(i);
            currRoom.removeReservation(id);
        }
    }

    public JSONArray list(String venueName) {
        JSONArray arrayResults = new JSONArray();
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = venues.get(i);
            if (!venue.getName().equals(venueName)) continue;
            // Getting list of rooms
            ArrayList<Room> rooms = venue.getRooms();
            for (int j = 0; j < rooms.size(); j++) {
                JSONObject result = new JSONObject();
                Room currRoom = rooms.get(j);
                result.put("room", currRoom.getName());
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

    public ArrayList<Room> findRoomsId(String id) {
        ArrayList<Room> foundRooms = new ArrayList<Room>();
        for (int i = 0; i < venues.size(); i++) {
            Venue currVenue = venues.get(i);
            ArrayList<Room> rooms = currVenue.getRooms();

            for (int j = 0; j < rooms.size(); j++) {
                Room currRoom = rooms.get(j);
                Reservation currRes = currRoom.getResId(id);
                if (currRes != null) {
                    if (currRes.getId().equals(id)) foundRooms.add(currRoom);
                }
                
            }
        }
        return null;
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();

        ArrayList<Room> goodRooms = new ArrayList<Room>();
        String status = "rejected";
        String venue = "";
        ArrayList<Venue> oldVenues = venues;
        // TODO Process the request commmand
        ArrayList<Room> foundRooms = findRoomsId(id);
        // If there already exists the room - change command
        if (foundRooms != null) {
            cancelRoom(id);
            /*for (int i = 0; i < venues.size(); i++) {
                ArrayList<Room> rooms = venues.get(i).getRooms();
                for (int j = 0; j < rooms.size(); j++) {
                    Room currRoom = rooms.get(j);
                    currRoom.removeReservation(id);
                    //System.out.println("removed :)");
                    removedRoom = true;
                }
                if (removedRoom) break;
            }*/
            
        }
    
        for (int i = 0; i < venues.size(); i++) {
            Venue currVenue = venues.get(i);
            boolean foundReservation = true;
            /*System.out.println("Venue = " + currVenue.getName());
            System.out.println("        numSmall = " + currVenue.getNumSmall());
            System.out.println("        numMed = " + currVenue.getNumMed());
            System.out.println("        numLarge = " + currVenue.getNumLarge());*/
            // The venue has enough rooms to accomodate
            if (currVenue.getNumSmall() >= small && currVenue.getNumMed() >= medium
                    && currVenue.getNumLarge() >= large) {
                int smallCount = 0;
                int mediumCount = 0;
                int largeCount = 0;
                ArrayList<Room> rooms = currVenue.getRooms();
                for (int j = 0; j < rooms.size(); j++) {
                    Room currRoom = rooms.get(j);
                    //System.out.println("    Room = " + currRoom.getName());
                    // Determining if the reservation is clashing with another
                    ArrayList<Reservation> currReservations = currRoom.getReservations();
                    if (currReservations.size() == 0) foundReservation = true;
                    else {
                        for (int k = 0; k < currReservations.size(); k++) {
                            Reservation currRes = currReservations.get(k);
                            /*System.out.println("        Res ID = " + currRes.getId());
                            System.out.println("        Res start = " + currRes.getStart().toString());
                            System.out.println("        Res end = " + currRes.getEnd().toString());*/
                            // Check if there already exists a reservation - change it
                            /*System.out.println("curr start: " + currRes.getStart().toString());
                            System.out.println("new start: " + start.toString());
                            System.out.println("curr end: " + currRes.getEnd().toString());
                            System.out.println("new end: " + end.toString());*/
                            if ((end.isAfter(currRes.getStart()) && end.isBefore(currRes.getEnd())) ||
                                (start.isAfter(currRes.getStart()) && start.isBefore(currRes.getEnd())) ||
                                (start.isBefore(currRes.getStart()) && end.isAfter(currRes.getEnd())) ||
                                start.equals(currRes.getStart()) || end.equals(currRes.getEnd())) {
                                foundReservation = false;
                                //System.out.println("        " + currRes.getId() + "is clashing");
                            }
                        }
                    }
                    // Are there any reservations left
                    if (foundReservation) {   
                        //System.out.println("Found a reservation");
                        // Determining room size and how many rooms to add
                        boolean hasAdded = false;
                        /*System.out.println("        numSmall = " + smallCount);
                        System.out.println("        numMed = " + mediumCount);
                        System.out.println("        numLarge = " + largeCount);
                        System.out.println("Size of room = " + currRoom.getSize());*/
                        if (currRoom.getSize().equals("small") && smallCount < small) {
                            goodRooms.add(currRoom);
                            currRoom.addReservation(new Reservation(id, start, end));
                            smallCount++;
                            hasAdded = true;
                        } else if (currRoom.getSize().equals("medium") && mediumCount < medium) {
                            goodRooms.add(currRoom);
                            currRoom.addReservation(new Reservation(id, start, end));
                            mediumCount++;
                            hasAdded = true;
                        } else if (currRoom.getSize().equals("large") && largeCount < large) {
                            goodRooms.add(currRoom);
                            currRoom.addReservation(new Reservation(id, start, end));
                            largeCount++;
                            hasAdded = true;
                        }
                        if (hasAdded){
                           status = "success";
                            venue = currVenue.getName(); 
                        }
                        
                    }
                }
            }
            // Ensures only one venue is used
            if (foundReservation) break;
        }

        // FIXME Shouldn't always produce the same answer
        result.put("status", status);
        if (status == "success") {
            result.put("venue", venue);
            JSONArray rooms = new JSONArray();
            for (int i = 0; i < goodRooms.size(); i++) {
                rooms.put(goodRooms.get(i).getName());
            }

            result.put("rooms", rooms);
        // If fail, then reset
        } else venues = oldVenues;

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
