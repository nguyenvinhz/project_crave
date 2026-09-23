package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KhuyenMai")
public class Promotion {
    @Id
    @Column(name = "MaKM", length = 10)
    private String id;
}

