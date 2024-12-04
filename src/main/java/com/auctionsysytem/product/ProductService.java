package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    String createProduct(MultipartFile image, RequestProductDto requestProductDto);

    String updateProductDetails(RequestProductDto requestProductDto, Integer pId);

    ResponseProductDto getProductByProductId(Integer pId);

    List<ResponseProductDto> getAllProducts();

    List<ResponseProductDto> getAllProductsByStatus(Status status);

    String changeProductStatus(Status status,Integer pId);

    String updateProductImage(Integer pId, MultipartFile image);

    String deleteProduct(Integer pId);

    byte[] previewImage(Integer pId);
}
