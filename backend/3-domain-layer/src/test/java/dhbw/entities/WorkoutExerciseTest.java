package dhbw.entities;

import dhbw.valueObjects.Exercise;
import dhbw.valueObjects.WorkoutExercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WorkoutExerciseTest {

    @Test
    void validConstructor() {
        assertDoesNotThrow(() -> new WorkoutExercise(mock(Exercise.class), 5 ,10));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void invalidSetAmount(int sets) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutExercise(mock(Exercise.class), sets, 10);
        });

        assertEquals("A planned exercise has to contain at least 1 set", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void invalidRepAmount(int reps) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutExercise(mock(Exercise.class), 3, reps);
        });

        assertEquals("A planned exercises sets have to be of at least 1 rep", exception.getMessage());
    }

}