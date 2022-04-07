package dhbw.persistence;

import dhbw.entities.Customer;
import dhbw.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryBridge implements CustomerRepository {

    private SpringDataCustomerRepository springDataCustomerRepository;

    @Autowired
    public CustomerRepositoryBridge(SpringDataCustomerRepository springDataCustomerRepository) {
        this.springDataCustomerRepository = springDataCustomerRepository;
    }

    @Override
    public void save(Customer newCustomer) {
        springDataCustomerRepository.save(newCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return springDataCustomerRepository.findAll();
    }

    @Override
    public Customer getById(Long uuid) {
        return springDataCustomerRepository.getById(uuid);
    }
}
