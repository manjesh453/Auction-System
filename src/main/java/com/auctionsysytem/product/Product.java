package com.auctionsysytem.product;

import com.auctionsysytem.customer.Customer;
import com.auctionsysytem.shared.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private String productImage;
    private float highestBet;
    private Status status;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate = new Date();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer productOwner;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer productBuyer;

    private Date dateToStartAuction;

    private Date dateToFinishAuction;

}
