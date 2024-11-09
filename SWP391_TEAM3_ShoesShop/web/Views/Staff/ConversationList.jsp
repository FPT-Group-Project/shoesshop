<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
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

<div class="container mt-4">
    <h2>Conversation List</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Conversation ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Message Preview</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="conversation" items="${conversations}">
                <tr>
                    <td>${conversation.conversationId}</td>
                    
                    <!-- Hiển thị thông tin khách hàng -->
                    <td>
                        <c:forEach var="customer" items="${customers}">
                            <c:if test="${customer.accountID == conversation.customerId}">
                                ${customer.fullName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="customer" items="${customers}">
                            <c:if test="${customer.accountID == conversation.customerId}">
                                ${customer.email}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="customer" items="${customers}">
                            <c:if test="${customer.accountID == conversation.customerId}">
                                ${customer.phoneNumber}
                            </c:if>
                        </c:forEach>
                    </td>
                    
                    <!-- Hiển thị tin nhắn gần nhất -->
                    <td>
                        <c:forEach var="message" items="${latestMessages}">
                            <c:if test="${message.conversationId == conversation.conversationId}">
                                ${message.content}
            <br>
            <small><i> ${message.sendTime}</i></small>
                            </c:if>
                        </c:forEach>
                    </td>
                    
                    <!-- Thêm liên kết để xem chi tiết cuộc trò chuyện -->
                    <td><a href="ConversationDetailServlet?id=${conversation.conversationId}" class="btn btn-primary">View</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
