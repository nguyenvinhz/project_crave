package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DonHang")
public class FoodOrder {
    @Id
    @Column(name = "MaDH", length = 10)
    private String id;
}

