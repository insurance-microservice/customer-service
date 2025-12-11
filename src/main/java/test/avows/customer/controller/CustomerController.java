package test.avows.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.avows.customer.dto.CustomerDto;
import test.avows.customer.service.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDto> getCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping()
    public CustomerDto getCustomer(@RequestBody CustomerDto param) {
        log.info("request with customer id: {}", param.getCustomerId());
        return customerService.getCustomer(param.getCustomerId());
    }
}
