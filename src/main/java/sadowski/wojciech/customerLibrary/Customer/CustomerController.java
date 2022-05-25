package sadowski.wojciech.customerLibrary.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> getAll(){
        return  customerRepository.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") int id){
        return customerRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Customer> customers) {
        return customerRepository.save(customers);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.getById(id);

        if(customer != null) {
            customer.setId(updatedCustomer.getId());
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            customerRepository.update(customer);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partUpdate(@PathVariable("id") int id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.getById(id);

        if(customer != null) {
            if(updatedCustomer.getFirstName() != null) customer.setFirstName(updatedCustomer.getFirstName());
            if(updatedCustomer.getLastName() != null) customer.setLastName(updatedCustomer.getLastName());
            if(updatedCustomer.getPhoneNumber() != null) customer.setPhoneNumber(updatedCustomer.getPhoneNumber());

            customerRepository.update(customer);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return customerRepository.delete(id);
    }
}
