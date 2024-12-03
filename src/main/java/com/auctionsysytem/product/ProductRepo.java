package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findProductByStatus(Status status);
}
