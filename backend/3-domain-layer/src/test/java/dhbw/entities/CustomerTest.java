package dhbw.entities;

import dhbw.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(new Name("Max Mustermann"), new Height(190), new Weight(80, true), new BodyFatPercentage(15), new DaysPerWeek(3));
    }

    @Test
    void validCustomer() {
        assertDoesNotThrow(() ->
                new Customer(new Name("Max Mustermann"), new Height(190), new Weight(80, true), new BodyFatPercentage(15), new DaysPerWeek(3))
        );
    }

    @Test
    void addWorkout() {
        Workout mockWorkout = mock(Workout.class);
        when(mockWorkout.getDays()).thenReturn(new DaysPerWeek(1));

        customer.addWorkout(mockWorkout);

        assertEquals(1, customer.getWorkouts().size());
    }

    @Test
    void exceedingCustomerAvailability() {
        Workout mockWorkout = mock(Workout.class);
        when(mockWorkout.getDays()).thenReturn(new DaysPerWeek(7));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(mockWorkout);
        });

        assertEquals("This workout does not fit the customers schedule", exception.getMessage());
    }

    @Test
    void addWorkoutMoreThanOnce() {
        Workout mockWorkout = mock(Workout.class);
        when(mockWorkout.getDays()).thenReturn(new DaysPerWeek(1));

        customer.addWorkout(mockWorkout);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(mockWorkout);
        });

        assertEquals("This workout has already been assigned to the customer", exception.getMessage());
    }
}