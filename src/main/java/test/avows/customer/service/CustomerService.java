package test.avows.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.avows.customer.dto.CustomerDto;
import test.avows.customer.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDto> getCustomer() {
        return customerRepository.findAll().stream()
                .map(customer -> CustomerDto.builder()
                        .customerId(customer.getCustomerId())
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .build()
                ).toList();
    }

    public CustomerDto getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> CustomerDto.builder()
                        .customerId(customer.getCustomerId())
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .build()
                ).orElseThrow(() -> new RuntimeException("Customer not found"));

    }
}
