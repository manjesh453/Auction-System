package com.auctionsysytem.customer;

import com.auctionsysytem.exception.ResourceNotFoundException;
import com.auctionsysytem.shared.Role;
import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;

    private final CustomerRepo customerRepo;

    @Override
    public String createCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setRole(Role.USER);
        customer.setStatus(Status.INACTIVE);
        customerRepo.saveAndFlush(customer);
        return "Successfully register";
    }

    @Override
    public String updateCustomer(CustomerDto customerDto, Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customerDto.getPassword());
        customerRepo.save(customer);
        return "Your data has been Updated";
    }

    @Override
    public String deleteCustomer(Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setStatus(Status.DELETED);
        customerRepo.save(customer);
        return "Successfully deleted";
    }

    @Override
    public CustomerDto getCustomerById(Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepo.findAll();
        return customers.stream().map(li -> modelMapper.map(li, CustomerDto.class)).toList();
    }

    @Override
    public List<CustomerDto> getCustomerByStatus(Status status) {
        List<Customer> customers = customerRepo.getCustomersByStatus(status);
        return customers.stream().map(li -> modelMapper.map(li, CustomerDto.class)).toList();
    }
}
