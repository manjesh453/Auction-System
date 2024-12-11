package com.auctionsysytem.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartAuctionDto {
    private Integer cId;
    private Integer pId;
    private Integer dayToEndAuction;
}
