package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GioHang")
public class Cart {
    @Id
    @Column(name = "MaGioHang", length = 10)
    private String id;
}

