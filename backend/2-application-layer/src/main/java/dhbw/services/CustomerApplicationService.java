package dhbw.services;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerApplicationService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerApplicationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long uuid) {
        return customerRepository.getById(uuid);
    }

    public List<Customer> getByWorkout(Workout workout) {
        return customerRepository.getByWorkout(workout);
    }
}
