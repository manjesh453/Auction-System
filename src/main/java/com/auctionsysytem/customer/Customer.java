package com.auctionsysytem.customer;

import com.auctionsysytem.shared.Role;
import com.auctionsysytem.shared.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    private String customerEmail;

    private String customerPassword;

    private String customerAddress;

    @Enumerated(EnumType.STRING)
    private Role customerRole;

    @Enumerated(EnumType.STRING)
    private Status customerStatus;
}
