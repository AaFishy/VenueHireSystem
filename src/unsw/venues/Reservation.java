package unsw.venues;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private LocalDate start;
    private LocalDate end;

    public Reservation (String id, LocalDate start, LocalDate end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}