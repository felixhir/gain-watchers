package dhbw.persistence.repositories;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataWorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {
    List<Workout> findByCustomer(Customer customer);
}
