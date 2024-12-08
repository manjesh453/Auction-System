package com.auctionsysytem.auction;

import com.auctionsysytem.exception.ResourceNotFoundException;
import com.auctionsysytem.product.Product;
import com.auctionsysytem.product.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final ProductRepo productRepository;


    @Override
    public String makeAuction(float amount, Integer pId) {
        Product product = productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", pId));
        return checkBetAmount(product, amount);
    }

    private String checkBetAmount(Product product, float amount) {
        if (product.getHighestBet() > amount && product.getProductPrice() > amount) {
            return "Bet amount should be more than current product price";
        } else {
            if (product.getDateToFinishAuction().before(new Date())) {
                product.setProductPrice(amount);
                productRepository.save(product);
                return "Congratulations you have made bet higher for " + product.getProductName();
            } else {
                return "Sorry the time has exceed to make bet for " + product.getProductName();
            }
        }
    }
}
