package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.services.customerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private customerApplicationService customerApplicationService;

    @Autowired
    public CustomerController(customerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return this.customerApplicationService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return this.customerApplicationService.getById(id);
    }
}
