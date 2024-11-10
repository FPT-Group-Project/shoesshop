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
            <!--thanh dieu huong-->
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="index.html">Footwear</a></div>
                            </div>
                            <div class="col-sm-5 col-md-3">

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <!--quay lai control-->
                                    <li class="active"><a href="AccoutListController">Back</a></li>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </nav>
    <h2>Add New Coupon</h2>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty message}">
        <div style="color: green; font-weight: bold;">${message}</div>
    </c:if>

    <form action="addCoupon" method="post">
        <table>
            <tr>
                <td><label for="codeName">Coupon Code:</label></td>
                <td><input type="text" id="codeName" name="codeName" required/></td>
            </tr>
            <tr>
                <td><label for="discount">Discount:</label></td>
                <td><input type="number" id="discount" name="discount" step="0.01" required/></td>
            </tr>
            <tr>
                <td><label for="couponType">Coupon Type:</label></td>
                <td><input type="text" id="couponType" name="couponType" required/></td>
            </tr>
            <tr>
                <td><label for="quantity">Quantity:</label></td>
                <td><input type="number" id="quantity" name="quantity" required/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add Coupon"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
