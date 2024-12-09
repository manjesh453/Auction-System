package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductScheduler {
    private final ProductRepo productRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    protected void scheduleProductSold() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getDateToFinishAuction().before(new Date())) {
                product.setStatus(Status.SOLD);
                productRepository.save(product);
            }
        }
    }
}
