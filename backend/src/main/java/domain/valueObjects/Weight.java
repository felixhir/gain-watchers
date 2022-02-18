package domain.valueObjects;

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

    @Override
    public boolean equals(Object comparisonObject) {
        if (comparisonObject instanceof Weight) {
            Weight weightObject = (Weight) comparisonObject;
            return this.weight == weightObject.weight && this.useMetricSystem == weightObject.useMetricSystem;
        }
        return false;
    }

    private boolean isValidWeight(float weight) {
        return weight >= 0;
    }
}
