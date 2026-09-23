package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MonAn")
public class Food {
    @Id
    @Column(name = "MaMon", length = 10)
    private String id;
}

