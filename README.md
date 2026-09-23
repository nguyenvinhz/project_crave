# Project Crave - Hệ Thống Đặt Món Ăn Trực Tuyến

> **Project Crave** là nền tảng đặt và giao đồ ăn trực tuyến (Food Ordering & Delivery Platform), được thiết kế theo mô hình kiến trúc phân tầng chuyên nghiệp, hỗ trợ đầy đủ các quy trình từ khám phá món ăn, tùy biến nguyên liệu, áp dụng mã khuyến mãi, giỏ hàng, đặt hàng đến theo dõi trạng thái đơn hàng theo thời gian thực.

---

## 📌 Mục Lục
1. [Tổng Quan Dự Án](#-tổng-quan-dự-án)
2. [Kiến Trúc & Công Nghệ](#-kiến-trúc--công-nghệ)
3. [Cấu Trúc Thư Mục](#-cấu-trúc-thư-mục)
4. [Các Module Chức Năng Chính](#-các-module-chức-năng-chính)
5. [Thiết Kế Cơ Sở Dữ Liệu (`QuanLyDatDoAn`)](#-thiết-kế-cơ-sở-dữ-liệu-quanlydatdoan)
6. [Danh Mục RESTful API](#-danh-mục-restful-api)
7. [Hướng Dẫn Cài Đặt & Chạy Dự Án](#-hướng-dẫn-cài-đặt--chạy-dự-án)
8. [Phân Quyền & Vai Trò Hệ Thống](#-phân-quyền--vai-trò-hệ-thống)

---

## 🌟 Tổng Quan Dự Án

Project Crave giải quyết bài toán đặt món tiện lợi cho khách hàng và quản lý vận hành tinh gọn cho nhà hàng/chuỗi cửa hàng:
- **Khách hàng**: Dễ dàng tìm kiếm món ăn theo danh mục, tùy biến thuộc tính món (size, topping, đường, đá), lưu nhiều địa chỉ giao hàng, lưu giỏ hàng cá nhân hóa, áp dụng mã voucher giảm giá và theo dõi đơn hàng từng bước.
- **Nhân viên & Quản trị**: Phân chia quyền hạn rõ ràng (Quản trị viên, Quản lý món, Xử lý đơn hàng, Quản lý khuyến mãi, Quản lý nhân sự) giúp tối ưu hóa tiến trình xử lý đơn hàng từ lúc tiếp nhận đến khi giao thành công.

---

## 🛠 Kiến Trúc & Công Nghệ

### 1. Backend (Java Enterprise / RESTful API)
- **Ngôn ngữ & Nền tảng**: Java 17 (LTS).
- **Web Layer**: Jakarta Servlet 6.0, JSTL 3.0, Jackson Databind 2.17 (xử lý JSON serialize/deserialize).
- **ORM / Persistence**: JPA 3.1 với Hibernate ORM 6.4.4.Final.
- **Connection Pool**: HikariCP 5.1.0 – thư viện pooling hiệu năng cao.
- **Cơ sở dữ liệu**: MySQL 8.x (`mysql-connector-j` 8.4.0), hỗ trợ đầy đủ tiếng Việt với bảng mã `utf8mb4`.
- **Kiến trúc mã nguồn**: Phân lớp rõ ràng:
  - `Controller` (Servlet API): Tiếp nhận request, điều phối dữ liệu.
  - `Service`: Chứa toàn bộ business logic nghiệp vụ.
  - `Repository`: Tương tác với CSDL qua JPA EntityManager.
  - `DTO` (Request / Response): Tách biệt mô hình truyền tải dữ liệu và Entity CSDL.
  - `Entity`: Ánh xạ bảng CSDL theo chuẩn JPA annotation.

### 2. Frontend
- **Giao diện**: HTML5, Vanilla CSS hiện đại, JavaScript modules.
- **Tổ chức trang**: Cấu trúc module hóa theo trang chức năng:
  - `home`: Trang chủ giới thiệu, món ăn thịnh hành, danh mục nổi bật.
  - `food`: Danh sách & chi tiết món ăn, tùy chọn option.
  - `cart`: Quản lý giỏ hàng, số lượng, tùy chọn đi kèm.
  - `checkout`: Đặt hàng, chọn địa chỉ nhận hàng, phương thức thanh toán, mã giảm giá.
  - `tracking`: Theo dõi trạng thái hành trình đơn hàng.
  - `profile`: Hồ sơ cá nhân, danh sách địa chỉ giao hàng.
  - `login` & `register`: Đăng nhập, đăng ký tài khoản.
- **Tài nguyên**: Hệ thống SVG icon và design system đồng bộ.

### 3. Build & Quản Lý Dự Án
- **Công cụ build**: Apache Maven 3.8+ (định dạng đóng gói `war`).

---

## 📂 Cấu Trúc Thư Mục

```text
project_crave/
├── backend/
│   └── src/
│       └── main/
│           ├── java/com/crave/
│           │   ├── common/           # Lớp dùng chung (ApiResponse, ApiServlet base)
│           │   ├── config/           # Cấu hình HikariCP, JPA Listener
│           │   ├── controller/       # Các Servlet Controller RESTful
│           │   ├── dto/
│           │   │   ├── request/      # Các DTO nhận dữ liệu từ client
│           │   │   └── response/     # Các DTO trả về cho client
│           │   ├── entity/           # Các JPA Entities ánh xạ CSDL
│           │   ├── repository/       # Tầng truy vấn CSDL (DAO/Repository)
│           │   ├── service/          # Tầng xử lý nghiệp vụ (Business logic)
│           │   └── util/             # Tiện ích bổ trợ (JpaUtil)
│           └── resources/
│               ├── META-INF/
│               │   └── persistence.xml # Cấu hình JPA Persistence Unit
│               └── application.properties # Cấu hình kết nối MySQL & HikariCP
├── frontend/
│   ├── public/                       # Favicon và SVG static
│   └── src/
│       ├── assets/                   # Biểu tượng SVG (icon) và hình ảnh
│       ├── components/               # Header, Footer dùng chung
│       └── pages/                    # Các trang chức năng
│           ├── home/                 # Trang chủ (HTML, CSS, JS)
│           ├── food/                 # Chi tiết & danh mục món
│           ├── cart/                 # Giỏ hàng
│           ├── checkout/             # Thanh toán & đặt hàng
│           ├── tracking/             # Theo dõi đơn hàng
│           ├── profile/              # Thông tin khách hàng & địa chỉ
│           ├── login/                # Đăng nhập
│           └── register/             # Đăng ký
├── database/
│   ├── schema.sql                    # Kịch bản tạo database, bảng, khóa, trigger
│   └── seed.sql                      # Kịch bản nạp dữ liệu mẫu ban đầu
├── docs/
│   └── api-skeleton.md               # Danh sách chi tiết các endpoints của API
├── .gitignore                        # Cấu hình bỏ qua file build, cache, dependencies
├── pom.xml                           # Cấu hình Maven Dependencies & Plugins
└── README.md                         # Tài liệu hướng dẫn dự án
```

---

## 🚀 Các Module Chức Năng Chính

### 1. Phân Hệ Người Dùng & Xác Thực (Auth & Users)
- **Đăng ký & Đăng nhập**: Dành cho khách hàng và nhân viên, kiểm tra mật khẩu và trạng thái tài khoản (`HoatDong`, `DaKhoa`, `DangLamViec`, `DaNghiViec`).
- **Sổ địa chỉ khách hàng**: Quản lý nhiều địa chỉ nhận hàng, thiết lập địa chỉ mặc định để thanh toán nhanh.

### 2. Phân Hệ Thực Đơn & Món Ăn (Catalog & Foods)
- **Danh mục món ăn**: Nhóm món theo thể loại (Burger, Pizza, Cơm, Mì, Đồ uống, Tráng miệng...).
- **Món ăn**: Quản lý tên món, hình ảnh, giá bán, mô tả, trạng thái kinh doanh (`DangBan`, `NgungBan`).
- **Tùy chọn món (Food Options)**: Cho phép cấu hình các tùy chọn phong phú:
  - `Size`: Nhỏ, Vừa, Lớn...
  - `Topping`: Thêm phô mai, trân châu, thịt xông khói...
  - `MucDuong` & `MucDa`: Điều chỉnh theo nhu cầu.

### 3. Phân Hệ Giỏ Hàng (Cart)
- Giỏ hàng lưu trữ theo từng khách hàng.
- Thêm món kèm các tùy chọn riêng biệt, cập nhật số lượng, xóa từng món hoặc làm trống giỏ hàng.

### 4. Phân Hệ Khuyến Mãi (Promotions)
- Quản lý mã giảm giá với 2 hình thức:
  - `PhanTram`: Giảm theo tỷ lệ % giá trị đơn hàng.
  - `TienMat`: Giảm trực tiếp số tiền cố định.
- Kiểm tra tính hợp lệ theo thời gian (`NgayBatDau` - `NgayKetThuc`) và trạng thái (`ConHieuLuc`).

### 5. Phân Hệ Đơn Hàng & Vận Chuyển (Orders & Checkout)
- **Đặt hàng**: Chọn hình thức nhận (`GiaoTanNoi` hoặc `TuDenLay`), chọn địa chỉ giao, áp dụng voucher khuyến mãi.
- **Phương thức thanh toán**: Hỗ trợ Tiền mặt (`TienMat`), Ví điện tử (`ViDienTu`), Chuyển khoản (`ChuyenKhoan`).
- **Quy trình trạng thái đơn hàng**:
  ```text
  [ChoXacNhan] ➔ [DangChuanBi] ➔ [DangGiao] ➔ [HoanTat]
        ↳ [DaHuy]
  ```
- **Lịch sử trạng thái**: Mỗi lần chuyển trạng thái đều được lưu vào bảng `LichSuTrangThaiDonHang`, ghi nhận thời điểm và nhân viên xử lý.

---

## 🗄 Thiết Kế Cơ Sở Dữ Liệu (`QuanLyDatDoAn`)

Hệ cơ sở dữ liệu MySQL bao gồm 11 bảng chính với đầy đủ ràng buộc khóa ngoại, tính toàn vẹn dữ liệu và chỉ mục tăng tốc độ truy vấn:

| Tên Bảng | Mô Tả |
| :--- | :--- |
| `KhachHang` | Thông tin tài khoản khách hàng, mật khẩu, trạng thái hoạt động |
| `NhanVien` | Tài khoản nhân viên, chức vụ/vai trò, ngày vào làm, trạng thái |
| `DanhMuc` | Danh mục phân loại các món ăn |
| `MonAn` | Thông tin món ăn, đơn giá, hình ảnh, trạng thái bán |
| `TuyChonMon` | Các tùy chọn thêm cho món (size, topping, mức đường/đá, giá cộng thêm) |
| `DiaChiGiaoHang`| Danh sách địa chỉ nhận hàng của khách hàng (có cờ địa chỉ mặc định) |
| `KhuyenMai` | Chương trình ưu đãi, mã giảm giá, hạn sử dụng, mức giảm |
| `GioHang` | Giỏ hàng được liên kết riêng với từng khách hàng |
| `ChiTietGioHang`| Món ăn, tùy chọn kèm theo và số lượng trong giỏ hàng |
| `DonHang` | Đơn đặt hàng, thông tin giao hàng, thanh toán, tổng tiền, trạng thái |
| `ChiTietDonHang`| Chi tiết từng món ăn trong đơn, số lượng, đơn giá và thành tiền |
| `LichSuTrangThaiDonHang` | Nhật ký chuyển đổi trạng thái của đơn hàng và người thực hiện |

> **Trigger tự động tính tổng tiền**: Trigger `TRG_ChiTietDonHang_TinhLaiTongTien` tự động tính toán lại `TongTien = SUM(ThanhTien) + PhiGiaoHang` trên bảng `DonHang` khi có chi tiết đơn hàng mới được thêm vào.

---

## 📡 Danh Mục RESTful API

Hệ thống cung cấp đầy đủ các API theo chuẩn RESTful JSON:

- **Xác thực (`/api/auth`)**:
  - `POST /api/auth/register`: Đăng ký tài khoản mới.
  - `POST /api/auth/login`: Đăng nhập hệ thống.
  - `POST /api/auth/logout`: Đăng xuất.
- **Khách hàng (`/api/customers`)**:
  - `GET /api/customers`, `GET /api/customers/{maKh}`: Danh sách / chi tiết khách hàng.
  - `PUT /api/customers/{maKh}`: Cập nhật thông tin.
  - `PATCH /api/customers/{maKh}/status`: Khóa / kích hoạt tài khoản.
  - `GET/POST /api/customers/{maKh}/addresses`: Sổ địa chỉ khách hàng.
- **Nhân viên (`/api/staff`)**:
  - `GET/POST /api/staff`, `PUT /api/staff/{maNv}`: Quản lý nhân sự.
  - `PATCH /api/staff/{maNv}/status`: Cập nhật trạng thái làm việc.
- **Danh mục & Món ăn (`/api/categories`, `/api/foods`)**:
  - `GET/POST/PUT/DELETE /api/categories`: Quản lý danh mục.
  - `GET/POST/PUT /api/foods`: Quản lý thực đơn.
  - `GET /api/foods/{maMon}/options`: Lấy các tùy chọn theo món.
  - `POST/PUT/DELETE /api/food-options`: Quản lý tùy chọn (size, topping).
- **Giỏ hàng (`/api/cart`)**:
  - `GET /api/customers/{maKh}/cart`: Lấy giỏ hàng hiện tại.
  - `POST /api/cart/items`: Thêm món vào giỏ.
  - `PUT/DELETE /api/cart/items/{maCtgh}`: Sửa số lượng / xóa món khỏi giỏ.
  - `DELETE /api/customers/{maKh}/cart`: Xóa toàn bộ giỏ hàng.
- **Đơn hàng (`/api/orders`)**:
  - `POST /api/orders`: Tạo đơn hàng mới từ giỏ hàng.
  - `GET /api/orders/{maDh}`: Xem chi tiết đơn hàng.
  - `GET /api/customers/{maKh}/orders`: Xem danh sách đơn của một khách hàng.
  - `PATCH /api/orders/{maDh}/status`: Cập nhật trạng thái đơn (dành cho nhân viên).
  - `PATCH /api/orders/{maDh}/cancel`: Khách hàng hủy đơn khi còn ở trạng thái `ChoXacNhan`.
  - `GET /api/orders/{maDh}/history`: Xem tiến trình và lịch sử chuyển trạng thái.
- **Khuyến mãi (`/api/promotions`)**:
  - `GET/POST/PUT /api/promotions`: Quản lý mã khuyến mãi.
  - `POST /api/promotions/validate`: Kiểm tra voucher có hợp lệ với đơn hàng hay không.

*(Xem chi tiết payload request/response tại tài liệu [docs/api-skeleton.md](file:///d:/Nam3/project_crave/docs/api-skeleton.md))*

---

## 💻 Hướng Dẫn Cài Đặt & Chạy Dự Án

### 1. Yêu Cầu Môi Trường
- **JDK**: Java Development Kit 17 trở lên.
- **Database**: MySQL Server 8.0 trở lên.
- **Build Tool**: Apache Maven 3.8+.
- **Servlet Container / Server**: Apache Tomcat 10+ (hỗ trợ Jakarta EE 10 / Servlet 6.0).

### 2. Thiết Lập Cơ Sở Dữ Liệu
1. Mở MySQL Workbench hoặc terminal MySQL:
   ```bash
   mysql -u root -p
   ```
2. Thực thi script khởi tạo cấu trúc CSDL:
   ```sql
   SOURCE d:/Nam3/project_crave/database/schema.sql;
   ```
3. Nạp dữ liệu mẫu ban đầu:
   ```sql
   SOURCE d:/Nam3/project_crave/database/seed.sql;
   ```

### 3. Cấu Hình Ứng Dụng
Kiểm tra và cập nhật thông tin kết nối CSDL trong file:
[backend/src/main/resources/application.properties](file:///d:/Nam3/project_crave/backend/src/main/resources/application.properties)

```properties
db.url=jdbc:mysql://localhost:3306/QuanLyDatDoAn?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh
db.username=root
db.password=mat_khau_cua_ban
db.pool.maximumPoolSize=10
```

### 4. Build Ứng Dụng Với Maven
Tại thư mục gốc của project, thực thi lệnh build:
```bash
mvn clean package
```
File đóng gói `.war` sẽ được tạo ra tại thư mục `target/project-crave-1.0.0-SNAPSHOT.war` sẵn sàng để deploy lên Apache Tomcat 10.

---

## 👥 Phân Quyền & Vai Trò Hệ Thống

| Vai Trò (`ChucVu`) | Phạm Vi Trách Nhiệm |
| :--- | :--- |
| **`KhachHang`** | Xem thực đơn, quản lý giỏ hàng, đặt hàng, quản lý sổ địa chỉ, xem trạng thái đơn của mình |
| **`QuanTriVien`** | Toàn quyền quản trị hệ thống, quản lý tài khoản nhân sự và cấu hình |
| **`NhanVienQuanLiMon`** | Quản lý danh mục món ăn, thêm/sửa món và thiết lập tùy chọn (size, topping) |
| **`NhanVienXuLyDonHang`** | Tiếp nhận đơn mới, xác nhận, cập nhật đơn sang chuẩn bị, bàn giao đơn cho giao hàng |
| **`NhanVienQuanLiKhuyenMai`** | Tạo và cấu hình các chương trình ưu đãi, mã giảm giá, thời gian áp dụng |
| **`NhanVienQuanLiNhanSu`** | Quản lý hồ sơ nhân viên, ngày vào làm, phân ca và trạng thái làm việc |

---

## 📄 Bản Quyền & Giấy Phép
Dự án được phát triển phục vụ mục đích học tập và xây dựng hệ thống phần mềm chuyên ngành CNTT. Mọi quyền sở hữu thuộc về nhóm phát triển **Project Crave**.
