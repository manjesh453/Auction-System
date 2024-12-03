package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    String createProduct(MultipartFile image, RequestProductDto requestProductDto);

    String updateProduct(RequestProductDto requestProductDto, Integer pId);

    ResponseProductDto getProductByProductId(Integer pId);

    List<ResponseProductDto> getAllProducts();

    List<ResponseProductDto> getAllProductsByStatus(Status status);

    String changeProductStatus(Status status,Integer pId);
}
