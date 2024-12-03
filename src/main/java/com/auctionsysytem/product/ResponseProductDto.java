package com.auctionsysytem.product;

import com.auctionsysytem.shared.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDto {
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productImage;
    private String highestBet;
    private Status status;
}
