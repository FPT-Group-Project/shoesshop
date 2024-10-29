<%-- 
    Document   : homePage
    Created on : Sep 26, 2024, 3:58:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Home Page</title>
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
           .wishlist-icon {
    position: absolute; /* Định vị tuyệt đối */
    top: 10px; /* Cách từ phía trên */
    right: 10px; /* Cách từ phía bên phải */
    z-index: 10; /* Đảm bảo biểu tượng nằm trên các phần khác */
    color: red; /* Màu sắc của biểu tượng */
}

.icon-heart {
    font-size: 24px; /* Kích thước của biểu tượng */
}
.icon-heart {
    color: red; /* Màu của biểu tượng trái tim */
    margin-left: 10px; /* Khoảng cách giữa tên sản phẩm và biểu tượng */
    cursor: pointer; /* Hiện con trỏ như một liên kết */
    font-size: 1.2em; /* Kích thước biểu tượng */
}

.icon-heart:hover {
    color: darkred; /* Màu khi di chuột qua biểu tượng */
}


</style>
    </head>
    <body>

        <div id="page">
            <!--nav bar-->
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="home">Footwear</a></div>
                            </div>
                            <div class="col-sm-5 col-md-3">
                                <form action="search" method="GET" class="search-wrap">
                                    <div class="form-group">
                                        <input name="searchQuery" type="search" class="form-control search" placeholder="Search">
                                        <button class="btn btn-primary submit-search text-center" type="submit"><i class="icon-search"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li class="active"><a href="home">Home</a></li>
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
                                    <li><a href="orderHistory">Order History</a></li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact">Contact</a></li>
                                        <c:if test="${sessionScope.acc==null}">
                                        <li class="login">
                                            <a href="login">Login</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.acc!=null}">
                                        <li class="logout">
                                            <a href="logout">Logout</a>
                                        </li>
                                        <li class="profile">
                                            <a href="profile?id=${acc.getAccountID()}">Profile</a>
                                        </li>
                                    </c:if>
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
            <!---->

            <div class="colorlib-intro">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 text-center">
                            <h2 class="intro">Shoes Shop</h2>
                        </div>
                    </div>
                </div>
            </div>




            <div class="colorlib-product">
                <div class="container">
                    <form action="products" method="get">
                        <div class="row" >
                            <div class="col col-sm-2">
                                <hr/>
                                <h3>Brands:</h3>
                                <ul>
                                    <c:forEach items='${brands}' var='b'>
                                        <li>
                                            <input type="checkbox" name="brands" value="${b.getBrandID()}" id="brand${b.getBrandID()}">
                                            <label for="${b.getBrandID()}">${b.getBrandName()}</label>
                                        </li>
                                    </c:forEach>
                                    <input type="hidden" value="${checkedBrands}" id="checkedBrands">
                                </ul>
                                <hr/>
                                <h3>Price:</h3>
                                <div style="display: flex; justify-content: center; gap: 10%">
                                    <input type="text" name="from" id="from" placeholder="From" value="${from}" style="width: 40%; text-align: center;" onkeydown="if (event.key === 'Enter')
                                                event.preventDefault();"/>

                                    <input type="text" name="to" id="to" placeholder="To" value="${to}" style="width: 40%; text-align: center;" onkeydown="if (event.key === 'Enter')
                                                event.preventDefault();"/>
                                </div>
                                <hr/>
                                <div style="display: flex; justify-content: center">
                                    <button type="submit" style="width: 100%; background-color: #90ccbc; color:white;">Filter</button>
                                </div>

                                <hr/>

                                <script>
                                    function submitByEnterFalse(event) {
                                        if (event.keyCode === 13) {
                                            event.preventDefault();
                                        }
                                    }
                                    window.onload = function () {
                                        const brands = document.querySelectorAll('input[name="brands"]');
                                    <c:forEach items="${checkedBrands}" var="cb">
                                        brands.forEach(b => {
                                            if (Number(b.value) === Number(${cb})) {
                                                b.checked = true;
                                            }
                                        });
                                    </c:forEach>
                                    };
                                </script>
                               
                            </div>
                                                
                            <div class="col">
                                <div class="row row-pb-md">
                                   <c:forEach items="${list}" var="p">
    <div class="col-lg-3 mb-4 text-center">
        <div class="product-entry border" style="position: relative;"> <!-- Thêm position: relative -->
            <a href="productDetail?id=${p.getProductId()}" class="prod-img">
                <img src="ImageProductAvt/${p.getAvatarP()}" class="img-fluid" alt="Free html5 bootstrap 4 template">
            </a>
            <div class="wishlist-icon">
                <a href="addWishlist?id=${p.getProductId()}" title="Add to Wishlist">
                    <i class="icon-heart"></i> <!-- Biểu tượng trái tim -->
                </a>
            </div>
            <div class="desc">
                <h2><a href="productDetail?id=${p.getProductId()}">${p.getProductName()}</a></h2>
                <span class="price">$${p.getPrice()}</span>
            </div>
        </div>
    </div>
</c:forEach>
                                </div>
                                <c:choose>
                                    <c:when test="${list!=null}">
                                        <div class="pagination_container">  
                                            <ul class="pagination">
                                                <button name="page" value="${atPage-1}" type="submit" ${atPage<=1?'disabled':''}><li class="pagination__btn"><span class="material-icons">chevron_left</span></li></button>
                                                        <c:forEach var="n" begin="1" end="${pagesNumber}">
                                                    <button name="page" value="${n}"><li  class="pagination__numbers" ${n==atPage?'active':''}>${n}</li></button>
                                                        </c:forEach>
                                                <button name="page" value="${atPage+1}" ${atPage>=pagesNumber?'disabled':''}><li class="pagination__btn"><span class="material-icons">chevron_right</span></li></button>
                                            </ul>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <style>
                                    a.disabled{
                                        pointer-events: none;
                                        cursor: default;
                                    }
                                    a.disabled .material-icons{
                                        color: #ffffff;
                                    }
                                    *,
                                    *::before,
                                    *::after {
                                        margin: 0;
                                        padding: 0;
                                        box-sizing: inherit;
                                    }

                                    html {
                                        box-sizing: border-box;
                                        font-size: 62.5%;
                                        overflow-y: scroll;
                                        font-family: "Poppins", sans-serif;
                                        letter-spacing: 0.6px;
                                        line-height: 1.4;
                                        -webkit-user-select: none;
                                        backface-visibility: hidden;
                                        -webkit-font-smoothing: subpixel-antialiased;
                                    }

                                    .pagination_container {
                                        display: flex;
                                        flex-direction: column;
                                        justify-content: center;
                                        align-items: center;
                                        text-align: center;
                                        color: #2d4848;
                                    }

                                    ul {
                                        list-style-type: none;
                                    }

                                    .pagination {
                                        display: flex;
                                        justify-content: center;
                                        align-items: center;
                                        height: 5rem;
                                        margin: 3rem;
                                        border-radius: 0.6rem;
                                        background: #ffffff;
                                        box-shadow: 0 0.8rem 2rem rgba(90, 97, 129, 0.05);
                                    }

                                    .pagination__numbers,
                                    .pagination__btn,
                                    .pagination__dots {
                                        display: flex;
                                        justify-content: center;
                                        align-items: center;
                                        margin: 0.8rem;
                                        font-size: 1.4rem;
                                        cursor: pointer;
                                    }

                                    .pagination__dots {
                                        width: 2.6rem;
                                        height: 2.6rem;
                                        color: #90ccbc;
                                        cursor: initial;
                                    }

                                    .pagination__numbers {
                                        width: 2.6rem;
                                        height: 2.6rem;
                                        border-radius: 0.4rem;
                                    }

                                    .pagination__numbers:hover {
                                        color: #90ccbc;
                                    }

                                    .pagination__numbers.active {
                                        color: #ffffff;
                                        background: #90ccbc;
                                        font-weight: 600;
                                        border: 1px solid #90ccbc;
                                    }

                                    .pagination__btn {
                                        color: #90ccbc;
                                        pointer-events: none;
                                    }

                                    .pagination__btn.active:hover {
                                        color: #90ccbc;
                                    }
                                </style>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="colorlib-partner">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
                            <h2>Trusted Partners</h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col partner-col text-center">
                            <img src="images/brand-1.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
                        </div>
                        <div class="col partner-col text-center">
                            <img src="images/brand-2.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
                        </div>
                        <div class="col partner-col text-center">
                            <img src="images/brand-3.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
                        </div>
                        <div class="col partner-col text-center">
                            <img src="images/brand-4.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
                        </div>
                        <div class="col partner-col text-center">
                            <img src="images/brand-5.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
                        </div>
                    </div>
                </div>
            </div>
            <!--footer-->
            <footer id="colorlib-footer" role="contentinfo">
                <div class="container">
                    <div class="row row-pb-md">
                        <div class="col footer-col colorlib-widget">
                            <h4>About Footwear</h4>
                            <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life</p>
                            <p>
                            <ul class="colorlib-social-icons">
                                <li><a href="#"><i class="icon-twitter"></i></a></li>
                                <li><a href="#"><i class="icon-facebook"></i></a></li>
                                <li><a href="#"><i class="icon-linkedin"></i></a></li>
                                <li><a href="#"><i class="icon-dribbble"></i></a></li>
                            </ul>
                            </p>
                        </div>
                        <div class="col footer-col colorlib-widget">
                            <h4>Customer Care</h4>
                            <p>
                            <ul class="colorlib-footer-links">
                                <li><a href="#">Contact</a></li>
                                <li><a href="#">Returns/Exchange</a></li>
                                <li><a href="#">Gift Voucher</a></li>
                                <li><a href="#">Wishlist</a></li>
                                <li><a href="#">Special</a></li>
                                <li><a href="#">Customer Services</a></li>
                                <li><a href="#">Site maps</a></li>
                            </ul>
                            </p>
                        </div>
                        <div class="col footer-col colorlib-widget">
                            <h4>Information</h4>
                            <p>
                            <ul class="colorlib-footer-links">
                                <li><a href="#">About us</a></li>
                                <li><a href="#">Delivery Information</a></li>
                                <li><a href="#">Privacy Policy</a></li>
                                <li><a href="#">Support</a></li>
                                <li><a href="#">Order Tracking</a></li>
                            </ul>
                            </p>
                        </div>

                        <div class="col footer-col">
                            <h4>News</h4>
                            <ul class="colorlib-footer-links">
                                <li><a href="blog.html">Blog</a></li>
                                <li><a href="#">Press</a></li>
                                <li><a href="#">Exhibitions</a></li>
                            </ul>
                        </div>

                        <div class="col footer-col">
                            <h4>Contact Information</h4>
                            <ul class="colorlib-footer-links">
                                <li>291 South 21th Street, <br> Suite 721 New York NY 10016</li>
                                <li><a href="tel://1234567920">+ 1235 2355 98</a></li>
                                <li><a href="mailto:info@yoursite.com">info@yoursite.com</a></li>
                                <li><a href="#">yoursite.com</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="copy">
                    <div class="row">
                        <div class="col-sm-12 text-center">
                            <p>
                                <span><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></span> 
                                <span class="block">Demo Images: <a href="http://unsplash.co/" target="_blank">Unsplash</a> , <a href="http://pexels.com/" target="_blank">Pexels.com</a></span>
                            </p>
                        </div>
                    </div>
                </div>
            </footer>
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


