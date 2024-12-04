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
        customer.setCustomerRole(Role.USER);
        customer.setCustomerStatus(Status.INACTIVE);
        customerRepo.saveAndFlush(customer);
        return "Successfully register";
    }

    @Override
    public String updateCustomer(CustomerDto customerDto, Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setCustomerName(customerDto.getName());
        customer.setCustomerEmail(customerDto.getEmail());
        customer.setCustomerAddress(customerDto.getAddress());
        customer.setCustomerPassword(customerDto.getPassword());
        customerRepo.save(customer);
        return "Your data has been Updated";
    }

    @Override
    public String deleteCustomer(Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setCustomerStatus(Status.DELETED);
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
        List<Customer> customers = customerRepo.getCustomersByCustomerStatus(status);
        return customers.stream().map(li -> modelMapper.map(li, CustomerDto.class)).toList();
    }

    @Override
    public String changeCustomerStatus(Integer cId, Status status) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setCustomerStatus(status);
        customerRepo.save(customer);
        return "Customer status has been changed successfully to "+ status.toString();
    }

    @Override
    public String changeCustomerRole(Integer cId, Role role) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        customer.setCustomerRole(role);
        customerRepo.save(customer);
        return "Customer role has been changed successfully to " + role.toString();
    }
}
