package dhbw.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Workout workout;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Max Mustermann", 190, 80, 15, 3);

        WorkoutExercise exerciseOne = new WorkoutExercise(new Exercise("Bench Press", ExerciseVariant.BARBELL), 3, 10);
        WorkoutExercise exerciseTwo = new WorkoutExercise(new Exercise("Bicep Curl", ExerciseVariant.DUMBBELL), 5, 12);

        workout = new Workout("Test Workout", "a workout used in a test", 3, Arrays.asList(exerciseOne, exerciseTwo));
    }

    @Test
    void validCustomer() {
        Customer customer = new Customer("Max Mustermann", 190, 80, 15, 3);

        assertEquals("Max Mustermann", customer.getName());
        assertEquals(190, customer.getHeight());
        assertEquals(80, customer.getWeight());
        assertEquals(15, customer.getBodyFatPercentage());
        assertEquals(3, customer.getDaysAvailablePerWeek());
        assertEquals(0, customer.getWorkouts().size());
    }

    @Test
    void emptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("", 190, 80, 15, 3);
        });

        assertEquals("Every customer must have a name", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 99, 251, 300})
    void invalidHeight(int height) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Karl", height, 80, 15, 3);
        });

        assertEquals("A persons height must lie within a reasonable range (100cm-250cm)", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 101})
    void invalidBodyFat(int bodyFatPercentage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Karl", 180, 80, bodyFatPercentage, 3);
        });

        assertEquals("A persons body fat cannot be less than 1% or more than 100%", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    void invalidAvailability(int daysAvailable) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Karl", 180, 80, 10, daysAvailable);
        });

        assertEquals("A week cannot have negative or more than 7 days", exception.getMessage());
    }

    @Test
    void addWorkout() {
        Customer customer = new Customer("Max Mustermann", 190, 80, 15, 3);

        customer.addWorkout(workout);

        assertEquals(1, customer.getWorkouts().size());
    }

    @Test
    void exceedingCustomerAvailability() {
        Customer customer = new Customer("Max Mustermann", 190, 80, 15, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(workout);
        });

        assertEquals("This workout does not fit the customers schedule", exception.getMessage());
    }

    @Test
    void addWorkoutMoreThanOnce() {
        customer.addWorkout(workout);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(workout);
        });

        assertEquals("This workout has already been assigned to the customer", exception.getMessage());
    }
}