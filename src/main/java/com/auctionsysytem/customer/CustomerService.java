package com.auctionsysytem.customer;

import com.auctionsysytem.shared.Status;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerDto customerDto);

    String updateCustomer(CustomerDto customerDto,Integer cId);

    String deleteCustomer(Integer cId);

    CustomerDto getCustomerById(Integer cId);

    List<CustomerDto> getAllCustomer();

    List<CustomerDto> getCustomerByStatus(Status status);
}
