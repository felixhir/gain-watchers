package dhbw.persistence;

import dhbw.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<Customer, Long> {
}
