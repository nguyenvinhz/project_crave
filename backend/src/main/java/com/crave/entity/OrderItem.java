package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietDonHang")
public class OrderItem {
    @Id
    @Column(name = "MaCTDH", length = 10)
    private String id;
}

