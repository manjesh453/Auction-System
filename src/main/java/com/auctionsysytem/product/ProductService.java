package com.auctionsysytem.product;

import java.util.List;

public interface ProductService {

    String createProduct(ProductDto productDto);
    String updateProduct(ProductDto productDto, Integer pId);
    ProductDto getProductByProductId(String pId);
    List<ProductDto> getAllProducts();
}
