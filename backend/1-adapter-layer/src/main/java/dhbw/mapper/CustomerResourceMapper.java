package dhbw.mapper;

import dhbw.entities.Customer;
import dhbw.resources.CustomerResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CustomerResourceMapper implements Function<Customer, CustomerResource> {

    @Override
    public CustomerResource apply(Customer customer) {
        return map(customer);
    }

    private CustomerResource map(Customer customer) {
        Map<String, Integer> workoutMap = new HashMap<>();
        customer.getWorkouts().forEach(workout -> workoutMap.put(workout.getName(), workout.getDays()));
        return new CustomerResource(customer.getId(), customer.getName(), customer.getDaysAvailablePerWeek(), customer.getBodyFatPercentage(), customer.getWeight(), customer.getHeight(), workoutMap);
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
