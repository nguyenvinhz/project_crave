package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class Staff {
    @Id
    @Column(name = "MaNV", length = 10)
    private String id;
}

