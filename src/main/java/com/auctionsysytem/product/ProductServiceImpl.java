package com.auctionsysytem.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public String createProduct(ProductDto productDto) {
        return "";
    }

    @Override
    public String updateProduct(ProductDto productDto, Integer pId) {
        return "";
    }

    @Override
    public ProductDto getProductByProductId(String pId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return List.of();
    }
}
