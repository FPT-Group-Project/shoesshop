<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.OrderDetail" %> <!-- Thay đổi theo package thực tế của bạn -->
<%@ page import="Models.Product" %>
<%@ page import="Models.Stock" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Footwear - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">

        <!-- Animate.css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="css/icomoon.css">
        <!-- Ion Icon Fonts-->
        <link rel="stylesheet" href="css/ionicons.min.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Magnific Popup -->
        <link rel="stylesheet" href="css/magnific-popup.css">

        <!-- Flexslider  -->
        <link rel="stylesheet" href="css/flexslider.css">

        <!-- Owl Carousel -->
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">

        <!-- Date Picker -->
        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <!-- Flaticons  -->
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

        <!-- Theme style  -->
        <link rel="stylesheet" href="css/style.css">
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            body {
                background-color: #f5f5f5;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
            }

            .orders-section {
                background-color: #fff;
                padding: 20px;
            }

            .order-item {
                border: 1px solid #e0e0e0;
                border-radius: 4px;
                padding: 15px;
                margin-bottom: 15px;
            }

            .order-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
            }

            .shop-name {
                font-weight: bold;
            }

            .shop-actions button {
                background-color: #ff5722;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 3px;
                cursor: pointer;
                margin-left: 10px;
            }

            .order-status {
                font-weight: bold;
            }

            .order-status.cancelled {
                color: red;
            }

            .order-status.completed {
                color: green;
            }

            .order-info {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 10px;
            }

            .product-img {
                width: 100px;
                height: 100px;
                object-fit: cover;
                margin-right: 15px;
                margin-bottom: 10px;
            }

            .product-details {
                flex-grow: 1;
            }

            .order-footer {
                text-align: right;
            }

            .order-price {
                font-weight: bold;
                font-size: 18px;
            }

            .order-total {
                margin-top: 5px;
                color: #555;
            }

            .system-note, .rating-note {
                font-size: 14px;
                color: #888;
                margin-bottom: 10px;
            }

            .order-actions {
                display: flex;
                justify-content: flex-end;
            }

            .order-actions .order-btn {
                background-color: #ff5722;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                margin-left: 10px;
                cursor: pointer;
            }

            .order-actions .order-btn:hover {
                background-color: #e64a19;
            }
            .orders-section {
                padding: 20px;
                background-color: #f9f9f9;
            }

            .order-item {
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 15px;
                margin-bottom: 20px;
                background-color: #fff;
            }

            .order-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .order-header span {
                font-weight: bold;
            }

            .order-details {
                margin-top: 10px;
            }

            .order-details table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }

            .order-details th, .order-details td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }

            .order-details th {
                background-color: #f2f2f2;
            }

            .order-footer {
                margin-top: 15px;
                font-size: 1.2em;
            }

            .order-actions {
                margin-top: 10px;
            }

            .order-btn {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .order-btn:hover {
                background-color: #0056b3;
            }

        </style>
    </head>
    <body>

        <div id="page">
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="home">Footwear</a></div>
                            </div>
                            <div class="col-sm-5 col-md-3">
                                <form action="#" class="search-wrap">
                                    <div class="form-group">
                                        <input type="search" class="form-control search" placeholder="Search">
                                        <button class="btn btn-primary submit-search text-center" type="submit"><i class="icon-search"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li><a href="home">Home</a></li>
                                    <li class="has-dropdown">
                                        <a href="men.html">Men</a>
                                        <ul class="dropdown">
                                            <li><a href="product-detail.html">Product Detail</a></li>
                                            <li><a href="cart">Shopping Cart</a></li>
                                            <li><a href="checkout.html">Checkout</a></li>
                                            <li><a href="order-complete.html">Order Complete</a></li>
                                            <li><a href="add-to-wishlist.html">Wishlist</a></li>
                                        </ul>
                                    </li>
                                    <li class="active"><a href="women.html">Women</a></li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                    <li class="cart"><a href="cart"><i class="icon-shopping-cart"></i> Cart [0]</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="sale">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-8 offset-sm-2 text-center">
                                <div class="row">
                                    <div class="owl-carousel2">
                                        <div class="item">
                                            <div class="col">
                                                <h3><a href="#">25% off (Almost) Everything! Use Code: Summer Sale</a></h3>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="col">
                                                <h3><a href="#">Our biggest sale yet 50% off all summer shoes</a></h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>


            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="home">Home</a></span> / <span>Women</span></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="breadcrumbs-two">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="breadcrumbs-img" style="background-image: url(images/cover-img-1.jpg);">

                            </div>
                            <% if (request.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger">
                                <%= request.getAttribute("errorMessage") %>
                            </div>
                            <% } %>
                            <div class="container">
                                <main>
                                    <section class="orders-section">
                                        <c:forEach var="order" items="${orderList}">
                                            <div class="order-item">
                                                Order History
                                                <div class="order-header">
                                                    <span class="shop-name">Shoe Shop</span>
                                                    <span class="order-id">Mã đơn hàng: ${order.orderID}</span>
                                                    <span class="order-status">
                                                        <c:choose>
                                                            <c:when test="${order.statusID == 1}">Pending</c:when>
                                                            <c:when test="${order.statusID == 2}">Approved</c:when>
                                                            <c:when test="${order.statusID == 3}">Delivering</c:when>
                                                            <c:when test="${order.statusID == 4}">Delivered</c:when>
                                                            <c:when test="${order.statusID == 5}">Rejected</c:when>
                                                            <c:when test="${order.statusID == 6}">Canceled</c:when>
                                                            <c:otherwise>Không xác định</c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                </div>

                                                <!-- Thông tin sản phẩm trong đơn hàng -->
                                                <div class="order-details">
                                                    <c:forEach var="detail" items="${order.orderDetails}">
                                                        <div class="product-item">

                                                            <div class="product-info">
                                                                <img src="ImageProductAvt/${detail.imageUrl}" alt="${detail.productID.productName}" style="height: 100px; width: 100px" class="product-image" />
                                                                <span class="product-name">${detail.productID.productName}</span>
                                                                <span class="product-size">Kích thước: ${detail.size}</span>
                                                                <span class="product-color">Màu sắc: ${detail.color}</span>
                                                                <span class="product-quantity">Số lượng: ${detail.quantity}</span>
                                                                <span class="product-price">Giá: ₫${detail.unitPrice}</span>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>

                                                <!-- Tổng giá trị đơn hàng -->
                                                <div class="order-footer">
                                                    <span class="order-total">Tổng tiền: ₫${order.totalPrice}</span>
                                                </div>

                                                <!-- Hành động cho đơn hàng -->
                                                <div class="order-actions">
                                                    <button class="order-btn" onclick="window.location.href = 'orderDetail2?id=${order.orderID}'">View Detail</button>

                                                    <button class="order-btn" onclick="confirmOrder(${order.orderID})">Order Confirmation</button>
                                                    <button class="order-btn" onclick="cancelOrder(${order.orderID})">Cancel Order</button> <!-- Nút hủy đơn hàng -->
                                                    <script>
                                                        function confirmOrder(orderID) {
                                                            if (confirm("Are you sure you want to confirm this order?")) {
                                                                window.location.href = 'confirmOrder?orderID=' + orderID;
                                                            }
                                                        }
                                                        function cancelOrder(orderID) {
                                                            if (confirm("Are you sure you want to cancel this order?")) {
                                                                window.location.href = 'cancelOrder?orderID=' + orderID; // Thay đổi đường dẫn theo yêu cầu của bạn
                                                            }
                                                        }
                                                    </script>

                                                </div>
                                            </div> <!-- Kết thúc order-item -->
                                        </c:forEach>

                                        <!-- Hiển thị nếu không có đơn hàng -->
                                        <c:if test="${empty orderList}">
                                            <p>Không có đơn hàng nào.</p>
                                        </c:if> 
                                    </section>
                                </main>



                            </div>



                        </div>
                    </div>
                </div>

                <div class="colorlib-featured">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4 text-center">
                                <div class="featured">
                                    <div class="featured-img featured-img-2" style="background-image: url(images/img_bg_2.jpg);">
                                        <h2>Casuals</h2>
                                        <p><a href="#" class="btn btn-primary btn-lg">Shop now</a></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 text-center">
                                <div class="featured">
                                    <div class="featured-img featured-img-2" style="background-image: url(images/women.jpg);">
                                        <h2>Dress</h2>
                                        <p><a href="#" class="btn btn-primary btn-lg">Shop now</a></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 text-center">
                                <div class="featured">
                                    <div class="featured-img featured-img-2" style="background-image: url(images/item-11.jpg);">
                                        <h2>Sports</h2>
                                        <p><a href="#" class="btn btn-primary btn-lg">Shop now</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="gototop js-top">
                    <a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
                </div>

                <!-- jQuery -->
                <script src="js/jquery.min.js"></script>
                <!-- popper -->
                <script src="js/popper.min.js"></script>
                <!-- bootstrap 4.1 -->
                <script src="js/bootstrap.min.js"></script>
                <!-- jQuery easing -->
                <script src="js/jquery.easing.1.3.js"></script>
                <!-- Waypoints -->
                <script src="js/jquery.waypoints.min.js"></script>
                <!-- Flexslider -->
                <script src="js/jquery.flexslider-min.js"></script>
                <!-- Owl carousel -->
                <script src="js/owl.carousel.min.js"></script>
                <!-- Magnific Popup -->
                <script src="js/jquery.magnific-popup.min.js"></script>
                <script src="js/magnific-popup-options.js"></script>
                <!-- Date Picker -->
                <script src="js/bootstrap-datepicker.js"></script>
                <!-- Stellar Parallax -->
                <script src="js/jquery.stellar.min.js"></script>
                <!-- Main -->
                <script src="js/main.js"></script>

                </body>
                </html>

