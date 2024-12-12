package com.auctionsysytem.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Carts, Integer> {

    Carts findByProduct_ProductId(Integer pId);
}
