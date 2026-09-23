package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DanhMuc")
public class Category {
    @Id
    @Column(name = "MaDM", length = 10)
    private String id;
}

