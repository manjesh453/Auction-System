package com.auctionsysytem.auction;

import com.auctionsysytem.customer.Customer;
import com.auctionsysytem.customer.CustomerRepo;
import com.auctionsysytem.exception.ResourceNotFoundException;
import com.auctionsysytem.product.Product;
import com.auctionsysytem.product.ProductRepo;
import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final ProductRepo productRepository;
    private final CustomerRepo customerRepository;


    @Override
    public String makeAuction(AuctionDto auctionDto, Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        return checkBetAmount(product, auctionDto);
    }

    private String checkBetAmount(Product product, AuctionDto auctionDto) {
        Customer customer = customerRepository.findById(auctionDto.getCId()).orElseThrow(() -> new ResourceNotFoundException("Customer", auctionDto.getCId()));
        if (product.getStatus() == Status.ACTIVE ) {
            if (product.getHighestBet() > auctionDto.getBetAmount() && product.getProductPrice() > auctionDto.getBetAmount()) {
                return "Bet amount should be more than current product price";
            } else if (product.getProductBuyer()==customer) {
                return "You have already the highest better of this product";
            } else {
                if (product.getDateToFinishAuction().before(new Date())) {
                    product.setProductPrice(auctionDto.getBetAmount());
                    productRepository.save(product);
                    return "Congratulations you have made bet higher for " + product.getProductName();
                } else {
                    return "Sorry the time has exceed to make bet for " + product.getProductName();
                }
            }
        } else {
            return "Sorry the product is not active for bet";
        }
    }

}
