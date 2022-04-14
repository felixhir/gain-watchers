package dhbw.repositories;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutAssignment;

import java.util.List;

public interface WorkoutAssignmentRepository {

    List<Workout> getWorkoutsForCustomer(Customer customer);

    List<WorkoutAssignment> findAll();

    WorkoutAssignment save(WorkoutAssignment workoutAssignment);
}
