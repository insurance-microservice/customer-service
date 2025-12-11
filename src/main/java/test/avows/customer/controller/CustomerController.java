package test.avows.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.avows.customer.common.ApiResponse;
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

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDto param) {
        customerService.createCustomer(param);
        return ResponseEntity.ok().body(
                new ApiResponse(
                        true,
                        "successfully create customer",
                        null
                )
        );
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto param) {
        customerService.updateCustomer(customerId, param);
        return ResponseEntity.ok().body(
                new ApiResponse(
                        true,
                        "successfully update customer",
                        null
                )
        );
    }
}
