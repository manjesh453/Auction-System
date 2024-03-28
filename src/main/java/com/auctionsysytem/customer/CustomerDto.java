package com.auctionsysytem.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;

    private String email;

    private String password;

    private String address;
}
