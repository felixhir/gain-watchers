package dhbw.repositories;

import dhbw.entities.Customer;
import dhbw.entities.Workout;

import java.util.List;

public interface CustomerRepository {

    Customer save(Customer newCustomer);

    List<Customer> findAll();

    Customer getById(Long uuid);

    List<Customer> getByWorkout(Workout workout);
}
