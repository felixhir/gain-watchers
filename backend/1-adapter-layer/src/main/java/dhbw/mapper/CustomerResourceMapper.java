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
        customer.getWorkouts().forEach((workout, amount) -> workoutMap.put(workout.getName(), amount));
        return new CustomerResource(customer.getId(), customer.getName(), customer.getDaysAvailablePerWeek(), customer.getWeight(), customer.getHeight(), workoutMap);
    }
}
