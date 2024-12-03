package com.auctionsysytem.customer;

import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auction")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/createUser")
    public String createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @PostMapping("/updateCustomer/{customerId}")
    public String updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer customerId) {
        return customerService.updateCustomer(customerDto, customerId);
    }

    @GetMapping("/getAllCustomer")
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomerById/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/getCustomerByStatus/{status}")
    public List<CustomerDto> getCustomerByStatus(@PathVariable Status status) {
        return customerService.getCustomerByStatus(status);
    }
}
