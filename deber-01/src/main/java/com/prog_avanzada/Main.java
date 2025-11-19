package com.prog_avanzada;

import com.prog_avanzada.model.Customer;
import com.prog_avanzada.repository.ICustomerRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.List;
import java.util.Optional;

public class Main {
  public static void main(String[] args) {

    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      ICustomerRepository customerRepository = container.select(ICustomerRepository.class).get();

      // ! Customer
      Customer newCustomer = Customer.builder()
          .name("Juan Perez")
          .email("juan@google.com")
          .version(1)
          .build();

//      boolean result = customerRepository.save(newCustomer);

//      System.out.println("Customer guardado: " + result);

      Optional<Customer> customer = customerRepository.findById(9L);

      System.out.println(customer);

      List<Customer> customers = customerRepository.findAll();

      System.out.println(customers);
    }
  }
}
