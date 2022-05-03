package dhbw.valueObjects;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class DaysPerWeek {

    private final int availability;
    private static final int MIN_DAYS = 0;
    private static final int MAX_DAYS = 7;

    public DaysPerWeek(int days) {
        if (days < MIN_DAYS || days > MAX_DAYS) {
            throw new IllegalArgumentException("A week cannot have negative or more than 7 days");
        }
        this.availability = days;
    }

    public DaysPerWeek() {
        // JPA default
        availability = 0;
    }

    public int getValue() {
        return availability;
    }

    private void setValue(int _) {
        // empty method for JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaysPerWeek that = (DaysPerWeek) o;
        return availability == that.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(availability);
    }
}
