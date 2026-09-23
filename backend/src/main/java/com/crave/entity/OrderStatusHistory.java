package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LichSuTrangThaiDonHang")
public class OrderStatusHistory {
    @Id
    @Column(name = "MaLS", length = 10)
    private String id;
}

