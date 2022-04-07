package dhbw.repositories;

import dhbw.entities.Customer;

import java.util.List;

public interface CustomerRepository {

    void create(Customer newCustomer);

    List<Customer> findAll();

    Customer getById(String uuid);
}
