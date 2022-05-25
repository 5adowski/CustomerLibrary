package sadowski.wojciech.customerLibrary.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAll(){
        return jdbcTemplate.query("SELECT id, firstname, lastname, phonenumber FROM customers",
                BeanPropertyRowMapper.newInstance(Customer.class));
    }

    public Customer getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, firstname, lastname, phonenumber FROM customers WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    public int save(List<Customer> customers) {
        customers.forEach(customer -> jdbcTemplate.update
                ("INSERT INTO customers(id, fistname, lastname, phonenumber VALUES (?, ?, ?, ?)",
                        customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber()
                ));
        return 1;
    }

    public int update(Customer customer) {
        return jdbcTemplate.update("UPDATE customers SET id = ?, fistname = ?, lastname = ?, phonenumber = ?",
                customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM customers WHERE id = ?", id);
    }
}
