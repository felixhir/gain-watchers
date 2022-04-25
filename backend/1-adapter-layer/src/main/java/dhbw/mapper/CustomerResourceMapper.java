package dhbw.mapper;

import dhbw.entities.Customer;
import dhbw.resources.CustomerResource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Component
public class CustomerResourceMapper implements Function<Customer, CustomerResource> {

    @Override
    public CustomerResource apply(Customer customer) {
        return map(customer);
    }

    private CustomerResource map(Customer customer) {
        List<String> workoutNames = new LinkedList<>();
        customer.getWorkouts().forEach(workout -> workoutNames.add(workout.getName()));
        return new CustomerResource(customer.getId(), customer.getName(), customer.getDaysAvailablePerWeek(), customer.getBodyFatPercentage(), customer.getWeight(), customer.getHeight(), workoutNames);
    }

    public Customer reverse(CustomerResource newCustomer) {
        return new Customer(
                newCustomer.getName(),
                newCustomer.getHeight(),
                newCustomer.getWeight(),
                newCustomer.getBodyFatPercentage(),
                newCustomer.getAvailability()
        );
    }
}
