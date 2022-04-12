package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.mapper.CustomerResourceMapper;
import dhbw.resources.CustomerResource;
import dhbw.services.CustomerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerResource> getCustomers() {
        return this.customerApplicationService.getAll().stream()
                .map(customerResourceMapper)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return this.customerApplicationService.getById(id);
    }
}
