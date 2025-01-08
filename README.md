<p>
    <h1 align="center">PROJECT SHOPPING KRIST BACK-END </h1>
</p>

<p align="center">
	<img src="https://img.shields.io/github/last-commit/jeyofdev/shopping-krist-back-end?style=flat-square&logo=git&logoColor=white&color=157bed" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/jeyofdev/shopping-krist-back-end?style=flat-square&color=157bed" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/jeyofdev/shopping-krist-back-end?style=flat-square&color=157bed" alt="repo-language-count">
<p>

<p align="center">
    <em>Developed with the software and tools below.</em>
</p>

<p align="center">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white" alt="Java">
	<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=flat-square&logo=spring&logoColor=white" alt="Spring">
	<img src="https://img.shields.io/badge/JWT-black?style=flat-square&logo=JSON%20web%20tokens" alt="Jwt">
	<img src="https://img.shields.io/badge/postgres-%23316192.svg?style=flat-square&logo=postgresql&logoColor=white" alt="Postgresql">
	<img src="https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=flat-square&logo=Apache%20Maven&logoColor=white" alt="Maven">
    <img src="https://img.shields.io/badge/JSON-000000.svg?style=flat-square&logo=JSON&logoColor=white" alt="JSON">
    <img src="https://img.shields.io/badge/GitHub-181717.svg?style=flat-square&logo=GitHub&logoColor=white" alt="GitHub">
</p>
<hr>

<p>
    Shopping Krist Back-End is a Java-based application designed to handle server-side operations for an e-commerce platform. It includes features such as product management, user management and order handling.
</p>

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL (or any other supported database)

## Installation

1. Clone the repository :
    ```sh
    git clone https://github.com/jeyofdev/shopping-krist-back-end.git
    cd shopping-krist-back-end
    ```

2. create an .env file : 
   ```
   # Database
   DB_HOST=
   DB_PORT=
   DB_NAME=shopping_krist
   DB_USER=
   DB_PASSWORD=
   
   # JWT
   TOKEN_PREFIX=
   TOKEN_SECRET_KEY=

   # Email
   MAIL_HOST=
   MAIL_PORT=
   MAIL_USERNAME=
   MAIL_PASSWORD=
   MAIL_SMTP_AUTH=true
   MAIL_SMTP_STARTTLS_ENABLE=true
   MAIL_FROM=
   ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints with permissions in ()

### Orders

- `GET /api/v1/orders` - Get all orders (Admin only)
- `GET /api/v1/orders/{orderId}` - Get order by ID (Admin and User)
- `POST /api/v1/orders/profile/{profileId}/address/{addressId}` - Create a new order (Admin and User)
- `PUT /api/v1/orders/{orderId}` - Update an order by ID (Admin and User)
- `DELETE /api/v1/orders/{orderId}` - Delete an order by ID (Admin and User)

### Products

- `GET /api/v1/products` - Get all products (Admin and User)
- `GET /api/v1/products/{productId}` - Get product by ID (Admin and User)
- `POST /api/v1/products` - Create a new product (Admin only)
- `PUT /api/v1/products/{productId}` - Update a product by ID (Admin only)
- `DELETE /api/v1/products/{productId}` - Delete a product by ID (Admin only)

### Categories

- `GET /api/v1/categories` - Get all categories (Admin and User)
- `GET /api/v1/categories/{categoryId}` - Get category by ID (Admin and User)
- `POST /api/v1/categories` - Create a new category (Admin only)
- `PUT /api/v1/categories/{categoryId}` - Update a category by ID (Admin only)
- `DELETE /api/v1/categories/{categoryId}` - Delete a category by ID (Admin only)

### Cities

- `GET /api/v1/cities` - Get all cities (Admin and User)
- `GET /api/v1/cities/{cityId}` - Get city by ID (Admin and User)
- `POST /api/v1/cities` - Create a new city (Admin only)
- `PUT /api/v1/cities/{cityId}` - Update a city by ID (Admin only)
- `DELETE /api/v1/cities/{cityId}` - Delete a city by ID (Admin only)

### Addresses

- `GET /api/v1/addresses` - Get all addresses (Admin and User)
- `GET /api/v1/addresses/{addressId}` - Get address by ID (Admin and User)
- `POST /api/v1/addresses` - Create a new address (Admin and User)
- `PUT /api/v1/addresses/{addressId}` - Update an address by ID (Admin and User)
- `DELETE /api/v1/addresses/{addressId}` - Delete an address by ID (Admin and User)

### Carts

- `GET /api/v1/carts` - Get all carts (Admin and User)
- `GET /api/v1/carts/{cartId}` - Get cart by ID (Admin and User)
- `POST /api/v1/carts` - Create a new cart (Admin and User)
- `PUT /api/v1/carts/{cartId}` - Update a cart by ID (Admin and User)
- `DELETE /api/v1/carts/{cartId}` - Delete a cart by ID (Admin and User)

### Cart Items

- `GET /api/v1/cartItems` - Get all cart items (Admin and User)
- `GET /api/v1/cartItems/{cartItemId}` - Get cart item by ID (Admin and User)
- `POST /api/v1/cartItems` - Create a new cart item (Admin and User)
- `PUT /api/v1/cartItems/{cartItemId}` - Update a cart item by ID (Admin and User)
- `DELETE /api/v1/cartItems/{cartItemId}` - Delete a cart item by ID (Admin and User)

### Comments

- `GET /api/v1/comments` - Get all comments (Admin and User)
- `GET /api/v1/comments/{commentId}` - Get comment by ID (Admin and User)
- `POST /api/v1/comments` - Create a new comment (Admin and User)
- `PUT /api/v1/comments/{commentId}` - Update a comment by ID (Admin and User)
- `DELETE /api/v1/comments/{commentId}` - Delete a comment by ID (Admin and User)

### Notifications

- `GET /api/v1/notifications` - Get all notifications (Admin and User)
- `GET /api/v1/notifications/{notificationId}` - Get notification by ID (Admin and User)
- `POST /api/v1/notifications` - Create a new notification (Admin and User)
- `PUT /api/v1/notifications/{notificationId}` - Update a notification by ID (Admin and User)
- `DELETE /api/v1/notifications/{notificationId}` - Delete a notification by ID (Admin and User)

### Profiles

- `GET /api/v1/profiles` - Get all profiles (Admin and User)
- `GET /api/v1/profiles/{profileId}` - Get profile by ID (Admin and User)
- `POST /api/v1/profiles` - Create a new profile (Admin and User)
- `PUT /api/v1/profiles/{profileId}` - Update a profile by ID (Admin and User)
- `DELETE /api/v1/profiles/{profileId}` - Delete a profile by ID (Admin and User)

### Profile Settings

- `GET /api/v1/profileSettings` - Get all profile settings (Admin and User)
- `GET /api/v1/profileSettings/{profileSettingsId}` - Get profile settings by ID (Admin and User)
- `POST /api/v1/profileSettings` - Create new profile settings (Admin and User)
- `PUT /api/v1/profileSettings/{profileSettingsId}` - Update profile settings by ID (Admin and User)
- `DELETE /api/v1/profileSettings/{profileSettingsId}` - Delete profile settings by ID (Admin and User)

### Authentication

- `POST /api/v1/auth/register` - User registration
- `POST /api/v1/auth/login` - User login
- `GET /api/v1/auth/validate-account` - Validate account
- `POST /api/v1/auth/update-password` - Update password
- `POST /api/v1/auth/forgot-password` - Forgot password
- `POST /api/v1/auth/reset-password` - Reset password