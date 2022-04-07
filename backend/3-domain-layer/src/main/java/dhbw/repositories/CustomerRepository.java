package dhbw.repositories;

import dhbw.entities.Customer;

import java.util.List;

public interface CustomerRepository {

    void save(Customer newCustomer);

    List<Customer> findAll();

    Customer getById(Long uuid);
}
