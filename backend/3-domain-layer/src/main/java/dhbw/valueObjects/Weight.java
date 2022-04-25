package dhbw.valueObjects;

import javax.persistence.Embeddable;

import static java.util.Objects.hash;

@Embeddable
public final class Weight {

    private final double weight;
    private final boolean useMetricSystem;

    public Weight(double weight, boolean useMetricSystem) {
        if (!isValidWeight(weight)) {
            throw new IllegalArgumentException("A weight must not be a negative number!");
        }
        this.weight = weight;
        this.useMetricSystem = useMetricSystem;
    }

    public Weight() {
        // default
        useMetricSystem = false;
        weight = 0;
    }

    private boolean isValidWeight(double weight) {
        return weight >= 0;
    }

    public double getWeightInKg() {
        if (this.useMetricSystem) {
            return this.weight;
        }
        return this.weight * 0.45;
    }

    public double getWeightInLbs() {
        if (!this.useMetricSystem) {
            return this.weight;
        }
        return this.weight / 0.45;
    }

    private void setWeightInKg(double _) {
        // empty method for hibernate
    }

    private void setWeightInLbs(double _) {
        // empty method for hibernate
    }

    public boolean usesMetricSystem() {
        return useMetricSystem;
    }

    public Weight increaseWeight(double inc) {
        return new Weight(this.weight + inc, this.useMetricSystem);
    }

    public Weight decreaseWeight(double dec) {
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
