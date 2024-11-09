<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        <!-- CSS in JSP -->
        <style>
            /* Đặt chiều cao cho phần chat */
            .messages {
                max-height: 400px; /* Chiều cao tối đa */
                overflow-y: auto;  /* Cho phép cuộn dọc */
                border: 1px solid #ccc; /* Đường viền xung quanh */
                padding: 10px; /* Khoảng cách giữa nội dung và viền */
                background-color: #f9f9f9; /* Màu nền cho khung chat */
                margin-bottom: 20px; /* Khoảng cách dưới khung chat */
            }

            .send-message form {
                margin-top: 20px;
            }

            .send-message textarea {
                width: 100%;
                height: 100px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                resize: none;
            }

            .send-message button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .send-message button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- thanh dieu huong-->
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
            <div class="container">
                <table>
                    <td>
                        <!-- Hiển thị thông tin khách hàng -->
                        <div class="customer-info">
                            <h3>Khách hàng: ${customer.fullName}</h3>
                            <p>Email: ${customer.email}</p>
                            <p>Phone number: ${customer.phoneNumber}</p>
                        </div>

                        <!-- Hiển thị danh sách tin nhắn -->
                        <div class="messages">
                            <h4>Your conversation</h4>
                            <ul>
                                <c:forEach var="message" items="${messages}">
                                    <li>
                                        <strong>
                                            <c:choose>
                                                <c:when test="${message.senderId == customer.accountID}">
                                                    ${customer.fullName} (Customer)
                                                </c:when>
                                                <c:otherwise>Admin/Staff</c:otherwise>
                                            </c:choose>:
                                        </strong>
                                        <p>${message.content}
                                        <br>
                                        <small><i> ${message.sendTime}</i></small></p>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <!-- Form gửi tin nhắn mới -->
                        <div class="send-message">
                            <h4>Type a message: </h4>
                            <form action="StaffSendMessageServlet" method="POST">
                                <input type="hidden" name="conversationId" value="${conversation.conversationId}" />
                                <input type="hidden" name="senderId" value="${sessionScope.accountID}" />
                                <input type="hidden" name="senderRole" value="${sessionScope.roleID}" />
                                <textarea name="messageContent" placeholder="Type your message" required></textarea>
                                <button type="submit">Send</button>
                            </form>
                        </div>
                    </td>
                </table>
            </div>
        </div>
    </body>
</html>
