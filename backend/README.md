# Crave Food & Beverage - Backend

Dự án Backend cho hệ sinh thái Crave Food Delivery, được xây dựng theo kiến trúc phân tầng (Layered Architecture) với đầy đủ các công nghệ yêu cầu:
- **JPA / Hibernate** (Quản lý các Entity và thao tác cơ sở dữ liệu qua EntityManager)
- **HikariCP** (Connection Pool hiệu năng cao)
- **Servlet & Filter & Listener** (Xử lý request HTTP, UTF-8 filter, CORS filter, Auth filter)
- **JSP & JSTL** (Giao diện Server-Side Rendering an toàn trong `/WEB-INF/views`)
- **REST Controller Skeletons** (Khung các REST API chuẩn mực với `@PostMapping`, `@GetMapping`, `@RequestBody`, `@PathVariable`, `ApiResponse<T>`)

---

## Cấu trúc thư mục Backend

```text
backend/
├── pom.xml                                      # Maven dependencies (Jakarta EE 10, Hibernate, HikariCP, MySQL, Spring Web)
├── .gitignore
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/crave/
│   │   │       ├── controller/                  # REST Controllers & Skeleton APIs
│   │   │       │   ├── AuthController.java      # /api/auth (register, login, logout, me)
│   │   │       │   ├── FoodController.java      # /api/foods (CRUD món ăn)
│   │   │       │   ├── CategoryController.java  # /api/categories (CRUD danh mục)
│   │   │       │   ├── CartController.java      # /api/cart (quản lý giỏ hàng)
│   │   │       │   ├── OrderController.java     # /api/orders (đặt hàng, cập nhật trạng thái)
│   │   │       │   ├── VoucherController.java   # /api/vouchers (khuyến mãi)
│   │   │       │   ├── UserController.java      # /api/users (thông tin người dùng)
│   │   │       │   └── servlets/                # Jakarta Servlets (AuthServlet, HomeServlet)
│   │   │       ├── dto/                         # Data Transfer Objects
│   │   │       │   ├── ApiResponse.java         # Chuẩn hóa format response
│   │   │       │   ├── RegisterRequest.java
│   │   │       │   ├── LoginRequest.java
│   │   │       │   ├── UserDTO.java
│   │   │       │   ├── FoodDTO.java
│   │   │       │   ├── CategoryDTO.java
│   │   │       │   ├── CartDTO.java
│   │   │       │   ├── OrderDTO.java
│   │   │       │   └── ...
│   │   │       ├── entity/                      # 12 JPA Entities tương ứng 12 bảng MySQL
│   │   │       │   ├── KhachHang.java
│   │   │       │   ├── NhanVien.java
│   │   │       │   ├── DanhMuc.java
│   │   │       │   ├── MonAn.java
│   │   │       │   ├── TuyChonMon.java
│   │   │       │   ├── DiaChiGiaoHang.java
│   │   │       │   ├── KhuyenMai.java
│   │   │       │   ├── GioHang.java
│   │   │       │   ├── ChiTietGioHang.java
│   │   │       │   ├── DonHang.java
│   │   │       │   ├── ChiTietDonHang.java
│   │   │       │   └── LichSuTrangThaiDonHang.java
│   │   │       ├── repository/                  # JPA Data Access Object / Repository
│   │   │       │   ├── KhachHangRepository.java
│   │   │       │   ├── DanhMucRepository.java
│   │   │       │   ├── MonAnRepository.java
│   │   │       │   ├── GioHangRepository.java
│   │   │       │   └── DonHangRepository.java
│   │   │       ├── service/                     # Service Interfaces & Implementations
│   │   │       │   ├── AuthService.java
│   │   │       │   └── impl/AuthServiceImpl.java
│   │   │       ├── filter/                      # HTTP Filters
│   │   │       │   ├── EncodingFilter.java      # UTF-8 Encoding
│   │   │       │   ├── CorsFilter.java          # CORS cho Frontend
│   │   │       │   └── AuthFilter.java          # Authentication & Authorization
│   │   │       ├── listener/                    # Lifecycle Listeners
│   │   │       │   └── AppContextListener.java
│   │   │       └── util/                        # Tiện ích
│   │   │           ├── JpaUtil.java             # Quản lý EntityManagerFactory
│   │   │           ├── DataSourceUtil.java      # HikariCP DataSource
│   │   │           ├── JsonUtil.java            # Jackson JSON serializer/deserializer
│   │   │           └── PasswordUtil.java        # BCrypt hashing
│   │   ├── resources/
│   │   │   ├── META-INF/
│   │   │   │   └── persistence.xml              # Cấu hình Hibernate + HikariCP
│   │   │   └── hikari.properties                # Cấu hình HikariCP Pool
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml                      # Deployment descriptor
│   │       │   └── views/                       # Thư mục trang JSP
│   │       │       ├── common/header.jsp
│   │       │       ├── common/footer.jsp
│   │       │       └── home.jsp
│   │       └── assets/
│   │           ├── css/style.css
│   │           └── js/main.js
```

---

## Hướng dẫn cài đặt CSDL

Chạy file script SQL:
`database/QuanLyDatDoAn.sql`
trên MySQL server (cổng mặc định 3306, user `root`, pass `12345` hoặc điều chỉnh lại trong `persistence.xml` và `hikari.properties`).
