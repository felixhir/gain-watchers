package dhbw.entities;

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
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
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
