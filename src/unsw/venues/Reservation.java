package unsw.venues;

import java.time.LocalDate;

/**
 * Room class stores necessary information for a room
 */
public class Reservation {
    /**
     * @param id id of reservation
     * @param start Start date for reservation
     * @param end End date for reservation
     */
    private String id;
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates reservation with: id, start, end
     */
    public Reservation (String id, LocalDate start, LocalDate end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    /**
     * Gets id of reservation
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets start date of reservation
     * @return Start date
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Gets end date of reservation
     * @return End date
     */
    public LocalDate getEnd() {
        return end;
    }
}