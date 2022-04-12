package dhbw.mapper;

import dhbw.entities.Customer;
import dhbw.resources.CustomerResource;
import dhbw.valueObjects.Weight;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerResourceMapper implements Function<Customer, CustomerResource> {

    @Override
    public CustomerResource apply(Customer customer) {
        return map(customer);
    }

    private CustomerResource map(Customer customer) {
        //Weight weight = customer.getWeight();
        //double weightInKgs = weight.usesMetricSystem() ? weight.getValue() : weight.getValue() * 0.45;
        return new CustomerResource(customer.getName(), customer.getDaysAvailablePerWeek(), customer.getWeight(), customer.getHeight() / 100);
    }
}
