package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DiaChiGiaoHang")
public class ShippingAddress {
    @Id
    @Column(name = "MaDiaChi", length = 10)
    private String id;
}

