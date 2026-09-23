DROP DATABASE IF EXISTS QuanLyDatDoAn;
CREATE DATABASE QuanLyDatDoAn
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE QuanLyDatDoAn;

CREATE TABLE KhachHang (
    MaKH            VARCHAR(10)     NOT NULL,
    HoTen           NVARCHAR(100)   NOT NULL,
    SoDienThoai     VARCHAR(15)     NOT NULL,
    Email           VARCHAR(100)    NULL,
    MatKhau         VARCHAR(255)    NOT NULL,
    DiaChiMacDinh   NVARCHAR(255)   NULL,
    TrangThai       ENUM('HoatDong','DaKhoa') NOT NULL DEFAULT 'HoatDong',
    NgayDangKy      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_KhachHang PRIMARY KEY (MaKH),
    CONSTRAINT UQ_KhachHang_SDT UNIQUE (SoDienThoai),
    CONSTRAINT UQ_KhachHang_Email UNIQUE (Email)
) ENGINE=InnoDB;

CREATE TABLE NhanVien (
    MaNV            VARCHAR(10)     NOT NULL,
    TenNV           NVARCHAR(100)   NOT NULL,
    ChucVu          ENUM(
                        'QuanTriVien',
                        'NhanVienQuanLiMon',
                        'NhanVienXuLyDonHang',
                        'NhanVienQuanLiKhuyenMai',
                        'NhanVienQuanLiNhanSu'
                    ) NOT NULL,
    DiaChi          NVARCHAR(255)   NULL,
    SoDienThoai     VARCHAR(15)     NOT NULL,
    Email           VARCHAR(100)    NOT NULL,
    MatKhau         VARCHAR(255)    NOT NULL,
    NgayVaoLam      DATE            NOT NULL,
    TrangThai       ENUM('DangLamViec','DaNghiViec') NOT NULL DEFAULT 'DangLamViec',
    CONSTRAINT PK_NhanVien PRIMARY KEY (MaNV),
    CONSTRAINT UQ_NhanVien_SDT UNIQUE (SoDienThoai),
    CONSTRAINT UQ_NhanVien_Email UNIQUE (Email)
) ENGINE=InnoDB;

CREATE TABLE DanhMuc (
    MaDM            VARCHAR(10)     NOT NULL,
    TenDM           NVARCHAR(100)   NOT NULL,
    MoTa            NVARCHAR(255)   NULL,
    CONSTRAINT PK_DanhMuc PRIMARY KEY (MaDM),
    CONSTRAINT UQ_DanhMuc_Ten UNIQUE (TenDM)
) ENGINE=InnoDB;

CREATE TABLE MonAn (
    MaMon           VARCHAR(10)     NOT NULL,
    TenMon          NVARCHAR(150)   NOT NULL,
    MaDM            VARCHAR(10)     NOT NULL,
    GiaBan          DECIMAL(12,0)   NOT NULL,
    HinhAnh         VARCHAR(255)    NULL,
    MoTa            NVARCHAR(500)   NULL,
    TrangThai       ENUM('DangBan','NgungBan') NOT NULL DEFAULT 'DangBan',
    CONSTRAINT PK_MonAn PRIMARY KEY (MaMon),
    CONSTRAINT FK_MonAn_DanhMuc FOREIGN KEY (MaDM)
        REFERENCES DanhMuc(MaDM) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT CK_MonAn_GiaBan CHECK (GiaBan > 0)
) ENGINE=InnoDB;

CREATE TABLE TuyChonMon (
    MaTuyChon       VARCHAR(10)     NOT NULL,
    MaMon           VARCHAR(10)     NOT NULL,
    LoaiTuyChon     ENUM('Size','Topping','MucDuong','MucDa','Khac') NOT NULL,
    TenTuyChon      NVARCHAR(100)   NOT NULL,
    GiaThem         DECIMAL(12,0)   NOT NULL DEFAULT 0,
    CONSTRAINT PK_TuyChonMon PRIMARY KEY (MaTuyChon),
    CONSTRAINT FK_TuyChonMon_MonAn FOREIGN KEY (MaMon)
        REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT CK_TuyChonMon_GiaThem CHECK (GiaThem >= 0)
) ENGINE=InnoDB;

CREATE TABLE DiaChiGiaoHang (
    MaDiaChi        VARCHAR(10)     NOT NULL,
    MaKH            VARCHAR(10)     NOT NULL,
    DiaChiChiTiet   NVARCHAR(255)   NOT NULL,
    GhiChu          NVARCHAR(255)   NULL,
    MacDinh         TINYINT(1)      NOT NULL DEFAULT 0,
    CONSTRAINT PK_DiaChiGiaoHang PRIMARY KEY (MaDiaChi),
    CONSTRAINT FK_DiaChiGiaoHang_KhachHang FOREIGN KEY (MaKH)
        REFERENCES KhachHang(MaKH) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE KhuyenMai (
    MaKM            VARCHAR(10)     NOT NULL,
    TenKM           NVARCHAR(150)   NOT NULL,
    LoaiGiam        ENUM('PhanTram','TienMat') NOT NULL,
    MucGiam         DECIMAL(12,0)   NOT NULL,
    NgayBatDau      DATE            NOT NULL,
    NgayKetThuc     DATE            NOT NULL,
    TrangThai       ENUM('ConHieuLuc','HetHan') NOT NULL DEFAULT 'ConHieuLuc',
    CONSTRAINT PK_KhuyenMai PRIMARY KEY (MaKM),
    CONSTRAINT CK_KhuyenMai_MucGiam CHECK (MucGiam > 0),
    CONSTRAINT CK_KhuyenMai_NgayApDung CHECK (NgayKetThuc > NgayBatDau)
) ENGINE=InnoDB;

CREATE TABLE GioHang (
    MaGioHang       VARCHAR(10)     NOT NULL,
    MaKH            VARCHAR(10)     NOT NULL,
    NgayTao         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_GioHang PRIMARY KEY (MaGioHang),
    CONSTRAINT FK_GioHang_KhachHang FOREIGN KEY (MaKH)
        REFERENCES KhachHang(MaKH) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT UQ_GioHang_KH UNIQUE (MaKH)
) ENGINE=InnoDB;

CREATE TABLE ChiTietGioHang (
    MaCTGH          VARCHAR(10)     NOT NULL,
    MaGioHang       VARCHAR(10)     NOT NULL,
    MaMon           VARCHAR(10)     NOT NULL,
    MaTuyChon       VARCHAR(10)     NULL,
    SoLuong         INT             NOT NULL DEFAULT 1,
    GhiChu          NVARCHAR(255)   NULL,
    CONSTRAINT PK_ChiTietGioHang PRIMARY KEY (MaCTGH),
    CONSTRAINT FK_ChiTietGioHang_GioHang FOREIGN KEY (MaGioHang)
        REFERENCES GioHang(MaGioHang) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_ChiTietGioHang_MonAn FOREIGN KEY (MaMon)
        REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_ChiTietGioHang_TuyChon FOREIGN KEY (MaTuyChon)
        REFERENCES TuyChonMon(MaTuyChon) ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT CK_ChiTietGioHang_SoLuong CHECK (SoLuong > 0)
) ENGINE=InnoDB;

CREATE TABLE DonHang (
    MaDH            VARCHAR(10)     NOT NULL,
    MaKH            VARCHAR(10)     NOT NULL,
    MaNVXuLy        VARCHAR(10)     NULL,
    MaKM            VARCHAR(10)     NULL,
    NgayDat         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    HinhThucNhan    ENUM('GiaoTanNoi','TuDenLay') NOT NULL,
    DiaChiGiao      NVARCHAR(255)   NULL,
    PhuongThucTT    ENUM('TienMat','ViDienTu','ChuyenKhoan') NOT NULL,
    PhiGiaoHang     DECIMAL(12,0)   NOT NULL DEFAULT 0,
    TongTien        DECIMAL(12,0)   NOT NULL,
    TrangThai       ENUM(
                        'ChoXacNhan','DangChuanBi','DangGiao',
                        'HoanTat','DaHuy'
                    ) NOT NULL DEFAULT 'ChoXacNhan',
    CONSTRAINT PK_DonHang PRIMARY KEY (MaDH),
    CONSTRAINT FK_DonHang_KhachHang FOREIGN KEY (MaKH)
        REFERENCES KhachHang(MaKH) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_DonHang_NhanVien FOREIGN KEY (MaNVXuLy)
        REFERENCES NhanVien(MaNV) ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT FK_DonHang_KhuyenMai FOREIGN KEY (MaKM)
        REFERENCES KhuyenMai(MaKM) ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT CK_DonHang_TongTien CHECK (TongTien >= 0),
    CONSTRAINT CK_DonHang_PhiGiaoHang CHECK (PhiGiaoHang >= 0),
    CONSTRAINT CK_DonHang_DiaChiGiao CHECK (
        (HinhThucNhan = 'GiaoTanNoi' AND DiaChiGiao IS NOT NULL)
        OR (HinhThucNhan = 'TuDenLay')
    )
) ENGINE=InnoDB;

CREATE TABLE ChiTietDonHang (
    MaCTDH          VARCHAR(10)     NOT NULL,
    MaDH            VARCHAR(10)     NOT NULL,
    MaMon           VARCHAR(10)     NOT NULL,
    TenTuyChon      NVARCHAR(255)   NULL,
    SoLuong         INT             NOT NULL,
    DonGia          DECIMAL(12,0)   NOT NULL,
    ThanhTien       DECIMAL(12,0)   NOT NULL,
    CONSTRAINT PK_ChiTietDonHang PRIMARY KEY (MaCTDH),
    CONSTRAINT FK_ChiTietDonHang_DonHang FOREIGN KEY (MaDH)
        REFERENCES DonHang(MaDH) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_ChiTietDonHang_MonAn FOREIGN KEY (MaMon)
        REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT CK_ChiTietDonHang_SoLuong CHECK (SoLuong > 0),
    CONSTRAINT CK_ChiTietDonHang_DonGia CHECK (DonGia > 0),
    CONSTRAINT CK_ChiTietDonHang_ThanhTien CHECK (ThanhTien = SoLuong * DonGia)
) ENGINE=InnoDB;

CREATE TABLE LichSuTrangThaiDonHang (
    MaLS            VARCHAR(10)     NOT NULL,
    MaDH            VARCHAR(10)     NOT NULL,
    TrangThai       ENUM(
                        'ChoXacNhan','DangChuanBi','DangGiao',
                        'HoanTat','DaHuy'
                    ) NOT NULL,
    ThoiGianCapNhat DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    MaNV            VARCHAR(10)     NULL,
    CONSTRAINT PK_LichSuTrangThaiDonHang PRIMARY KEY (MaLS),
    CONSTRAINT FK_LichSu_DonHang FOREIGN KEY (MaDH)
        REFERENCES DonHang(MaDH) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_LichSu_NhanVien FOREIGN KEY (MaNV)
        REFERENCES NhanVien(MaNV) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE INDEX IDX_MonAn_TenMon        ON MonAn(TenMon);
CREATE INDEX IDX_MonAn_MaDM          ON MonAn(MaDM);
CREATE INDEX IDX_DonHang_MaKH        ON DonHang(MaKH);
CREATE INDEX IDX_DonHang_NgayDat     ON DonHang(NgayDat);
CREATE INDEX IDX_DonHang_TrangThai   ON DonHang(TrangThai);
CREATE INDEX IDX_ChiTietDonHang_MaMon ON ChiTietDonHang(MaMon);
CREATE INDEX IDX_KhachHang_HoTen     ON KhachHang(HoTen);
CREATE INDEX IDX_NhanVien_TenNV      ON NhanVien(TenNV);

DELIMITER $$

CREATE TRIGGER TRG_ChiTietDonHang_TinhLaiTongTien
AFTER INSERT ON ChiTietDonHang
FOR EACH ROW
BEGIN
    UPDATE DonHang
    SET TongTien = (
        SELECT COALESCE(SUM(ThanhTien),0) FROM ChiTietDonHang WHERE MaDH = NEW.MaDH
    ) + PhiGiaoHang
    WHERE MaDH = NEW.MaDH;
END$$

DELIMITER ;
