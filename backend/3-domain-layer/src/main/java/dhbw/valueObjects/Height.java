package dhbw.valueObjects;

import javax.persistence.Embeddable;

import static java.util.Objects.hash;

@Embeddable
public final class Height {

    private final int height;
    private static final int MIN_HEIGHT = 50;
    private static final int MAX_HEIGHT = 260;

    public Height(int height) {
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new IllegalArgumentException(String.format("A persons height must lie within a reasonable range (%dcm-%dcm)", MIN_HEIGHT, MAX_HEIGHT));
        }
        this.height = height;
    }

    public Height() {
        // JPA default
        height = 0;
    }

    public int getHeightInCm() {
        return height;
    }

    private void setHeightInCm(int height) {
        // empty method for JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height1 = (Height) o;
        return height == height1.height;
    }

    @Override
    public int hashCode() {
        return hash(height);
    }
}
