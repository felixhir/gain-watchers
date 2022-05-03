package dhbw.valueObjects;

import dhbw.helper.ExerciseKey;
import dhbw.helper.ExerciseVariant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ExerciseKey.class)
public final class Exercise {

    private final Name name;
    @Id
    private final ExerciseVariant variant;
    @Id
    private final String nameValue; // this value is needed to utilize a composite key (@EmbeddedId)

    public Exercise(Name name, ExerciseVariant variant) {
        this.name = name;
        this.variant = variant;
        this.nameValue = name.getName();
    }

    public Exercise() {
        // default for JPA
        this.name = null;
        this.variant = null;
        this.nameValue = null;
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
        return Objects.equals(name, exercise.name) && variant == exercise.variant && Objects.equals(nameValue, exercise.nameValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, variant, nameValue);
    }
}
