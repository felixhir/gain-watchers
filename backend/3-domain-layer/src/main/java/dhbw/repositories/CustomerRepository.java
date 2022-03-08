package dhbw.repositories;

import dhbw.entities.Customer;

import java.util.List;

public interface CustomerRepository {

    void create(Customer newCustomer);

    List<Customer> getAll();

    Customer getById(String uuid);
}
