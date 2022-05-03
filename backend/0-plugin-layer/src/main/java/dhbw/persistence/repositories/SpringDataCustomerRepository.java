package dhbw.persistence.repositories;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> getCustomersByWorkoutsIn(List<Workout> workouts);
}
