package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.mapper.CustomerResourceMapper;
import dhbw.resources.CustomerResource;
import dhbw.services.CustomerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private CustomerApplicationService customerApplicationService;
    private CustomerResourceMapper customerResourceMapper;

    @Autowired
    public CustomerController(CustomerApplicationService customerApplicationService, CustomerResourceMapper customerResourceMapper) {
        this.customerApplicationService = customerApplicationService;
        this.customerResourceMapper = customerResourceMapper;
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

    @PostMapping
    public ResponseEntity<CustomerResource> postCustomer(@RequestBody Customer newCustomer) {
        Customer customer = this.customerApplicationService.save(newCustomer);
        System.out.println(customer);
        return new ResponseEntity(customer, HttpStatus.CREATED);
    }
}
