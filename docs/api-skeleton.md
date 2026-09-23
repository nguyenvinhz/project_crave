# API Skeleton

## Auth

- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/auth/logout`

## Customers

- `GET /api/customers`
- `GET /api/customers/{maKh}`
- `PUT /api/customers/{maKh}`
- `PATCH /api/customers/{maKh}/status`

## Staff

- `GET /api/staff`
- `GET /api/staff/{maNv}`
- `POST /api/staff`
- `PUT /api/staff/{maNv}`
- `PATCH /api/staff/{maNv}/status`

## Categories

- `GET /api/categories`
- `GET /api/categories/{maDm}`
- `POST /api/categories`
- `PUT /api/categories/{maDm}`
- `DELETE /api/categories/{maDm}`

## Foods

- `GET /api/foods`
- `GET /api/foods/{maMon}`
- `POST /api/foods`
- `PUT /api/foods/{maMon}`
- `PATCH /api/foods/{maMon}/status`
- `GET /api/foods/{maMon}/options`

## Food Options

- `POST /api/food-options`
- `PUT /api/food-options/{maTuyChon}`
- `DELETE /api/food-options/{maTuyChon}`

## Addresses

- `GET /api/customers/{maKh}/addresses`
- `POST /api/customers/{maKh}/addresses`
- `PUT /api/addresses/{maDiaChi}`
- `PATCH /api/addresses/{maDiaChi}/default`
- `DELETE /api/addresses/{maDiaChi}`

## Promotions

- `GET /api/promotions`
- `GET /api/promotions/{maKm}`
- `POST /api/promotions`
- `PUT /api/promotions/{maKm}`
- `POST /api/promotions/validate`

## Cart

- `GET /api/customers/{maKh}/cart`
- `POST /api/cart/items`
- `PUT /api/cart/items/{maCtgh}`
- `DELETE /api/cart/items/{maCtgh}`
- `DELETE /api/customers/{maKh}/cart`

## Orders

- `POST /api/orders`
- `GET /api/orders/{maDh}`
- `GET /api/customers/{maKh}/orders`
- `PATCH /api/orders/{maDh}/status`
- `PATCH /api/orders/{maDh}/cancel`
- `GET /api/orders/{maDh}/history`

