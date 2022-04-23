package dhbw.entities;

import dhbw.helper.ExerciseKey;

import javax.persistence.*;

@Entity
@IdClass(ExerciseKey.class)
public class Exercise {

    @Id
    private String name;
    private ExerciseType type;
    @Id
    private ExerciseVariant variant;

    public Exercise(String name, ExerciseType type, ExerciseVariant variant) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
        this.name = name;
        this.type = type;
        this.variant = variant;
    }

    public Exercise() {
        // default
    }

    public String getName() {
        return name;
    }

    public ExerciseType getType() {
        return type;
    }

    public ExerciseVariant getVariant() {
        return variant;
    }
}
