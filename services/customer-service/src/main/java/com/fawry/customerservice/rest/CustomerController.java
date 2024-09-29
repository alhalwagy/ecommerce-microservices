package com.fawry.customerservice.rest;

import com.fawry.customerservice.entities.Customer;
import com.fawry.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer) {

    return customerService.createCustomer(customer);
  }

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("{id}")
  public Customer getCustomer(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @PutMapping("{id}")
  public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    return customerService.updateCustomerById(id, customer);
  }

  @DeleteMapping("{id}")
  public void deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
  }
}
