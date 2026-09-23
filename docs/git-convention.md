# 📜 Quy Chuẩn Làm Việc Với Git (Git Convention) - Project Crave

Tài liệu này quy định toàn bộ tiêu chuẩn làm việc với Git cho các thành viên trong nhóm phát triển **Project Crave**, bao gồm quy tắc đặt tên nhánh, định dạng commit message, quy trình làm việc và quy chuẩn mở Pull Request (PR).

---

## 🌿 1. Quy Tắc Đặt Tên Nhánh (Branch Naming)

Toàn bộ nhánh chức năng trong dự án được tiền tố hóa theo layer để phân biệt rạch ròi giữa Frontend và Backend:

### Cấu trúc:
- **Backend**: `backend/<tên-module>`
- **Frontend**: `frontend/<tên-module>`
- **Sửa lỗi khẩn cấp**: `hotfix/<mô-tả-lỗi>`

### Danh sách 16 nhánh chính thức của dự án:
```
backend/auth            frontend/auth
backend/profile         frontend/profile
backend/home            frontend/home
backend/food            frontend/food
backend/restaurants     frontend/restaurants
backend/cart            frontend/cart
backend/checkout        frontend/checkout
backend/tracking        frontend/tracking
```

> **Nguyên tắc**: 
> - Nhánh `main` là nhánh nguồn cội (Production ready), được bảo vệ, **tuyệt đối không code hoặc commit trực tiếp lên `main`**.
> - Mọi thành viên chỉ làm việc trên nhánh được phân công.

---

## 💬 2. Quy Chuẩn Commit Message (Conventional Commits)

Mỗi commit phải có ý nghĩa rõ ràng, súc tích và tuân theo cấu trúc sau:

```text
<type>(<scope>): <mô tả ngắn gọn về thay đổi>
```

### 2.1. Các Loại Commit (`type`)

| Type | Ý Nghĩa | Ví Dụ |
| :--- | :--- | :--- |
| **`feat`** | Thêm tính năng mới cho ứng dụng | `feat(home): dung layout hero banner theo framer` |
| **`fix`** | Sửa lỗi (bug) trong mã nguồn | `fix(cart): sua loi tinh sai tong tien khi xoa mon` |
| **`docs`** | Thêm hoặc sửa đổi tài liệu (README, docs) | `docs: cap nhat huong dan cai dat database` |
| **`style`** | Chỉnh sửa giao diện, CSS, khoảng trắng (không đổi logic code) | `style(food): chinh mau sac button them vao gio` |
| **`refactor`**| Tái cấu trúc code (không thêm tính năng, không sửa lỗi) | `refactor(auth): tach logic validate token sang lop rieng` |
| **`perf`** | Cải thiện hiệu năng xử lý hoặc tải trang | `perf(database): toi uu cau truy van lay top mon an` |
| **`test`** | Thêm hoặc sửa các bài kiểm thử tự động | `test(order): bo sung unit test cho tinh phi ship` |
| **`chore`** | Các thay đổi phụ trợ (build tool, pom.xml, gitignore) | `chore: cap nhat dependencies hibernate trong pom.xml` |

### 2.2. Phạm Vi Thay Đổi (`scope`)

Scope chỉ định phân hệ bị tác động, sử dụng các từ khóa sau:
- `auth`: Xác thực, đăng nhập, đăng ký
- `profile`: Hồ sơ cá nhân, sổ địa chỉ
- `home`: Trang chủ, cravings, trending
- `food`: Danh mục món, chi tiết món, options
- `restaurants`: Danh sách nhà hàng đối tác
- `cart`: Giỏ hàng, voucher
- `checkout`: Thanh toán, tạo đơn hàng
- `tracking`: Theo dõi tiến trình đơn hàng
- `database`: Schema SQL, seed data, trigger
- `api`: Common API servlet, response format

### 2.3. Ví Dụ Cụ Thể

#### ✅ Ví dụ chuẩn:
```bash
feat(home): tich hop api lay danh sach craving categories
fix(checkout): xu ly loi khong nhan dia chi mac dinh
style(tracking): them hieu ung stepper cho trang thai dang giao
docs: bo sung bang phan cong nhiem vu vao docs
chore(db): them cot HinhAnh vao du lieu mau seed.sql
```

#### ❌ Ví dụ sai (CẦN TRÁNH):
```bash
git commit -m "fix"               # Quá chung chung, không rõ sửa gì
git commit -m "update code"        # Không đúng chuẩn, vô nghĩa
git commit -m "xong frontend"      # Quá lớn, thiếu type và scope
git commit -m "asdfghjk"           # Cấm tuyệt đối
```

---

## 🔄 3. Quy Trình Làm Việc Hằng Ngày (Git Workflow)

### Bước 1: Chuyển sang nhánh được phân công và kéo code mới nhất
```bash
git checkout <ten-nhanh-cua-ban>
git pull origin <ten-nhanh-cua-ban>
```
*Ví dụ:* `git checkout frontend/home`

### Bước 2: Viết mã nguồn & Commit định kỳ
- Chia nhỏ các commit theo từng đầu việc cụ thể thay vì dồn một commit khổng lồ.
```bash
git status
git add <cac-file-thay-doi>
git commit -m "feat(home): dung component popular food card"
```

### Bước 3: Đồng bộ với nhánh `main` trước khi đẩy code
Trước khi push, kéo code mới nhất từ `main` về nhánh của mình để tránh xung đột (conflict):
```bash
git fetch origin
git merge origin/main
```
*(Nếu có conflict, mở VS Code giải quyết xung đột, lưu lại rồi commit hoàn tất merge)*.

### Bước 4: Đẩy nhánh lên GitHub
```bash
git push origin <ten-nhanh-cua-ban>
```

---

## 🔀 4. Quy Chuẩn Tạo Pull Request (PR)

Khi đã hoàn thành xong một tính năng trên nhánh của mình và sẵn sàng gộp vào `main`:

1. **Tiêu đề PR**: Tuân theo đúng chuẩn commit:
   - *Ví dụ:* `feat(frontend/home): Hoàn thiện giao diện trang chủ theo thiết kế Framer`
2. **Nội dung mô tả PR**:
   - Tóm tắt các thay đổi đã thực hiện.
   - Danh sách file/component chính được tạo hoặc sửa đổi.
   - Ảnh chụp màn hình (screenshot) hoặc kết quả kiểm thử API Postman.
3. **Người Review (Reviewer)**:
   - Gán ít nhất 1 thành viên khác trong nhóm review code trước khi nhấn Merge.
4. **Quy tắc Merge**:
   - Khuyến khích sử dụng **Squash and merge** hoặc **Create a merge commit**.
   - Sau khi merge thành công vào `main`, có thể xóa nhánh tạm nếu không còn phát triển thêm tính năng đó.

---

## 🚫 5. Những Điều Cấm Kỵ (Do's & Don'ts)

| KHÔNG ĐƯỢC LÀM ❌ | NÊN LÀM ✅ |
| :--- | :--- |
| **Không** `git push --force` lên nhánh `main`. | Chỉ force push trên nhánh cá nhân khi thực sự hiểu rõ hậu quả. |
| **Không** commit thông tin nhạy cảm: mật khẩu CSDL cá nhân, private key, token bí mật. | Dùng file cấu hình mẫu hoặc biến môi trường. |
| **Không** commit file binary/build: thư mục `target/`, file `.class`, cache IDE (`.idea/`, `.vscode/`). | Kiểm tra file `.gitignore` trước khi `git add .` |
| **Không** commit code đang bị lỗi biên dịch (build failed) lên remote. | Luôn chạy thử `mvn clean compile` hoặc kiểm tra giao diện trên trình duyệt trước khi push. |
