# DỰ ÁN CRAVE (PROJECT CRAVE)

Bố cục khung sườn (skeleton) dự án phân chia rõ 2 phần **Frontend** và **Backend** kèm script tạo CSDL MySQL theo yêu cầu.

---

## 1. Cấu trúc thư mục dự án

```text
d:\Nam3\project_crave\
├── database/
│   └── QuanLyDatDoAn.sql           # File SQL tạo 12 bảng, Triggers, Indexes và Dữ liệu mẫu (từ PDF)
│
├── frontend/                       # Bố cục thư mục Frontend (chỉ tạo thư mục rỗng, không chứa code)
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   │   ├── icons/
│   │   │   └── images/
│   │   ├── components/
│   │   │   ├── common/
│   │   │   ├── header/
│   │   │   └── footer/
│   │   ├── hooks/
│   │   ├── layouts/
│   │   ├── services/
│   │   ├── types/
│   │   └── pages/
│   │       ├── home/
│   │       ├── food/
│   │       ├── cart/
│   │       ├── checkout/
│   │       ├── tracking/
│   │       ├── login/
│   │       ├── register/
│   │       └── profile/
│   └── package.json
│
└── backend/                        # Bố cục thư mục Backend (JPA + JSP + HikariCP + Servlet)
    ├── pom.xml                     # Quản lý dependency Maven (JPA, JSP, HikariCP, Servlet, Spring Web)
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/crave/
    │   │   │       ├── controller/          # Chỉ gồm các API Controller Skeleton (@PostMapping, @RequestBody)
    │   │   │       │   ├── AuthController.java
    │   │   │       │   ├── FoodController.java
    │   │   │       │   ├── CartController.java
    │   │   │       │   └── OrderController.java
    │   │   │       ├── dto/                 # Khung sườn DTO tối giản (ApiResponse, RegisterRequest, UserDTO)
    │   │   │       │   ├── ApiResponse.java
    │   │   │       │   ├── RegisterRequest.java
    │   │   │       │   └── UserDTO.java
    │   │   │       ├── entity/              # Thư mục cho JPA Entities (thư mục rỗng)
    │   │   │       ├── repository/          # Thư mục cho DAO / Repository JPA (thư mục rỗng)
    │   │   │       ├── service/             # Thư mục cho Service Interfaces (thư mục rỗng)
    │   │   │       │   └── impl/            # Thư mục Service Implementations (thư mục rỗng)
    │   │   │       ├── servlet/             # Thư mục cho các Servlet (thư mục rỗng)
    │   │   │       ├── filter/              # Thư mục cho HttpFilter (thư mục rỗng)
    │   │   │       ├── listener/            # Thư mục cho ContextListener (thư mục rỗng)
    │   │   │       └── util/                # Thư mục cho các Utility (thư mục rỗng)
    │   │   ├── resources/
    │   │   │   ├── META-INF/
    │   │   │   │   └── persistence.xml      # Khung cấu hình JPA kết nối QuanLyDatDoAn qua HikariCP
    │   │   │   └── hikari.properties        # Cấu hình HikariCP Pool
    │   │   └── webapp/
    │   │       ├── WEB-INF/
    │   │       │   ├── web.xml              # Deployment Descriptor
    │   │       │   └── views/               # Thư mục chứa các trang JSP (thư mục rỗng)
    │   │       │       └── common/
    │   │       └── assets/                 # Thư mục static assets (thư mục rỗng)
    │   │           ├── css/
    │   │           ├── js/
    │   │           └── images/
    │   └── test/
    └── .gitignore
```

---

## 2. Các Controller Skeleton có sẵn

Chỉ gồm đúng các khung API skeleton không có code xử lý bên trong:

* [AuthController.java](file:///d:/Nam3/project_crave/backend/src/main/java/com/crave/controller/AuthController.java):
  - `@PostMapping("/register") public ApiResponse<UserDTO> register(@RequestBody RegisterRequest request) { return null; }`
  - `@PostMapping("/login") public ApiResponse<Object> login(@RequestBody Object request) { return null; }`
* [FoodController.java](file:///d:/Nam3/project_crave/backend/src/main/java/com/crave/controller/FoodController.java):
  - `getAllFoods()`, `getFoodById(...)`, `createFood(...)`
* [CartController.java](file:///d:/Nam3/project_crave/backend/src/main/java/com/crave/controller/CartController.java):
  - `getCart()`, `addItem(...)`
* [OrderController.java](file:///d:/Nam3/project_crave/backend/src/main/java/com/crave/controller/OrderController.java):
  - `checkout(...)`, `getOrders()`
