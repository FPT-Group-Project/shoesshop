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
                                    <li class="active"><a href="AccoutListController">Back</a></li>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </nav>
            <h1>Order List</h1>
            <div class="tab-content" id="orders-table-tab-content">
                <div class="tab-pane fade show active" id="orders-all" role="tabpanel" aria-labelledby="orders-all-tab">
                    <div class="app-card app-card-orders-table shadow-sm mb-5">
                        <div class="app-card-body">
                            <div class="table-responsive">
                                <table class="table app-table-hover mb-0 text-left">
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Account ID</th>
                                            <th>Customer Name</th>
                                            <th>Address</th>
                                            <th>Total Price</th>
                                            <th>Order Date</th>
                                            <th>Approve Date</th>
                                            <th>Send Date</th>
                                            <th>Arrival Date</th>
                                            <th>Status</th>
                                            <th>Details</th>
                                            <th>Update</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="order" items="${orders}">
                                            <tr>
                                                <td>${order.orderID}</td>
                                                <td>${order.accountID}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${order.customerName != null}">
                                                            ${order.customerName}
                                                        </c:when>
                                                        <c:otherwise>
                                                            N/A <!-- Nếu không có tên khách hàng -->
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${order.address}</td>
                                                <td>${order.totalPrice}</td>
                                                <td>${order.orderDate}</td>
                                                <td>${order.approveDate != null ? order.approveDate : 'N/A'}</td>
                                                <td>${order.sendDate != null ? order.sendDate : 'N/A'}</td>
                                                <td>${order.arrivalDate != null ? order.arrivalDate : 'N/A'}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${order.statusID == 1}">
                                                            pending
                                                        </c:when>
                                                        <c:when test="${order.statusID == 2}">
                                                            approved
                                                        </c:when>
                                                        <c:when test="${order.statusID == 3}">
                                                            delivering
                                                        </c:when>
                                                        <c:when test="${order.statusID == 4}">
                                                            delivered
                                                        </c:when>
                                                        <c:when test="${order.statusID == 5}">
                                                            rejected
                                                        </c:when>
                                                        <c:otherwise>
                                                            Unknown Status
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td>
    <a href="orderDetail?orderID=${order.orderID}&accountID=${order.accountID}&orderDate=${order.orderDate}&approveDate=${order.approveDate}&sendDate=${order.sendDate}&arrivalDate=${order.arrivalDate}">
        View Details
    </a>
</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${order.statusID == 1}"> <!-- Pending -->
                                                            <form action="updateOrderStatus" method="post">
                                                                <input type="hidden" name="orderID" value="${order.orderID}" />
                                                                <button type="submit" name="action" value="approve">Approve</button>
                                                                <button type="submit" name="action" value="reject">Reject</button>
                                                            </form>
                                                        </c:when>
                                                        <c:when test="${order.statusID == 2}"> <!-- Approved -->
                                                            <form action="updateOrderStatus" method="post">
                                                                <input type="hidden" name="orderID" value="${order.orderID}" />
                                                                <button type="submit" name="action" value="delivering">Delivering</button>
                                                            </form>
                                                        </c:when>
                                                        <c:when test="${order.statusID == 3}"> <!-- Delivering -->
                                                            Delivering
                                                        </c:when>
                                                        <c:when test="${order.statusID == 4}"> <!-- Delivered -->
                                                            Delivered
                                                        </c:when>
                                                        <c:otherwise>
                                                            Rejected
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>

                            </div><!--//table-responsive-->

                        </div><!--//app-card-body-->		
                    </div><!--//app-card-->
                </div>
            </div>
    </body>
</html>

