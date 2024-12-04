package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/auction/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public String createProduct(@RequestParam("image") MultipartFile image, @RequestPart RequestProductDto productDto) {
        return productService.createProduct(image, productDto);
    }

    @PostMapping("/updateProductDetails/{pId}")
    public String updateProductDetails(@RequestBody RequestProductDto productDto, @PathVariable Integer pId) {
        return productService.updateProductDetails(productDto, pId);
    }

    @PostMapping("/updateProductImage/{pId}")
    public String updateProductImage(@RequestParam MultipartFile image, @PathVariable Integer pId) {
        return productService.updateProductImage(pId, image);
    }

    @GetMapping("/getAllProducts")
    public List<ResponseProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getAllProductsByStatus/{status}")
    public List<ResponseProductDto> getAllProductsByStatus(@PathVariable Status status) {
        return productService.getAllProductsByStatus(status);
    }

    @GetMapping("/getProductByProductId/{pId}")
    public ResponseProductDto getProductByProductId(@PathVariable Integer pId) {
        return productService.getProductByProductId(pId);
    }

    @GetMapping("/changeProductStatus/{pId}")
    public String changeProductStatus(@RequestBody Status status, @PathVariable Integer pId) {
        return productService.changeProductStatus(status, pId);
    }

    @GetMapping("/deleteProduct/{pId}")
    public String deleteProduct(@PathVariable Integer pId) {
        return productService.deleteProduct(pId);
    }

    @GetMapping("/previewImage/{pId}")
    public ResponseEntity<byte[]> previewImage(@PathVariable Integer pId) {
        byte[] imageBytes = productService.previewImage(pId);
        if (imageBytes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
