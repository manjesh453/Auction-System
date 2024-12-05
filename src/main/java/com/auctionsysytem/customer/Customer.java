package com.auctionsysytem.customer;

import com.auctionsysytem.product.Product;
import com.auctionsysytem.shared.Role;
import com.auctionsysytem.shared.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "productOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Product> products = new ArrayList<>();

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate = new Date();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate = new Date();
}
