package com.auctionsysytem.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDto {
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productImage;

}
