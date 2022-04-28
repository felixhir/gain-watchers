package dhbw.valueObjects;

import dhbw.helper.ExerciseVariant;
import dhbw.helper.ExerciseKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(ExerciseKey.class)
public final class Exercise {

    @Id
    private final Name name;
    @Id
    private final ExerciseVariant variant;

    public Exercise(String name, ExerciseVariant variant) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
        this.name = new Name(name);
        this.variant = variant;
    }

    public Exercise() {
        variant = null;
        name = null;
        // default
    }

    public String getName() {
        return name.getName();
    }

    public ExerciseVariant getVariant() {
        return variant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && variant == exercise.variant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, variant);
    }
}
