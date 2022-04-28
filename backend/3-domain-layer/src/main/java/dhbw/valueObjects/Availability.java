package dhbw.valueObjects;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class Availability {

    private final int availability;
    private static final int MIN_DAYS = 0;
    private static final int MAX_DAYS = 7;

    public Availability(int availability) {
        if (availability < MIN_DAYS || availability > MAX_DAYS) {
            throw new IllegalArgumentException("A week cannot have negative or more than 7 days");
        }
        this.availability = availability;
    }

    public Availability() {
        // JPA default
        availability = 0;
    }

    public int getAvailability() {
        return availability;
    }

    private void setAvailability(int _) {
        // empty method for JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability that = (Availability) o;
        return availability == that.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(availability);
    }
}
