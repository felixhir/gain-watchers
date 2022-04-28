package dhbw.valueObjects;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class BodyFatPercentage {

    private final int bodyFat;
    private static final int MIN_FAT = 1;
    private static final int MAX_FAT = 100;

    public BodyFatPercentage(int bodyFat) {
        if (bodyFat < MIN_FAT || bodyFat > MAX_FAT) {
            throw new IllegalArgumentException("A persons body fat cannot be less than 1% or more than 100%");
        }
        this.bodyFat = bodyFat;
    }

    public BodyFatPercentage() {
        // JPA default
        bodyFat = 0;
    }

    public int getBodyFatInPercent() {
        return bodyFat;
    }

    private void setBodyFatInPercent(int bodyFat) {
        // empty method for JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyFatPercentage that = (BodyFatPercentage) o;
        return bodyFat == that.bodyFat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyFat);
    }
}
