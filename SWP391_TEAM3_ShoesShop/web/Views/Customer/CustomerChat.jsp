<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li class="active"><a href="AccoutListController">Back</a></li>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </nav>
    <h2>Chat with Us</h2>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" style="color: red;">
            ${errorMessage}
        </div>
    </c:if>

    <!-- Hiển thị danh sách tin nhắn -->
<div id="chatContainer">
    <c:forEach var="message" items="${messages}">
        <p>
            <strong>
                <c:choose>
                    <c:when test='${message.senderId == customer.accountID}'>${customer.fullName}</c:when>
                    <c:otherwise>Admin/Staff</c:otherwise>
                </c:choose>:
            </strong>
            ${message.content}
            <br>
            <small><i> ${message.sendTime}</i></small> <!-- Hiển thị thời gian gửi -->
        </p>
    </c:forEach>
</div>

        
        

    <!-- Form gửi tin nhắn -->
    <form action="SendMessageServlet" method="post">
        <input type="hidden" name="conversationId" value="${conversationId}">
        <input type="hidden" name="senderId" value="${customer.accountID}">
        <input type="hidden" name="senderRole" value="3"> <!-- 3 là customer -->
        <textarea name="content" rows="4" cols="50" required></textarea><br>
        <button type="submit">Send</button>
    </form>
</body>
</html>
