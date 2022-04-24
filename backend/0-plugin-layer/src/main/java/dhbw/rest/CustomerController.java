package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.mapper.CustomerResourceMapper;
import dhbw.resources.CustomerResource;
import dhbw.services.CustomerApplicationService;
import dhbw.services.WorkoutApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(this.customerApplicationService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postCustomer(@RequestBody CustomerResource newCustomer) {
        try {
            Customer customer = this.customerApplicationService.save(this.customerResourceMapper.reverse(newCustomer));
            return new ResponseEntity(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getClass().equals(IllegalArgumentException.class)) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(path = "/{customerId}")
    public ResponseEntity<?> assignWorkout(@PathVariable Long customerId, @RequestParam String workoutName) {
        try {
            Customer customer = this.customerApplicationService.getById(customerId);
            Workout workout = this.workoutApplicationService.getByName(workoutName);
            customer.addWorkout(workout);
            this.customerApplicationService.save(customer);
            return new ResponseEntity<>(customerResourceMapper.apply(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
