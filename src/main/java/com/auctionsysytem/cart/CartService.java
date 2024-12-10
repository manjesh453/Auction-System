package com.auctionsysytem.cart;

import java.util.List;

public interface CartService {

    List<CartDto> getBetProductsByCustomer(Integer customerId);
}
