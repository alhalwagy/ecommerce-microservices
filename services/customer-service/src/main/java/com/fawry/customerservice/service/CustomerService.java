package com.fawry.customerservice.service;

import com.fawry.customerservice.entities.Customer;
import com.fawry.customerservice.exception.customExceptions.ResourceNotFoundException;
import com.fawry.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer createCustomer(Customer customer) {

    return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {

    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
  }

  public Customer updateCustomerById(Long id, Customer customer) {

    Customer getCustomer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

    getCustomer.setFirstname(customer.getFirstname());
    getCustomer.setLastname(customer.getLastname());
    return customerRepository.save(getCustomer);
  }

  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }
}
