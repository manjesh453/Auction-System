package com.auctionsysytem.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/makeAuction/{pId}")
    public String makeAuction(@RequestBody float betAmount, @PathVariable Integer pId) {
        return auctionService.makeAuction(betAmount, pId);
    }
}
