package dhbw.mapper;

import dhbw.entities.Customer;
import dhbw.resources.CustomerResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerResourceMapper implements Function<Customer, CustomerResource> {

    @Override
    public CustomerResource apply(Customer customer) {
        return map(customer);
    }

    private CustomerResource map(Customer customer) {
        return new CustomerResource(customer.getId(), customer.getName(), customer.getDaysAvailablePerWeek(), customer.getWeight(), customer.getHeight());
    }
}
