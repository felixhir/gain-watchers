package dhbw.entities;

import dhbw.helper.ExerciseVariant;
import dhbw.valueObjects.Exercise;
import dhbw.valueObjects.Name;
import dhbw.valueObjects.WorkoutExercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutExerciseTest {

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        exercise = new Exercise(new Name("Squat"), ExerciseVariant.BARBELL);
    }

    @Test
    void validConstructor() {
        assertDoesNotThrow(() -> new WorkoutExercise(exercise, 5 ,10));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void invalidSetAmount(int sets) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutExercise(exercise, sets, 10);
        });

        assertEquals("A planned exercise has to contain at least 1 set", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void invalidRepAmount(int reps) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutExercise(exercise, 3, reps);
        });

        assertEquals("A planned exercises sets have to be of at least 1 rep", exception.getMessage());
    }

}