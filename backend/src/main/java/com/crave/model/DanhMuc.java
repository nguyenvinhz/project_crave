package com.crave.model;

public class DanhMuc {
    private String maDM;
    private String tenDM;
    private String moTa;
    private String hinhAnh;

    public DanhMuc() {
    }

    public DanhMuc(String maDM, String tenDM, String moTa, String hinhAnh) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
