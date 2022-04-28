package dhbw.entities;

import dhbw.helper.ExerciseVariant;
import dhbw.helper.ExerciseKey;
import dhbw.valueObjects.Name;

import javax.persistence.*;

@Entity
@IdClass(ExerciseKey.class)
public class Exercise {

    @Id
    private Name name;
    @Id
    private ExerciseVariant variant;

    public Exercise(String name, ExerciseVariant variant) {
        this.name = new Name(name);
        this.variant = variant;
    }

    public Exercise() {
        // default
    }

    public String getName() {
        return name.getName();
    }

    public ExerciseVariant getVariant() {
        return variant;
    }
}
