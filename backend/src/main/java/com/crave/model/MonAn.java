package com.crave.model;

public class MonAn {
    private String maMon;
    private String tenMon;
    private String maDM;
    private double giaBan;
    private String hinhAnh;
    private String moTa;
    private String trangThai;

    public MonAn() {
    }

    public MonAn(String maMon, String tenMon, String maDM, double giaBan, String hinhAnh, String moTa, String trangThai) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.maDM = maDM;
        this.giaBan = giaBan;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
