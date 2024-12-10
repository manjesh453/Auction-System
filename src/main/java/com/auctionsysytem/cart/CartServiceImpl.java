package com.auctionsysytem.cart;

import com.auctionsysytem.customer.Customer;
import com.auctionsysytem.customer.CustomerRepo;
import com.auctionsysytem.exception.ResourceNotFoundException;
import com.auctionsysytem.product.Product;
import com.auctionsysytem.product.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CartDto> getBetProductsByCustomer(Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", cId));
        List<Product> products = productRepo.findProductByProductBuyer(customer);
        return products.stream().map(pro -> modelMapper.map(pro, CartDto.class)).toList();
    }
}
