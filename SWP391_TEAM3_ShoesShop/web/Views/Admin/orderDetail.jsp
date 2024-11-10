<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Footwear - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- CSS links -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/flexslider.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
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
                                <h3>Customer & Order Information</h3>
                                <p><strong>Customer ID:</strong> ${account.accountID}</p>
                                <p><strong>Customer Name:</strong> ${account.fullName}</p>
                                <p><strong>Customer Email:</strong> ${account.email}</p>
                                <p><strong>Customer Phone:</strong> ${account.phoneNumber}</p>

<p><strong>Order Date:</strong> 
    <c:choose>
        <c:when test="${not empty param.orderDate}">
            ${param.orderDate}
        </c:when>
        <c:otherwise>N/A</c:otherwise>
    </c:choose>
</p>

<p><strong>Approve Date:</strong> 
    <c:choose>
        <c:when test="${not empty param.approveDate}">
            ${param.approveDate}
        </c:when>
        <c:otherwise>N/A</c:otherwise>
    </c:choose>
</p>

<p><strong>Send Date:</strong> 
    <c:choose>
        <c:when test="${not empty param.sendDate}">
            ${param.sendDate}
        </c:when>
        <c:otherwise>N/A</c:otherwise>
    </c:choose>
</p>

<p><strong>Arrival Date:</strong> 
    <c:choose>
        <c:when test="${not empty param.arrivalDate}">
            ${param.arrivalDate}
        </c:when>
        <c:otherwise>N/A</c:otherwise>
    </c:choose>
</p>


 

                                <h3>Order Details</h3>
                                <table class="table app-table-hover mb-0 text-left">
                                    <thead>
                                        <tr>
                                            <th>Order Detail ID</th>
                                            <th>Product ID</th>
                                            <th>Stock ID</th>
                                            <th>Quantity</th>
                                            <th>Price</th>

                                            <th>Unit Price</th>
                                            <th>Color</th>
                                            <th>Size</th>
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

                                                <td>${productInfoMap[detail.stockID]['price']}</td>
                                                <td>${colorMap[productInfoMap[detail.stockID]['color']]}</td>
                                                <td>${sizeMap[productInfoMap[detail.stockID]['size']]}</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>		
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
