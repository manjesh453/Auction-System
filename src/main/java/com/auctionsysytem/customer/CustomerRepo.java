package com.auctionsysytem.customer;

import com.auctionsysytem.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    List<Customer> getCustomersByStatus(Status status);
}
