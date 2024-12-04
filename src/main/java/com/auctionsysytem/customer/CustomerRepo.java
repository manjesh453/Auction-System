package com.auctionsysytem.customer;

import com.auctionsysytem.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    List<Customer> getCustomersByCustomerStatus(Status status);
}
