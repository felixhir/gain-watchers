package dhbw.entities;

import dhbw.helper.ExerciseVariant;
import dhbw.helper.ExerciseKey;
import dhbw.valueObjects.Name;

import javax.persistence.*;

@Entity
@IdClass(ExerciseKey.class)
public class Exercise {

    private Name name;
    @Id
    private ExerciseVariant variant;
    @Id
    private String nameValue;

    public Exercise(Name name, ExerciseVariant variant) {
        this.name = name;
        this.variant = variant;
        this.nameValue = name.getName();
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
