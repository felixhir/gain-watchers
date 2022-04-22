package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.mapper.CustomerResourceMapper;
import dhbw.resources.CustomerResource;
import dhbw.services.CustomerApplicationService;
import dhbw.services.WorkoutApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "api/customers")
public class CustomerController {

    private CustomerApplicationService customerApplicationService;
    private CustomerResourceMapper customerResourceMapper;
    private WorkoutApplicationService workoutApplicationService;

    @Autowired
    public CustomerController(CustomerApplicationService customerApplicationService, CustomerResourceMapper customerResourceMapper, WorkoutApplicationService workoutApplicationService) {
        this.customerApplicationService = customerApplicationService;
        this.customerResourceMapper = customerResourceMapper;
        this.workoutApplicationService = workoutApplicationService;
    }

    @GetMapping
    public List<CustomerResource> getCustomers() {
        return this.customerApplicationService.getAll().stream()
                .map(customerResourceMapper)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return this.customerApplicationService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCustomer(@RequestBody Customer newCustomer) {
        Customer customer = this.customerApplicationService.save(newCustomer);
        return new ResponseEntity(customer, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{customerId}")
    public ResponseEntity<?> assignWorkout(@PathVariable Long customerId, @RequestParam String workoutName, @RequestParam int amount) {
        Customer customer = this.customerApplicationService.getById(customerId);
        Workout workout = this.workoutApplicationService.getByName(workoutName);
        customer.addWorkout(workout, amount);
        this.customerApplicationService.save(customer);
        return new ResponseEntity<>(customerResourceMapper.apply(customer), HttpStatus.OK);
    }
}
