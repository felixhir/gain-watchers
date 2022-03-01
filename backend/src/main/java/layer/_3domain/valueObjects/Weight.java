package layer._3domain.valueObjects;

import static java.util.Objects.hash;

public final class Weight {

    private final float weight;
    private final boolean useMetricSystem;

    public Weight(float weight, boolean useMetricSystem) {
        if (!isValidWeight(weight)) {
            throw new IllegalArgumentException("A weight must not be a negative number!");
        }
        this.weight = weight;
        this.useMetricSystem = useMetricSystem;
    }

    private boolean isValidWeight(float weight) {
        return weight >= 0;
    }

    public Weight increaseWeight(float inc) {
        return new Weight(this.weight + inc, this.useMetricSystem);
    }

    public Weight decreaseWeight(float dec) {
        return new Weight(this.weight + dec, this.useMetricSystem);
    }

    @Override
    public boolean equals(Object comparisonObject) {
        if (comparisonObject instanceof Weight) {
            Weight weightObject = (Weight) comparisonObject;
            return this.weight == weightObject.weight && this.useMetricSystem == weightObject.useMetricSystem;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(weight, useMetricSystem);
    }
}
