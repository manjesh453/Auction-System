package com.auctionsysytem.product;

import com.auctionsysytem.customer.Customer;
import com.auctionsysytem.customer.CustomerRepo;
import com.auctionsysytem.exception.ResourceNotFoundException;
import com.auctionsysytem.s3bucket.service.StorageService;
import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final StorageService imageService;
    private final ProductRepo productRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepo customerRepository;

    @Override
    public String createProduct(MultipartFile image, RequestProductDto requestProductDto) {
        log.info("Product is" + requestProductDto.toString());
        if (imageService.uploadFile(image)) {
            Product product = modelMapper.map(requestProductDto, Product.class);
            product.setProductImage(image.getOriginalFilename());
            product.setStatus(Status.UNVERIFIED);
            product.setHighestBet(0);
            product.setProductOwner(customerRepository.findById(requestProductDto.getProductOwnerId()).
                    orElseThrow(() -> new ResourceNotFoundException("Customer", requestProductDto.getProductOwnerId())));
            productRepository.save(product);
            return "Product has been successfully uploaded";
        } else {
            return "Sorry the product cannot be uploaded";
        }

    }

    @Override
    public String updateProductDetails(RequestProductDto requestProductDto, Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        product.setProductName(requestProductDto.getProductName());
        product.setProductPrice(requestProductDto.getProductPrice());
        product.setProductDescription(requestProductDto.getProductDescription());
        productRepository.save(product);
        return "Product has been updated successfully";
    }

    @Override
    public ResponseProductDto getProductByProductId(Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(li -> modelMapper.map(li, ResponseProductDto.class)).toList();
    }

    @Override
    public List<ResponseProductDto> getAllProductsByStatus(Status status) {
        List<Product> products = productRepository.findProductByStatus(status);
        return products.stream().map(li -> modelMapper.map(li, ResponseProductDto.class)).toList();
    }

    @Override
    public String changeProductStatus(Status status, Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        product.setStatus(status);
        productRepository.save(product);
        return "Product Status has been changed successfully";
    }

    @Override
    public String updateProductImage(Integer pId, MultipartFile image) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        String result = null;
        if (imageService.deleteFile(product.getProductImage())) {
            if (imageService.uploadFile(image)) {
                product.setProductImage(image.getOriginalFilename());
                productRepository.save(product);
                result = "Image has been successfully uploaded";
            } else {
                result = "Sorry the image cannot be uploaded";
            }
        } else {
            result = "Failed to delete old image";
        }
        return result;
    }

    @Override
    public String deleteProduct(Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        product.setStatus(Status.DELETED);
        productRepository.save(product);
        return "Product has been deleted successfully";
    }

    @Override
    public byte[] previewImage(Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        return imageService.downloadFile(product.getProductImage());
    }

    @Override
    public String startAuction(StartAuctionDto startAuctionDto) {
        Customer customer = customerRepository.findById(startAuctionDto.getCId()).orElseThrow(() -> new ResourceNotFoundException("Customer", startAuctionDto.getCId()));
        Product product = productRepository.findById(startAuctionDto.getPId()).orElseThrow(() -> new ResourceNotFoundException("Product", startAuctionDto.getPId()));
        if (product.getProductOwner() == customer) {
            product.setStatus(Status.ACTIVE);
            product.setDateToStartAuction(new Date());
            product.setDateToFinishAuction(dateToAdd(startAuctionDto.getDayToEndAuction()));
            return "Auction for the " + product.getProductName() + " has been started";
        }
        return "Sorry you are not the owner of this product";
    }

    private Date dateToAdd(Integer dateToAdd) {
        return new Date(new Date().getTime() + dateToAdd * 24 * 60 * 60 * 1000);
    }

}
