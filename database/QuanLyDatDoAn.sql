-- ========================================================
-- CƠ SỞ DỮ LIỆU: QuanLyDatDoAn (CRAVE FOOD & BEVERAGE)
-- Hệ quản trị CSDL: MySQL
-- ========================================================

DROP DATABASE IF EXISTS QuanLyDatDoAn;
CREATE DATABASE QuanLyDatDoAn
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;
USE QuanLyDatDoAn;

-- 1. BẢNG KHÁCH HÀNG
CREATE TABLE KhachHang (
 MaKH VARCHAR(10) NOT NULL,
 HoTen NVARCHAR(100) NOT NULL,
 SoDienThoai VARCHAR(15) NOT NULL,
 Email VARCHAR(100) NULL,
 MatKhau VARCHAR(255) NOT NULL,
 DiaChiMacDinh NVARCHAR(255) NULL,
 TrangThai ENUM('HoatDong','DaKhoa') NOT NULL DEFAULT 'HoatDong',
 NgayDangKy DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT PK_KhachHang PRIMARY KEY (MaKH),
 CONSTRAINT UQ_KhachHang_SDT UNIQUE (SoDienThoai),
 CONSTRAINT UQ_KhachHang_Email UNIQUE (Email)
) ENGINE=InnoDB;

-- 2. BẢNG NHÂN VIÊN
CREATE TABLE NhanVien (
 MaNV VARCHAR(10) NOT NULL,
 TenNV NVARCHAR(100) NOT NULL,
 ChucVu ENUM(
 'QuanTriVien',
 'NhanVienQuanLiMon',
 'NhanVienXuLyDonHang',
 'NhanVienQuanLiKhuyenMai',
 'NhanVienQuanLiNhanSu'
 ) NOT NULL,
 DiaChi NVARCHAR(255) NULL,
 SoDienThoai VARCHAR(15) NOT NULL,
 Email VARCHAR(100) NOT NULL,
 MatKhau VARCHAR(255) NOT NULL,
 NgayVaoLam DATE NOT NULL,
 TrangThai ENUM('DangLamViec','DaNghiViec') NOT NULL DEFAULT 'DangLamViec',
 CONSTRAINT PK_NhanVien PRIMARY KEY (MaNV),
 CONSTRAINT UQ_NhanVien_SDT UNIQUE (SoDienThoai),
 CONSTRAINT UQ_NhanVien_Email UNIQUE (Email)
) ENGINE=InnoDB;

-- 3. BẢNG DANH MỤC
CREATE TABLE DanhMuc (
 MaDM VARCHAR(10) NOT NULL,
 TenDM NVARCHAR(100) NOT NULL,
 MoTa NVARCHAR(255) NULL,
 CONSTRAINT PK_DanhMuc PRIMARY KEY (MaDM),
 CONSTRAINT UQ_DanhMuc_Ten UNIQUE (TenDM)
) ENGINE=InnoDB;

-- 4. BẢNG MÓN ĂN
CREATE TABLE MonAn (
 MaMon VARCHAR(10) NOT NULL,
 TenMon NVARCHAR(150) NOT NULL,
 MaDM VARCHAR(10) NOT NULL,
 GiaBan DECIMAL(12,0) NOT NULL,
 HinhAnh VARCHAR(255) NULL,
 MoTa NVARCHAR(500) NULL,
 TrangThai ENUM('DangBan','NgungBan') NOT NULL DEFAULT 'DangBan',
 CONSTRAINT PK_MonAn PRIMARY KEY (MaMon),
 CONSTRAINT FK_MonAn_DanhMuc FOREIGN KEY (MaDM)
 REFERENCES DanhMuc(MaDM) ON UPDATE CASCADE ON DELETE RESTRICT,
 CONSTRAINT CK_MonAn_GiaBan CHECK (GiaBan > 0)
) ENGINE=InnoDB;

-- 5. BẢNG TÙY CHỌN MÓN
CREATE TABLE TuyChonMon (
 MaTuyChon VARCHAR(10) NOT NULL,
 MaMon VARCHAR(10) NOT NULL,
 LoaiTuyChon ENUM('Size','Topping','MucDuong','MucDa','Khac') NOT NULL,
 TenTuyChon NVARCHAR(100) NOT NULL,
 GiaThem DECIMAL(12,0) NOT NULL DEFAULT 0,
 CONSTRAINT PK_TuyChonMon PRIMARY KEY (MaTuyChon),
 CONSTRAINT FK_TuyChonMon_MonAn FOREIGN KEY (MaMon)
 REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT CK_TuyChonMon_GiaThem CHECK (GiaThem >= 0)
) ENGINE=InnoDB;

-- 6. BẢNG ĐỊA CHỈ GIAO HÀNG
CREATE TABLE DiaChiGiaoHang (
 MaDiaChi VARCHAR(10) NOT NULL,
 MaKH VARCHAR(10) NOT NULL,
 DiaChiChiTiet NVARCHAR(255) NOT NULL,
 GhiChu NVARCHAR(255) NULL,
 MacDinh TINYINT(1) NOT NULL DEFAULT 0,
 CONSTRAINT PK_DiaChiGiaoHang PRIMARY KEY (MaDiaChi),
 CONSTRAINT FK_DiaChiGiaoHang_KhachHang FOREIGN KEY (MaKH)
 REFERENCES KhachHang(MaKH) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

-- 7. BẢNG KHUYẾN MÃI
CREATE TABLE KhuyenMai (
 MaKM VARCHAR(10) NOT NULL,
 TenKM NVARCHAR(150) NOT NULL,
 LoaiGiam ENUM('PhanTram','TienMat') NOT NULL,
 MucGiam DECIMAL(12,0) NOT NULL,
 NgayBatDau DATE NOT NULL,
 NgayKetThuc DATE NOT NULL,
 TrangThai ENUM('ConHieuLuc','HetHan') NOT NULL DEFAULT 'ConHieuLuc',
 CONSTRAINT PK_KhuyenMai PRIMARY KEY (MaKM),
 CONSTRAINT CK_KhuyenMai_MucGiam CHECK (MucGiam > 0),
 CONSTRAINT CK_KhuyenMai_NgayApDung CHECK (NgayKetThuc > NgayBatDau)
) ENGINE=InnoDB;

-- 8. BẢNG GIỎ HÀNG
CREATE TABLE GioHang (
 MaGioHang VARCHAR(10) NOT NULL,
 MaKH VARCHAR(10) NOT NULL,
 NgayTao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT PK_GioHang PRIMARY KEY (MaGioHang),
 CONSTRAINT FK_GioHang_KhachHang FOREIGN KEY (MaKH)
 REFERENCES KhachHang(MaKH) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT UQ_GioHang_KH UNIQUE (MaKH)
) ENGINE=InnoDB;

-- 9. BẢNG CHI TIẾT GIỎ HÀNG
CREATE TABLE ChiTietGioHang (
 MaCTGH VARCHAR(10) NOT NULL,
 MaGioHang VARCHAR(10) NOT NULL,
 MaMon VARCHAR(10) NOT NULL,
 MaTuyChon VARCHAR(10) NULL,
 SoLuong INT NOT NULL DEFAULT 1,
 GhiChu NVARCHAR(255) NULL,
 CONSTRAINT PK_ChiTietGioHang PRIMARY KEY (MaCTGH),
 CONSTRAINT FK_ChiTietGioHang_GioHang FOREIGN KEY (MaGioHang)
 REFERENCES GioHang(MaGioHang) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT FK_ChiTietGioHang_MonAn FOREIGN KEY (MaMon)
 REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE RESTRICT,
 CONSTRAINT FK_ChiTietGioHang_TuyChon FOREIGN KEY (MaTuyChon)
 REFERENCES TuyChonMon(MaTuyChon) ON UPDATE CASCADE ON DELETE SET NULL,
 CONSTRAINT CK_ChiTietGioHang_SoLuong CHECK (SoLuong > 0)
) ENGINE=InnoDB;

-- 10. BẢNG ĐƠN HÀNG
CREATE TABLE DonHang (
 MaDH VARCHAR(10) NOT NULL,
 MaKH VARCHAR(10) NOT NULL,
 MaNVXuLy VARCHAR(10) NULL,
 MaKM VARCHAR(10) NULL,
 NgayDat DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 HinhThucNhan ENUM('GiaoTanNoi','TuDenLay') NOT NULL,
 DiaChiGiao NVARCHAR(255) NULL,
 PhuongThucTT ENUM('TienMat','ViDienTu','ChuyenKhoan') NOT NULL,
 PhiGiaoHang DECIMAL(12,0) NOT NULL DEFAULT 0,
 TongTien DECIMAL(12,0) NOT NULL,
 TrangThai ENUM(
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

-- 11. BẢNG CHI TIẾT ĐƠN HÀNG
CREATE TABLE ChiTietDonHang (
 MaCTDH VARCHAR(10) NOT NULL,
 MaDH VARCHAR(10) NOT NULL,
 MaMon VARCHAR(10) NOT NULL,
 TenTuyChon NVARCHAR(255) NULL,
 SoLuong INT NOT NULL,
 DonGia DECIMAL(12,0) NOT NULL,
 ThanhTien DECIMAL(12,0) NOT NULL,
 CONSTRAINT PK_ChiTietDonHang PRIMARY KEY (MaCTDH),
 CONSTRAINT FK_ChiTietDonHang_DonHang FOREIGN KEY (MaDH)
 REFERENCES DonHang(MaDH) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT FK_ChiTietDonHang_MonAn FOREIGN KEY (MaMon)
 REFERENCES MonAn(MaMon) ON UPDATE CASCADE ON DELETE RESTRICT,
 CONSTRAINT CK_ChiTietDonHang_SoLuong CHECK (SoLuong > 0),
 CONSTRAINT CK_ChiTietDonHang_DonGia CHECK (DonGia > 0),
 CONSTRAINT CK_ChiTietDonHang_ThanhTien CHECK (ThanhTien = SoLuong * DonGia)
) ENGINE=InnoDB;

-- 12. BẢNG LỊCH SỬ TRẠNG THÁI ĐƠN HÀNG
CREATE TABLE LichSuTrangThaiDonHang (
 MaLS VARCHAR(10) NOT NULL,
 MaDH VARCHAR(10) NOT NULL,
 TrangThai ENUM(
 'ChoXacNhan','DangChuanBi','DangGiao',
 'HoanTat','DaHuy'
 ) NOT NULL,
 ThoiGianCapNhat DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 MaNV VARCHAR(10) NULL,
 CONSTRAINT PK_LichSuTrangThaiDonHang PRIMARY KEY (MaLS),
 CONSTRAINT FK_LichSu_DonHang FOREIGN KEY (MaDH)
 REFERENCES DonHang(MaDH) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT FK_LichSu_NhanVien FOREIGN KEY (MaNV)
 REFERENCES NhanVien(MaNV) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

-- CHỈ MỤC (INDEXES)
CREATE INDEX IDX_MonAn_TenMon ON MonAn(TenMon);
CREATE INDEX IDX_MonAn_MaDM ON MonAn(MaDM);
CREATE INDEX IDX_DonHang_MaKH ON DonHang(MaKH);
CREATE INDEX IDX_DonHang_NgayDat ON DonHang(NgayDat);
CREATE INDEX IDX_DonHang_TrangThai ON DonHang(TrangThai);
CREATE INDEX IDX_ChiTietDonHang_MaMon ON ChiTietDonHang(MaMon);
CREATE INDEX IDX_KhachHang_HoTen ON KhachHang(HoTen);
CREATE INDEX IDX_NhanVien_TenNV ON NhanVien(TenNV);

-- TRIGGER TÍNH LẠI TỔNG TIỀN ĐƠN HÀNG KHI THÊM CHI TIẾT
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

-- ========================================================
-- DỮ LIỆU MẪU (SEED DATA)
-- ========================================================

-- DANH MỤC
INSERT INTO DanhMuc (MaDM, TenDM, MoTa) VALUES
('DM01', N'Món chính', N'Các món cơm, mì, bún...'),
('DM02', N'Nước uống', N'Trà sữa, nước ép, cà phê...'),
('DM03', N'Tráng miệng', N'Bánh ngọt, chè, kem...'),
('DM04', N'Ăn vặt', N'Khoai tây chiên, gà rán, xúc xích nướng...'),
('DM05', N'Lẩu - Nướng', N'Các món lẩu, nướng phục vụ nhóm đông người'),
('DM06', N'Đồ chay', N'Món ăn chay thanh đạm, tốt cho sức khỏe');

-- MÓN ĂN
INSERT INTO MonAn (MaMon, TenMon, MaDM, GiaBan, HinhAnh, MoTa, TrangThai) VALUES
('MA01', N'Cơm gà xối mỡ', 'DM01', 45000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789654803/2023_12_6_638374928096209198_com-ga-xoi-mo-bao-nhieu-calo.webp', N'Cơm gà giòn, nước mắm chua ngọt', 'DangBan'),
('MA02', N'Trà sữa trân châu', 'DM02', 35000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789655114/cach-lam-tra-sua-chan-trau-thom-ngon-tai-nha.jpg', N'Trà sữa truyền thống', 'DangBan'),
('MA03', N'Bánh flan', 'DM03', 15000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789655151/cach-lam-banh-flan-thumbnail.jpg', N'Bánh flan caramel', 'DangBan'),
('MA04', N'Phở bò tái', 'DM01', 50000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652157/crave/MA04_pho_bo_tai.jpg', N'Phở bò truyền thống, nước dùng ninh xương 8 tiếng', 'DangBan'),
('MA05', N'Bún chả Hà Nội', 'DM01', 48000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657008/2024_1_12_638406880045931692_cach-lam-bun-cha-ha-noi-0.webp', N'Chả nướng than hoa ăn kèm bún và nước chấm', 'DangBan'),
('MA06', N'Mì xào hải sản', 'DM01', 55000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657194/OIP.webp', N'Mì xào giòn cùng tôm, mực, rau củ', 'DangBan'),
('MA07', N'Cơm tấm sườn bì chả', 'DM01', 42000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789656913/com-tam-suon-bi-cha-chay-1_99356b3b594740f793b4d570925b0572.jpg', N'Cơm tấm sườn nướng, bì, chả trứng', 'DangBan'),
('MA08', N'Bún bò Huế', 'DM01', 47000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789655329/nau-bun-bo-hue-chuan-vi-tai-nha-voi-cot-co-dac-quoc-viet-foods_59b7ba1543004e67967af718d8afc32b.webp', N'Đậm vị cay nồng đặc trưng xứ Huế', 'DangBan'),
('MA09', N'Cà phê sữa đá', 'DM02', 25000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657259/OIP.webp', N'Cà phê phin truyền thống pha sữa đặc', 'DangBan'),
('MA10', N'Nước ép cam', 'DM02', 30000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652163/crave/MA10_nuoc_ep_cam.jpg', N'Cam tươi ép nguyên chất, không đường hóa học', 'DangBan'),
('MA11', N'Sinh tố bơ', 'DM02', 32000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657333/OIP.webp', N'Sinh tố bơ sáp béo ngậy', 'DangBan'),
('MA12', N'Trà đào cam sả', 'DM02', 38000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652165/crave/MA12_tra_dao_cam_sa.jpg', N'Trà đào thơm mát kèm cam và sả', 'DangBan'),
('MA13', N'Nước chanh muối', 'DM02', 20000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652166/crave/MA13_nuoc_chanh_muoi.jpg', N'Giải khát chua nhẹ, thanh lọc', 'DangBan'),
('MA14', N'Chè khúc bạch', 'DM03', 25000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789655493/che-khuc-bach-003-20220606.jpg', N'Khúc bạch, hạnh nhân, nhãn nhục', 'DangBan'),
('MA15', N'Kem dừa', 'DM03', 20000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652169/crave/MA15_kem_dua.jpg', N'Kem vị dừa mát lạnh phục vụ trong trái dừa', 'DangBan'),
('MA16', N'Bánh su kem', 'DM03', 12000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652170/crave/MA16_banh_su_kem.jpg', N'Vỏ bánh giòn, nhân kem trứng béo mịn', 'DangBan'),
('MA17', N'Khoai tây chiên', 'DM04', 28000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652171/crave/MA17_khoai_tay_chien.jpg', N'Khoai tây chiên giòn ăn kèm tương ớt/mayonnaise', 'DangBan'),
('MA18', N'Gà rán giòn', 'DM04', 45000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652172/crave/MA18_ga_ran_gion.jpg', N'Gà rán tẩm bột giòn rụm, 2 miếng', 'DangBan'),
('MA19', N'Xúc xích nướng', 'DM04', 20000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657405/OIP.webp', N'Xúc xích nướng than, 3 cây', 'DangBan'),
('MA20', N'Lẩu thái hải sản', 'DM05', 180000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657457/202311280950003415.webp', N'Lẩu chua cay hải sản, phục vụ 2-3 người', 'DangBan'),
('MA21', N'Nướng BBQ thập cẩm', 'DM05', 220000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789652174/crave/MA21_nuong_bbq_thap_cam.jpg', N'Set nướng thịt bò, heo, gà, hải sản', 'DangBan'),
('MA22', N'Đậu hũ sốt cà', 'DM06', 30000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657511/OIP.webp', N'Đậu hũ chiên sốt cà chua kiểu chay', 'DangBan'),
('MA23', N'Rau củ xào chay', 'DM06', 28000, 'https://res.cloudinary.com/mkx3r87x/image/upload/v1789657565/OIP.webp', N'Rau củ theo mùa xào tỏi kiểu chay', 'DangBan');

-- TÙY CHỌN MÓN
INSERT INTO TuyChonMon (MaTuyChon, MaMon, LoaiTuyChon, TenTuyChon, GiaThem) VALUES
('TC01', 'MA02', 'Size', N'Size L', 5000),
('TC02', 'MA02', 'MucDuong', N'50% đường', 0),
('TC03', 'MA01', 'Topping', N'Thêm trứng ốp la', 8000),
('TC04', 'MA04', 'Size', N'Tô lớn', 10000),
('TC05', 'MA04', 'Topping', N'Thêm bò viên', 10000),
('TC06', 'MA09', 'MucDa', N'Ít đá', 0),
('TC07', 'MA09', 'Size', N'Size L', 5000),
('TC08', 'MA10', 'Size', N'Size L', 5000),
('TC09', 'MA11', 'Topping', N'Thêm sữa đặc', 3000),
('TC10', 'MA12', 'Size', N'Size L', 5000),
('TC11', 'MA18', 'Khac', N'Sốt phô mai', 5000),
('TC12', 'MA20', 'Topping', N'Thêm hải sản', 50000);

-- NHÂN VIÊN
INSERT INTO NhanVien (MaNV, TenNV, ChucVu, DiaChi, SoDienThoai, Email, MatKhau, NgayVaoLam) VALUES
('NV01', N'Nguyễn Văn Quản Trị', 'QuanTriVien', N'10 Lê Lợi, Q.1, TP.HCM', '0900000001', 'admin@shop.vn', 'hashed_pw', '2025-01-01'),
('NV02', N'Lê Thị Quản Món', 'NhanVienQuanLiMon', N'20 Lê Lợi, Q.1, TP.HCM', '0900000010', 'lethiquanmon@shop.vn', 'hashed_pw', '2025-02-15'),
('NV03', N'Phạm Văn Xử Lý', 'NhanVienXuLyDonHang', N'15 Cách Mạng Tháng 8, Q.3, TP.HCM', '0900000011', 'phamvanxuly@shop.vn', 'hashed_pw', '2025-03-01'),
('NV04', N'Hoàng Thị Xử Lý Hai', 'NhanVienXuLyDonHang', N'88 Nguyễn Thị Minh Khai, Q.3, TP.HCM', '0900000012', 'hoangxulyhai@shop.vn', 'hashed_pw', '2025-04-10'),
('NV05', N'Đỗ Văn Khuyến Mãi', 'NhanVienQuanLiKhuyenMai', N'5 Điện Biên Phủ, Bình Thạnh, TP.HCM', '0900000013', 'dovankm@shop.vn', 'hashed_pw', '2025-05-20'),
('NV06', N'Vũ Thị Nhân Sự', 'NhanVienQuanLiNhanSu', N'12 Phan Xích Long, Phú Nhuận, TP.HCM', '0900000014', 'vuthins@shop.vn', 'hashed_pw', '2025-06-01'),
('NV07', N'Ngô Văn Giao Hàng', 'NhanVienXuLyDonHang', N'30 Trường Chinh, Tân Bình, TP.HCM', '0900000015', 'ngovangiao@shop.vn', 'hashed_pw', '2025-07-18'),
('NV08', N'Bùi Thị Quản Món Hai', 'NhanVienQuanLiMon', N'9 Hoàng Văn Thụ, Tân Bình, TP.HCM', '0900000016', 'buiquanmonhai@shop.vn', 'hashed_pw', '2025-08-05');

-- KHÁCH HÀNG
INSERT INTO KhachHang (MaKH, HoTen, SoDienThoai, Email, MatKhau, DiaChiMacDinh) VALUES
('KH01', N'Trần Thị Khách', '0900000002', 'khachhang@gmail.com', 'hashed_pw', N'12 Nguyễn Trãi, Q.1, TP.HCM'),
('KH02', N'Nguyễn Văn An', '0911111111', 'an.nguyen@gmail.com', 'hashed_pw', N'25 Lý Tự Trọng, Q.1, TP.HCM'),
('KH03', N'Trần Thị Bình', '0911111112', 'binh.tran@gmail.com', 'hashed_pw', N'40 Nguyễn Huệ, Q.1, TP.HCM'),
('KH04', N'Lê Văn Cường', '0911111113', 'cuong.le@gmail.com', 'hashed_pw', N'12 Võ Văn Tần, Q.3, TP.HCM'),
('KH05', N'Phạm Thị Dung', '0911111114', 'dung.pham@gmail.com', 'hashed_pw', N'7 Lê Văn Sỹ, Q.3, TP.HCM'),
('KH06', N'Hoàng Văn Em', '0911111115', 'em.hoang@gmail.com', 'hashed_pw', N'56 Xô Viết Nghệ Tĩnh, Bình Thạnh, TP.HCM'),
('KH07', N'Đỗ Thị Phương', '0911111116', 'phuong.do@gmail.com', 'hashed_pw', N'18 Phan Đăng Lưu, Phú Nhuận, TP.HCM'),
('KH08', N'Vũ Văn Giang', '0911111117', 'giang.vu@gmail.com', 'hashed_pw', N'99 Cộng Hòa, Tân Bình, TP.HCM'),
('KH09', N'Ngô Thị Hoa', '0911111118', 'hoa.ngo@gmail.com', 'hashed_pw', N'3 Trường Sơn, Tân Bình, TP.HCM'),
('KH10', N'Bùi Văn Inh', '0911111119', 'inh.bui@gmail.com', 'hashed_pw', N'21 Nguyễn Oanh, Gò Vấp, TP.HCM'),
('KH11', N'Đặng Thị Kim', '0911111120', 'kim.dang@gmail.com', 'hashed_pw', N'44 Quang Trung, Gò Vấp, TP.HCM'),
('KH12', N'Trịnh Văn Long', '0911111121', 'long.trinh@gmail.com', 'hashed_pw', N'8 Kha Vạn Cân, Thủ Đức, TP.HCM'),
('KH13', N'Lý Thị Mai', '0911111122', 'mai.ly@gmail.com', 'hashed_pw', N'67 Võ Văn Ngân, Thủ Đức, TP.HCM'),
('KH14', N'Phan Văn Nam', '0911111123', 'nam.phan@gmail.com', 'hashed_pw', N'11 Đỗ Xuân Hợp, Q.9, TP.HCM'),
('KH15', N'Tô Thị Oanh', '0911111124', 'oanh.to@gmail.com', 'hashed_pw', N'29 Nguyễn Duy Trinh, Q.2, TP.HCM');

-- ĐỊA CHỈ GIAO HÀNG
INSERT INTO DiaChiGiaoHang (MaDiaChi, MaKH, DiaChiChiTiet, GhiChu, MacDinh) VALUES
('DC01', 'KH01', N'12 Nguyễn Trãi, Q.1, TP.HCM', N'Nhà mặt tiền, cổng xanh', 1),
('DC02', 'KH01', N'Công ty ABC, 88 Lê Duẩn, Q.1, TP.HCM', N'Giao tại quầy lễ tân tầng 1', 0),
('DC03', 'KH02', N'25 Lý Tự Trọng, Q.1, TP.HCM', N'Gọi trước khi đến', 1),
('DC04', 'KH03', N'40 Nguyễn Huệ, Q.1, TP.HCM', NULL, 1),
('DC05', 'KH04', N'12 Võ Văn Tần, Q.3, TP.HCM', N'Chung cư, tầng 5', 1),
('DC06', 'KH06', N'56 Xô Viết Nghệ Tĩnh, Bình Thạnh, TP.HCM', NULL, 1),
('DC07', 'KH07', N'18 Phan Đăng Lưu, Phú Nhuận, TP.HCM', N'Giao giờ hành chính', 1),
('DC08', 'KH09', N'3 Trường Sơn, Tân Bình, TP.HCM', NULL, 1),
('DC09', 'KH10', N'21 Nguyễn Oanh, Gò Vấp, TP.HCM', N'Hẻm nhỏ, gọi điện trước', 1),
('DC10', 'KH12', N'8 Kha Vạn Cân, Thủ Đức, TP.HCM', NULL, 1);

-- KHUYẾN MÃI
INSERT INTO KhuyenMai (MaKM, TenKM, LoaiGiam, MucGiam, NgayBatDau, NgayKetThuc, TrangThai) VALUES
('KM01', N'Giảm 10% đơn đầu tiên', 'PhanTram', 10, '2026-09-01', '2026-12-31', 'ConHieuLuc'),
('KM02', N'Giảm 20K cho đơn từ 100K', 'TienMat', 20000, '2026-09-01', '2026-10-31', 'ConHieuLuc'),
('KM03', N'Giảm 15% cuối tuần', 'PhanTram', 15, '2026-09-01', '2027-03-31', 'ConHieuLuc'),
('KM04', N'Giảm 50K cho đơn từ 300K', 'TienMat', 50000, '2026-10-01', '2026-12-31', 'ConHieuLuc'),
('KM05', N'Mừng khai trương giảm 30%', 'PhanTram', 30, '2026-01-01', '2026-09-30', 'HetHan'),
('KM06', N'Giảm 5% mọi đơn hàng', 'PhanTram', 5, '2026-09-01', '2027-12-31', 'ConHieuLuc');

-- GIỎ HÀNG
INSERT INTO GioHang (MaGioHang, MaKH) VALUES
('GH01', 'KH02'),
('GH02', 'KH03'),
('GH03', 'KH04'),
('GH04', 'KH05'),
('GH05', 'KH06');

-- CHI TIẾT GIỎ HÀNG
INSERT INTO ChiTietGioHang (MaCTGH, MaGioHang, MaMon, MaTuyChon, SoLuong, GhiChu) VALUES
('CTGH01', 'GH01', 'MA07', NULL, 1, N'Không hành'),
('CTGH02', 'GH01', 'MA09', 'TC07', 1, N'Ít đường'),
('CTGH03', 'GH02', 'MA04', 'TC04', 2, NULL),
('CTGH04', 'GH02', 'MA13', NULL, 2, NULL),
('CTGH05', 'GH03', 'MA20', 'TC12', 1, N'Cay vừa'),
('CTGH06', 'GH03', 'MA17', NULL, 1, NULL),
('CTGH07', 'GH04', 'MA22', NULL, 2, N'Đơn chay'),
('CTGH08', 'GH04', 'MA12', 'TC10', 1, NULL),
('CTGH09', 'GH05', 'MA05', NULL, 1, NULL),
('CTGH10', 'GH05', 'MA11', 'TC09', 1, NULL);

-- ĐƠN HÀNG
INSERT INTO DonHang (MaDH, MaKH, MaNVXuLy, MaKM, NgayDat, HinhThucNhan, DiaChiGiao, PhuongThucTT, PhiGiaoHang, TongTien, TrangThai) VALUES
('DH01', 'KH01', 'NV03', 'KM01', '2026-09-01 10:00:00', 'GiaoTanNoi', N'12 Nguyễn Trãi, Q.1, TP.HCM', 'ViDienTu', 15000, 0, 'HoanTat'),
('DH02', 'KH02', 'NV03', NULL, '2026-09-02 11:30:00', 'TuDenLay', NULL, 'TienMat', 0, 0, 'HoanTat'),
('DH03', 'KH03', 'NV04', 'KM02', '2026-09-03 12:15:00', 'GiaoTanNoi', N'40 Nguyễn Huệ, Q.1, TP.HCM', 'ChuyenKhoan', 15000, 0, 'DangGiao'),
('DH04', 'KH04', 'NV04', NULL, '2026-09-04 18:45:00', 'GiaoTanNoi', N'12 Võ Văn Tần, Q.3, TP.HCM', 'TienMat', 20000, 0, 'DangChuanBi'),
('DH05', 'KH05', 'NV03', 'KM03', '2026-09-05 19:00:00', 'TuDenLay', NULL, 'ViDienTu', 0, 0, 'ChoXacNhan'),
('DH06', 'KH06', 'NV04', NULL, '2026-09-06 09:20:00', 'GiaoTanNoi', N'56 Xô Viết Nghệ Tĩnh, Bình Thạnh, TP.HCM', 'ChuyenKhoan', 15000, 0, 'HoanTat'),
('DH07', 'KH07', 'NV03', 'KM04', '2026-09-07 20:10:00', 'GiaoTanNoi', N'18 Phan Đăng Lưu, Phú Nhuận, TP.HCM', 'TienMat', 20000, 0, 'HoanTat'),
('DH08', 'KH08', 'NV04', NULL, '2026-09-07 21:00:00', 'TuDenLay', NULL, 'ViDienTu', 0, 0, 'DaHuy'),
('DH09', 'KH09', 'NV03', 'KM06', '2026-09-08 12:40:00', 'GiaoTanNoi', N'3 Trường Sơn, Tân Bình, TP.HCM', 'ChuyenKhoan', 15000, 0, 'HoanTat'),
('DH10', 'KH10', 'NV04', NULL, '2026-09-08 17:05:00', 'GiaoTanNoi', N'21 Nguyễn Oanh, Gò Vấp, TP.HCM', 'TienMat', 15000, 0, 'DangGiao'),
('DH11', 'KH11', 'NV03', NULL, '2026-09-09 08:30:00', 'TuDenLay', NULL, 'ViDienTu', 0, 0, 'HoanTat'),
('DH12', 'KH12', 'NV04', 'KM01', '2026-09-09 13:25:00', 'GiaoTanNoi', N'8 Kha Vạn Cân, Thủ Đức, TP.HCM', 'ChuyenKhoan', 20000, 0, 'HoanTat'),
('DH13', 'KH13', 'NV03', NULL, '2026-09-10 19:50:00', 'GiaoTanNoi', N'67 Võ Văn Ngân, Thủ Đức, TP.HCM', 'TienMat', 15000, 0, 'DangChuanBi'),
('DH14', 'KH14', 'NV04', 'KM06', '2026-09-11 11:10:00', 'TuDenLay', NULL, 'ViDienTu', 0, 0, 'ChoXacNhan'),
('DH15', 'KH15', 'NV03', NULL, '2026-09-11 20:35:00', 'GiaoTanNoi', N'29 Nguyễn Duy Trinh, Q.2, TP.HCM', 'ChuyenKhoan', 15000, 0, 'HoanTat');

-- CHI TIẾT ĐƠN HÀNG
INSERT INTO ChiTietDonHang (MaCTDH, MaDH, MaMon, TenTuyChon, SoLuong, DonGia, ThanhTien) VALUES
('CTDH01', 'DH01', 'MA01', N'Thêm trứng ốp la', 2, 45000, 90000),
('CTDH02', 'DH01', 'MA02', N'Size L, 50% đường', 1, 35000, 35000),
('CTDH03', 'DH02', 'MA07', NULL, 1, 42000, 42000),
('CTDH04', 'DH02', 'MA09', NULL, 1, 25000, 25000),
('CTDH05', 'DH03', 'MA04', N'Tô lớn', 2, 50000, 100000),
('CTDH06', 'DH03', 'MA13', NULL, 2, 20000, 40000),
('CTDH07', 'DH04', 'MA20', N'Thêm hải sản', 1, 180000, 180000),
('CTDH08', 'DH04', 'MA17', NULL, 1, 28000, 28000),
('CTDH09', 'DH05', 'MA22', NULL, 1, 30000, 30000),
('CTDH10', 'DH05', 'MA23', NULL, 1, 28000, 28000),
('CTDH11', 'DH06', 'MA05', NULL, 2, 48000, 96000),
('CTDH12', 'DH06', 'MA12', N'Size L', 2, 38000, 76000),
('CTDH13', 'DH07', 'MA21', NULL, 1, 220000, 220000),
('CTDH14', 'DH07', 'MA10', NULL, 2, 30000, 60000),
('CTDH15', 'DH08', 'MA18', N'Sốt phô mai', 1, 45000, 45000),
('CTDH16', 'DH09', 'MA06', NULL, 1, 55000, 55000),
('CTDH17', 'DH09', 'MA14', NULL, 2, 25000, 50000),
('CTDH18', 'DH10', 'MA08', NULL, 2, 47000, 94000),
('CTDH19', 'DH10', 'MA11', NULL, 1, 32000, 32000),
('CTDH20', 'DH11', 'MA03', NULL, 3, 15000, 45000),
('CTDH21', 'DH11', 'MA16', NULL, 2, 12000, 24000),
('CTDH22', 'DH12', 'MA01', NULL, 1, 45000, 45000),
('CTDH23', 'DH12', 'MA02', NULL, 1, 35000, 35000),
('CTDH24', 'DH13', 'MA19', NULL, 3, 20000, 60000),
('CTDH25', 'DH13', 'MA15', NULL, 1, 20000, 20000),
('CTDH26', 'DH14', 'MA04', NULL, 1, 50000, 50000),
('CTDH27', 'DH15', 'MA20', NULL, 1, 180000, 180000),
('CTDH28', 'DH15', 'MA13', NULL, 1, 20000, 20000);

-- LỊCH SỬ TRẠNG THÁI ĐƠN HÀNG
INSERT INTO LichSuTrangThaiDonHang (MaLS, MaDH, TrangThai, ThoiGianCapNhat, MaNV) VALUES
('LS01', 'DH01', 'ChoXacNhan', '2026-09-01 10:00:00', 'NV03'),
('LS02', 'DH01', 'DangChuanBi', '2026-09-01 10:15:00', 'NV02'),
('LS03', 'DH01', 'DangGiao', '2026-09-01 10:40:00', 'NV07'),
('LS04', 'DH01', 'HoanTat', '2026-09-01 11:10:00', 'NV07'),
('LS05', 'DH02', 'ChoXacNhan', '2026-09-02 11:30:00', 'NV03'),
('LS06', 'DH02', 'DangChuanBi', '2026-09-02 11:40:00', 'NV02'),
('LS07', 'DH02', 'HoanTat', '2026-09-02 12:05:00', 'NV03'),
('LS08', 'DH03', 'ChoXacNhan', '2026-09-03 12:15:00', 'NV04'),
('LS09', 'DH03', 'DangChuanBi', '2026-09-03 12:25:00', 'NV08'),
('LS10', 'DH03', 'DangGiao', '2026-09-03 12:50:00', 'NV07'),
('LS11', 'DH04', 'ChoXacNhan', '2026-09-04 18:45:00', 'NV04'),
('LS12', 'DH04', 'DangChuanBi', '2026-09-04 18:55:00', 'NV08'),
('LS13', 'DH05', 'ChoXacNhan', '2026-09-05 19:00:00', 'NV03'),
('LS14', 'DH06', 'ChoXacNhan', '2026-09-06 09:20:00', 'NV04'),
('LS15', 'DH06', 'DangChuanBi', '2026-09-06 09:30:00', 'NV02'),
('LS16', 'DH06', 'DangGiao', '2026-09-06 09:55:00', 'NV07'),
('LS17', 'DH06', 'HoanTat', '2026-09-06 10:30:00', 'NV07'),
('LS18', 'DH07', 'ChoXacNhan', '2026-09-07 20:10:00', 'NV03'),
('LS19', 'DH07', 'DangChuanBi', '2026-09-07 20:25:00', 'NV08'),
('LS20', 'DH07', 'DangGiao', '2026-09-07 20:50:00', 'NV07'),
('LS21', 'DH07', 'HoanTat', '2026-09-07 21:30:00', 'NV07'),
('LS22', 'DH08', 'ChoXacNhan', '2026-09-07 21:00:00', 'NV04'),
('LS23', 'DH08', 'DaHuy', '2026-09-07 21:10:00', 'NV04'),
('LS24', 'DH09', 'ChoXacNhan', '2026-09-08 12:40:00', 'NV03'),
('LS25', 'DH09', 'DangChuanBi', '2026-09-08 12:50:00', 'NV02'),
('LS26', 'DH09', 'DangGiao', '2026-09-08 13:15:00', 'NV07'),
('LS27', 'DH09', 'HoanTat', '2026-09-08 13:45:00', 'NV07'),
('LS28', 'DH10', 'ChoXacNhan', '2026-09-08 17:05:00', 'NV04'),
('LS29', 'DH10', 'DangChuanBi', '2026-09-08 17:15:00', 'NV08'),
('LS30', 'DH10', 'DangGiao', '2026-09-08 17:40:00', 'NV07'),
('LS31', 'DH11', 'ChoXacNhan', '2026-09-09 08:30:00', 'NV03'),
('LS32', 'DH11', 'DangChuanBi', '2026-09-09 08:40:00', 'NV02'),
('LS33', 'DH11', 'HoanTat', '2026-09-09 09:00:00', 'NV03'),
('LS34', 'DH12', 'ChoXacNhan', '2026-09-09 13:25:00', 'NV04'),
('LS35', 'DH12', 'DangChuanBi', '2026-09-09 13:35:00', 'NV08'),
('LS36', 'DH12', 'DangGiao', '2026-09-09 14:00:00', 'NV07'),
('LS37', 'DH12', 'HoanTat', '2026-09-09 14:35:00', 'NV07'),
('LS38', 'DH13', 'ChoXacNhan', '2026-09-10 19:50:00', 'NV03'),
('LS39', 'DH13', 'DangChuanBi', '2026-09-10 20:00:00', 'NV02'),
('LS40', 'DH14', 'ChoXacNhan', '2026-09-11 11:10:00', 'NV04'),
('LS41', 'DH15', 'ChoXacNhan', '2026-09-11 20:35:00', 'NV03'),
('LS42', 'DH15', 'DangChuanBi', '2026-09-11 20:45:00', 'NV08'),
('LS43', 'DH15', 'DangGiao', '2026-09-11 21:10:00', 'NV07'),
('LS44', 'DH15', 'HoanTat', '2026-09-11 21:50:00', 'NV07');
