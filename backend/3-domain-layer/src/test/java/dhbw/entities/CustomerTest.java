package dhbw.entities;

import dhbw.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(new Name("Max Mustermann"), new Height(190), new Weight(80, true), new BodyFatPercentage(15), new Availability(3));
    }

    @Test
    void validCustomer() {
        assertDoesNotThrow(() ->
                new Customer(new Name("Max Mustermann"), new Height(190), new Weight(80, true), new BodyFatPercentage(15), new Availability(3))
        );
    }

    @Test
    void addWorkout() {
        customer.addWorkout(mock(Workout.class));

        assertEquals(1, customer.getWorkouts().size());
    }

    @Test
    void exceedingCustomerAvailability() {
        Workout mockWorkout = mock(Workout.class);
        when(mockWorkout.getDays()).thenReturn(7);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(mockWorkout);
        });

        assertEquals("This workout does not fit the customers schedule", exception.getMessage());
    }

    @Test
    void addWorkoutMoreThanOnce() {
        Workout mockWorkout = mock(Workout.class);

        customer.addWorkout(mockWorkout);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addWorkout(mockWorkout);
        });

        assertEquals("This workout has already been assigned to the customer", exception.getMessage());
    }
}