package com.crave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TuyChonMon")
public class FoodOption {
    @Id
    @Column(name = "MaTuyChon", length = 10)
    private String id;
}

