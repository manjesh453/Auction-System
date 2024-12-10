package com.auctionsysytem.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private String productName;
    private String productDescription;
    private float productPrice;
    private String productImage;
    private float highestBet;
}
