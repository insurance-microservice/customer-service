package test.avows.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.avows.customer.dto.CustomerDto;
import test.avows.customer.entity.Customer;
import test.avows.customer.exception.ApiException;
import test.avows.customer.repository.CustomerRepository;

import java.sql.Timestamp;
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
                        .age(customer.getAge())
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
                        .age(customer.getAge())
                        .build()
                ).orElseThrow(() -> new ApiException(400, "not found", "no data found for customer id " + customerId));

    }

    public void createCustomer(CustomerDto param) {
        customerRepository.save(
                Customer.builder()
                        .fullName(param.getFullName())
                        .email(param.getEmail())
                        .phone(param.getPhone())
                        .age(param.getAge())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );
    }

    public void updateCustomer(Long customerId, CustomerDto param) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ApiException(400, "not found", "no data found for customer id " + customerId));

        String fullName = param.getFullName();
        String email = param.getEmail();
        String phone = param.getPhone();
        Integer age = param.getAge();

        if (fullName != null) customer.setFullName(fullName);
        if (email != null) customer.setEmail(email);
        if (phone != null) customer.setPhone(phone);
        if (age != null && age != 0) customer.setAge(age);

        customerRepository.save(customer);
    }
}
