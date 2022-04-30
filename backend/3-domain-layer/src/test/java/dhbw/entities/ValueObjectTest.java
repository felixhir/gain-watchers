package dhbw.entities;

import dhbw.valueObjects.Availability;
import dhbw.valueObjects.BodyFatPercentage;
import dhbw.valueObjects.Height;
import dhbw.valueObjects.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValueObjectTest {

    @Test
    void emptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name("");
        });

        assertEquals("A name cannot be empty", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 49, 261, 300})
    void invalidHeight(int height) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Height(height);
        });

        assertEquals("A persons height must lie within a reasonable range (50cm-260cm)", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 101})
    void invalidBodyFat(int bodyFatPercentage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BodyFatPercentage(bodyFatPercentage);
        });

        assertEquals("A persons body fat cannot be less than 1% or more than 100%", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    void invalidAvailability(int availability) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Availability(availability);
        });

        assertEquals("A week cannot have negative or more than 7 days", exception.getMessage());
    }
}
