# 📋 Bảng Phân Công Nhiệm Vụ Dự Án Project Crave

Tài liệu này phân chia chi tiết trách nhiệm và phạm vi công việc cho **16 branch** (8 Backend + 8 Frontend) theo sát bản thiết kế giao diện [Framer - Thrilled Calendar](https://framer.com/projects/Thrilled-Calendar--1sSWDnYbE1G1X1kvJXe0-9VSP3).

---

## 👥 Mô Hình Phân Bổ Nhân Sự (Đề xuất nhóm 4 người)

Dự án được phân chia thành **4 gói phân hệ chính**. Mỗi thành viên phụ trách **4 branch** (2 Backend + 2 Frontend) để đảm bảo tính đồng bộ end-to-end từ giao diện đến API và CSDL.

```
Nhóm 4 thành viên:
├── Thành viên 1: Xác thực & Hồ sơ cá nhân (Auth & Profile)
├── Thành viên 2: Trang chủ & Thực đơn món ăn (Home & Food)
├── Thành viên 3: Đối tác nhà hàng & Giỏ hàng (Restaurants & Cart)
└── Thành viên 4: Thanh toán & Theo dõi đơn hàng (Checkout & Tracking)
```

---

## 📌 Chi Tiết Nhiệm Vụ Từng Phân Hệ

### 👤 Thành Viên 1: Phân Hệ Xác Thực & Hồ Sơ Cá Nhân

#### 1. Màn hình Xác thực (`/login`, `/register`)
- **Frontend Branch**: `frontend/auth`
  - Xây dựng giao diện Form Đăng nhập & Đăng ký bám sát design Framer.
  - Validation dữ liệu đầu vào (Email, số điện thoại, mật khẩu).
  - Quản lý trạng thái đăng nhập, lưu trữ thông tin phiên người dùng (Local Storage / Session Cookie).
  - Điều hướng sau đăng nhập thành công.
- **Backend Branch**: `backend/auth`
  - Endpoint `POST /api/auth/register`: Đăng ký tài khoản khách hàng mới, kiểm tra trùng số điện thoại/email.
  - Endpoint `POST /api/auth/login`: Xác thực thông tin đăng nhập, băm/so sánh mật khẩu.
  - Endpoint `POST /api/auth/logout`: Hủy phiên làm việc.
  - Bộ lọc xác thực (Authentication Filter) phân quyền `KhachHang` và `NhanVien`.

#### 2. Màn hình Hồ sơ cá nhân (`/profile`)
- **Frontend Branch**: `frontend/profile`
  - Giao diện xem và chỉnh sửa thông tin cá nhân (Họ tên, SĐT, Email).
  - Quản lý sổ địa chỉ nhận hàng: Danh sách địa chỉ, thêm mới, sửa, xóa, đặt địa chỉ mặc định.
  - Tab xem lịch sử các đơn hàng đã đặt và trạng thái đơn gần nhất.
- **Backend Branch**: `backend/profile`
  - Endpoints `GET /api/customers/{maKh}`, `PUT /api/customers/{maKh}`: Xem/sửa thông tin cá nhân.
  - Endpoints `GET /api/customers/{maKh}/addresses`, `POST /api/customers/{maKh}/addresses`: Quản lý sổ địa chỉ.
  - Endpoints `PUT /api/addresses/{maDiaChi}`, `PATCH /api/addresses/{maDiaChi}/default`, `DELETE /api/addresses/{maDiaChi}`.
  - Endpoint `GET /api/customers/{maKh}/orders`: Lấy lịch sử đơn hàng của khách.

---

### 🍕 Thành Viên 2: Phân Hệ Trang Chủ & Thực Đơn Món Ăn

#### 1. Màn hình Trang chủ (`Home`)
- **Frontend Branch**: `frontend/home`
  - Xây dựng Hero Section: Tiêu đề *"Delicious food, delivered to you"*, mô tả, nút *"Order Now"*, thanh tìm kiếm món ăn.
  - Mục *"Browse by craving"*: Các thẻ danh mục kèm hình ảnh (Burgers, Pizza, Sushi, Noodles, Desserts...).
  - Mục *"Popular near you"*: Danh sách thẻ món ăn nổi bật (ảnh món, tên, mô tả ngắn, giá tiền, nút yêu thích).
  - Banner khuyến mãi CTA màu cam và cụm tính năng cam kết (Giao nhanh, thanh toán an toàn, hỗ trợ 24/7).
- **Backend Branch**: `backend/home`
  - API tổng hợp dữ liệu trang chủ: Danh sách danh mục nổi bật (`DanhMuc`), top món ăn thịnh hành (`MonAn`).
  - Hỗ trợ API tìm kiếm nhanh theo từ khóa món ăn hoặc danh mục cravings.

#### 2. Màn hình Chi tiết món & Thực đơn (`/food`)
- **Frontend Branch**: `frontend/food`
  - Giao diện danh sách món ăn đầy đủ theo phân loại danh mục, bộ lọc theo giá và trạng thái còn bán.
  - Modal/Trang chi tiết món ăn: Ảnh lớn, thông tin mô tả chi tiết, đơn giá.
  - Trình cấu hình tùy chọn món: Chọn Size (Nhỏ, Vừa, Lớn), Topping thêm, Mức đường, Mức đá.
  - Bộ đếm số lượng và nút *"Thêm vào giỏ hàng"* (tự động tính tổng tiền theo tùy chọn đã chọn).
- **Backend Branch**: `backend/food`
  - Endpoints `GET /api/foods`, `GET /api/foods/{maMon}`: Danh sách và chi tiết món ăn.
  - Endpoint `GET /api/foods/{maMon}/options`: Trả về danh sách tùy chọn (size, topping) liên kết với món ăn (`TuyChonMon`).
  - Endpoints quản trị: `POST/PUT /api/foods`, `PATCH /api/foods/{maMon}/status`.

---

### 🏬 Thành Viên 3: Phân Hệ Nhà Hàng Đối Tác & Giỏ Hàng

#### 1. Màn hình Nhà hàng (`/restaurants`)
- **Frontend Branch**: `frontend/restaurants`
  - Giao diện danh sách nhà hàng/thương hiệu đối tác theo thiết kế Framer.
  - Thẻ thông tin cửa hàng: Tên, ảnh đại diện, đánh giá sao, thời gian giao hàng dự kiến, khoảng cách.
  - Bộ lọc nhà hàng theo khu vực, ẩm thực hoặc xếp hạng.
  - Xem thực đơn riêng theo từng nhà hàng/thương hiệu.
- **Backend Branch**: `backend/restaurants`
  - Xây dựng mô hình dữ liệu và API phục vụ danh sách cửa hàng/đối tác.
  - API lọc nhà hàng theo khu vực, thời gian hoạt động, thể loại ẩm thực.

#### 2. Màn hình Giỏ hàng (`/cart`)
- **Frontend Branch**: `frontend/cart`
  - Giao diện danh sách các món đang có trong giỏ hàng.
  - Hiển thị chi tiết từng món: Ảnh, tên món, các tùy chọn đã chọn (size, topping...), đơn giá.
  - Tăng/giảm số lượng từng món ăn trực tiếp trong giỏ, nút xóa món, nút xóa sạch giỏ hàng.
  - Ô nhập mã Voucher giảm giá, hiển thị số tiền được giảm tương ứng.
  - Bảng tổng kết tiền: Tạm tính, giảm giá, phí giao hàng dự kiến, tổng thanh toán.
  - Nút chuyển hướng *"Tiến hành đặt hàng"* sang trang Checkout.
- **Backend Branch**: `backend/cart`
  - Endpoints `GET /api/customers/{maKh}/cart`: Lấy thông tin giỏ hàng của khách.
  - Endpoint `POST /api/cart/items`: Thêm món và các tùy chọn vào giỏ (`ChiTietGioHang`).
  - Endpoints `PUT /api/cart/items/{maCtgh}`, `DELETE /api/cart/items/{maCtgh}`: Cập nhật số lượng / xóa món.
  - Endpoint `DELETE /api/customers/{maKh}/cart`: Làm trống giỏ hàng.
  - Endpoint `POST /api/promotions/validate`: Kiểm tra mã voucher hợp lệ và tính số tiền giảm.

---

### 🚚 Thành Viên 4: Phân Hệ Thanh Toán & Theo Dõi Đơn Hàng

#### 1. Màn hình Đặt hàng & Thanh toán (`/checkout`)
- **Frontend Branch**: `frontend/checkout`
  - Lựa chọn hình thức nhận hàng: Giao tận nơi (`GiaoTanNoi`) hoặc Tự đến lấy (`TuDenLay`).
  - Chọn địa chỉ từ sổ địa chỉ đã lưu hoặc nhập địa chỉ giao hàng mới kèm ghi chú cho shipper.
  - Lựa chọn phương thức thanh toán: Tiền mặt (`TienMat`), Ví điện tử (`ViDienTu`), Chuyển khoản (`ChuyenKhoan`).
  - Tóm tắt đơn hàng lần cuối (danh sách món, voucher áp dụng, tổng tiền thanh toán).
  - Nút *"Xác nhận đặt hàng"*, xử lý trạng thái loading và chuyển hướng sang trang Tracking.
- **Backend Branch**: `backend/checkout`
  - Endpoint `POST /api/orders`: Tiếp nhận dữ liệu đặt hàng, chuyển dữ liệu từ giỏ hàng sang bảng `DonHang` và `ChiTietDonHang`.
  - Kích hoạt tính toán phí ship, áp dụng voucher giảm giá hợp lệ và tính tổng tiền đơn hàng.
  - Xóa các item tương ứng trong giỏ sau khi đặt hàng thành công.

#### 2. Màn hình Theo dõi đơn hàng (`/tracking`)
- **Frontend Branch**: `frontend/tracking`
  - Stepper trực quan hiển thị hành trình đơn hàng theo thời gian thực:
    `[Chờ xác nhận]` ➔ `[Đang chuẩn bị]` ➔ `[Đang giao]` ➔ `[Hoàn tất]` (hoặc `[Đã hủy]`).
  - Thời gian dự kiến nhận món, thông tin người giao hàng (tên, SĐT liên hệ).
  - Chi tiết danh sách món ăn đã đặt trong đơn hàng hiện tại.
  - Nút liên hệ hỗ trợ hoặc nút Hủy đơn (chỉ hiển thị khi đơn đang ở trạng thái `ChoXacNhan`).
- **Backend Branch**: `backend/tracking`
  - Endpoint `GET /api/orders/{maDh}`: Lấy thông tin chi tiết và trạng thái hiện tại của đơn hàng.
  - Endpoint `GET /api/orders/{maDh}/history`: Lấy nhật ký lịch sử đổi trạng thái (`LichSuTrangThaiDonHang`).
  - Endpoint `PATCH /api/orders/{maDh}/cancel`: Cho phép khách hủy đơn khi đơn chưa được chuẩn bị.
  - Endpoint `PATCH /api/orders/{maDh}/status`: API dành cho nhân viên cập nhật tiến trình đơn.

---

## 📊 Ma Trận Phân Công & Trạng Thái Công Việc

| STT | Branch Git | Phân Hệ / Màn Hình | Người Phụ Trách | Trạng Thái |
| :-: | :--- | :--- | :--- | :---: |
| 1 | `backend/auth` | API Đăng ký, Đăng nhập, Phân quyền | Thành viên 1 | `Sẵn sàng` |
| 2 | `frontend/auth` | UI Đăng nhập & Đăng ký (`/login`, `/register`) | Thành viên 1 | `Sẵn sàng` |
| 3 | `backend/profile` | API Hồ sơ cá nhân, Sổ địa chỉ | Thành viên 1 | `Sẵn sàng` |
| 4 | `frontend/profile` | UI Hồ sơ & Sổ địa chỉ (`/profile`) | Thành viên 1 | `Sẵn sàng` |
| 5 | `backend/home` | API Dữ liệu trang chủ, Trending, Craving | Thành viên 2 | `Sẵn sàng` |
| 6 | `frontend/home` | UI Trang chủ (`Home` - Framer) | Thành viên 2 | `Đang thực hiện` |
| 7 | `backend/food` | API Thực đơn, Chi tiết món, Tùy chọn món | Thành viên 2 | `Sẵn sàng` |
| 8 | `frontend/food` | UI Danh sách & Modal chọn món (`/food`) | Thành viên 2 | `Sẵn sàng` |
| 9 | `backend/restaurants` | API Quản lý nhà hàng, đối tác ẩm thực | Thành viên 3 | `Sẵn sàng` |
| 10 | `frontend/restaurants` | UI Danh sách & Chi tiết nhà hàng (`/restaurants`) | Thành viên 3 | `Sẵn sàng` |
| 11 | `backend/cart` | API Giỏ hàng, Chi tiết giỏ, Áp dụng voucher | Thành viên 3 | `Sẵn sàng` |
| 12 | `frontend/cart` | UI Giỏ hàng (`/cart`) | Thành viên 3 | `Sẵn sàng` |
| 13 | `backend/checkout` | API Tạo đơn hàng, Xử lý thanh toán | Thành viên 4 | `Sẵn sàng` |
| 14 | `frontend/checkout` | UI Đặt hàng & Thanh toán (`/checkout`) | Thành viên 4 | `Sẵn sàng` |
| 15 | `backend/tracking` | API Theo dõi đơn hàng & Lịch sử trạng thái | Thành viên 4 | `Sẵn sàng` |
| 16 | `frontend/tracking` | UI Theo dõi tiến trình đơn hàng (`/tracking`) | Thành viên 4 | `Sẵn sàng` |

---

## 🎯 Tiêu Chí Hoàn Thành Một Nhánh (Definition of Done - DoD)

Trước khi tạo Pull Request để gộp vào nhánh chính (`main`), mỗi nhánh cần đảm bảo:
1. **Frontend**:
   - Giao diện khớp với bố cục, màu sắc và typography của bản thiết kế Framer.
   - Hoạt động responsive tốt trên cả Desktop, Tablet và Mobile.
   - Không có lỗi cú pháp hoặc lỗi hiển thị trên Console trình duyệt.
   - Đã liên kết API (hoặc có fallback dữ liệu mẫu rõ ràng).
2. **Backend**:
   - Đúng chuẩn RESTful JSON payload theo tài liệu `docs/api-skeleton.md`.
   - Xử lý đầy đủ các ngoại lệ (Validation error 400, Not found 404, Server error 500).
   - Truy vấn CSDL chính xác, an toàn, sử dụng đúng bảng trong `QuanLyDatDoAn`.
3. **Quy chuẩn Git**:
   - Tuân thủ toàn bộ quy tắc viết commit tại `docs/git-convention.md`.
