<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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

    </head>
    <body>
        <div class="container">
            <!--thanh dieu huong-->
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="index.html">Footwear</a></div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li class="active"><a href="orderList">Back</a></li>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </nav>
            <h1>Order Detail</h1>
            <div class="tab-content" id="orders-table-tab-content">
                <div class="tab-pane fade show active" id="orders-all" role="tabpanel" aria-labelledby="orders-all-tab">
                    <div class="app-card app-card-orders-table shadow-sm mb-5">
                        <div class="app-card-body">

                            <div class="table-responsive">
                                <!-- Thông tin khách hàng -->
                                <h3>Customer Information</h3>
                                <p>Customer ID: ${account.accountID}</p>
                                <p>Customer Name: ${account.fullName}</p>
                                <p>Customer Email: ${account.email}</p>
                                <p>Customer Phone: ${account.phoneNumber}</p>

                                <!-- Chi tiết đơn hàng -->
                                <h3>Order Details</h3>
                                <table class="table app-table-hover mb-0 text-left">
                                    <thead>
                                        <tr>
                                            <th>Order Detail ID</th>
                                            <th>Product ID</th>
                                            <th>Stock ID</th>
                                            <th>Quantity</th>
                                            <th>Unit Price</th>
                                            <th>Avatar</th> <!-- Cột Avatar -->
                                            <th>Price</th>  <!-- Cột Price -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="detail" items="${orderDetails}">
                                            <tr>
                                                <td>${detail.orderDetailID}</td>
                                                <td>${detail.productID}</td>
                                                <td>${detail.stockID}</td>
                                                <td>${detail.quantity}</td>
                                                <td>${detail.unitPrice}</td>
                                                <td>
                                                    <img src="${productInfoMap[detail.productID]['avatarP']}" alt="Product Image" width="50" height="50"/>
                                                </td> <!-- Hiển thị avatarP -->
                                                <td>${productInfoMap[detail.productID]['price']}</td>  <!-- Hiển thị price -->
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div><!--//table-responsive-->
                        </div><!--//app-card-body-->		
                    </div><!--//app-card-->
                </div>
            </div>
        </div>
    </body>
</html>
