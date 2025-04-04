-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3366
-- Thời gian đã tạo: Th4 04, 2025 lúc 12:32 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `crud_database1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('hung', 'ROLE_ADMIN'),
('hung', 'ROLE_MANAGER'),
('hung', 'ROLE_STUDENT'),
('tru', 'ROLE_STUDENT'),
('truong', 'ROLE_MANAGER'),
('truong', 'ROLE_STUDENT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `carts`
--

INSERT INTO `carts` (`id`, `user_id`) VALUES
(2, 'hung'),
(1, 'truong');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_items`
--

CREATE TABLE `cart_items` (
  `id` bigint(20) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `name_company` varchar(8) NOT NULL,
  `post_code` varchar(10) NOT NULL,
  `region` varchar(255) NOT NULL,
  `street_address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `city`, `country`, `name_company`, `post_code`, `region`, `street_address`) VALUES
(1, 'Hồ Chí Minh', 'VN', 'VuNam777', '11117777', 'Miền Nam', 'Thủ Đức'),
(3, 'Hồ Chí Minh', 'VN', 'Sanhdev1', '44448888', 'Miền Nam', '333 Gò Vấp'),
(4, 'Hồ Chí Minh', 'VN', 'Huyen267', '26072002', 'Miền Nam', 'Quận 12');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `name_of_company` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `zip_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`) VALUES
(1, 'Leslie', 'Andrews', 'leslie@luv2code.com'),
(2, 'Emma', 'Baumgarten', 'emma@luv2code.com'),
(3, 'Avani', 'Gupta', 'avani@luv2code.com'),
(4, 'Yuri', 'Petrov', 'yuri@luv2code.com'),
(5, 'Juan', 'Vega', 'juan@luv2code.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `category`, `description`, `image_url`, `name`, `price`) VALUES
(1, 'Hiking', 'Survival tool with multiple functions', 'e89bcb67-8738-4ba4-9c27-d29ac3477204_bo_sinh_ton.png', 'Multi-tool Knife', 75),
(2, 'Survival', 'Essential tools in one kit', 'caed8f46-2243-41d8-9e58-6294fd623528_bo_so_cuu.png', 'Emergency Survival Kit', 45),
(3, 'Survival', 'Keeps you warm in low temperatures', 'a7ea6938-6308-43e6-a44c-911e736c43dd_tui_chong_lanh.png', 'Sleeping Bag - Cold Weather', 65),
(4, 'Survival', 'Clean water anywhere', '9035ada2-c870-4b75-a411-9f329cc75a6a_bo_loc_nuoc.png', 'Portable Water Filter', 40),
(5, 'Tents', 'Lightweight tent for 2 people', '8e224634-7f83-441a-a839-d3a02b8942bd_trai_2ng.png', 'Camping Tent 2-Person', 100),
(6, 'Lighting', 'Hands-free lighting for night hiking', '7f3a3ab4-0f3b-487e-a61c-e2fe7724d2d3_den_led.png', 'Headlamp LED Rechargeable', 35),
(7, 'Tents', 'Spacious tent for family trips', '3f6d25a1-ff82-4fa4-b522-42183bf5d3c7_trai_4ng.png', 'Camping Tent 4-Person', 180),
(8, 'Coking', 'Compact and reliable stove', '02c9f219-78e0-4893-98b2-8b0ed42c2068_bep_ga.png', 'Portable', 65);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `product_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `password`, `age`) VALUES
(23, 'Vu Phan', 'Hoai Nam', 'hoainam@gmail.com', NULL, NULL),
(24, 'Ho Tan', 'Sanh', 'tansanh@gmail.com', NULL, NULL),
(25, 'Tran Minh', 'Huy', 'minhhuy@gmail.com', NULL, NULL),
(30, 'Dao Thi', 'Thanh Huyen', 'daothithanhhuyen@gmail.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('hung', '{bcrypt}$2a$12$kKC38.grPJsUcUO4jrwgIelXRzqdVvwGH/sdCHAYDcxobqWzUOCJC', b'1'),
('nam', '{bcrypt}$2a$12$bGQ0IQ8wJQnfVwXhgbGe0edDVIO42/tSn93/Kq6zFZ01/4ySqFWju', b'1'),
('tru', '{bcrypt}$2a$12$PtHy.7IkbIi8/h3GLzo2Ne3/vtDrP.519859Vczr9YPH8h5SJfA82', b'1'),
('truong', '{bcrypt}$2a$12$OdTqHi5ZI6EBs/uJJx84q.LDpl.FeVuRHLvoQiZr4ktLAOt68p8UW', b'1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `authorities_idx_1` (`username`,`authority`);

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK64t7ox312pqal3p7fg9o503c2` (`user_id`);

--
-- Chỉ mục cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  ADD KEY `FKl7je3auqyq1raj52qmwrgih8x` (`product_id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT cho bảng `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Các ràng buộc cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `FKl7je3auqyq1raj52qmwrgih8x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
