<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.News" %>
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
        <script>
            window.onload = function () {
                // Xóa dữ liệu trong sessionStorage và localStorage khi tải trang
                sessionStorage.clear();
                localStorage.clear();

                // Khởi tạo lại WebChatClient
                new CozeWebSDK.WebChatClient({
                    config: {
                        bot_id: '7434940179271942152',
                    },
                    componentProps: {
                        title: ' Shoes Shop BotChat',
                    },
                });
            };

            // Cài đặt sự kiện trước khi trang bị thoát
            window.addEventListener('beforeunload', function () {
                sessionStorage.clear();
                localStorage.clear();
            });
        </script>
        <script src="https://sf-cdn.coze.com/obj/unpkg-va/flow-platform/chat-app-sdk/1.0.0-beta.4/libs/oversea/index.js"></script>
    </head>

    <body>

        <div class="colorlib-loader"></div>

        <div id="page">
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="index.html">Footwear</a></div>
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
                                            <li><a href="cart.html">Shopping Cart</a></li>
                                            <li><a href="checkout.html">Checkout</a></li>
                                            <li><a href="order-complete.html">Order Complete</a></li>
                                            <li><a href="add-to-wishlist.html">Wishlist</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="women.html">Women</a></li>
                                    <li class="active"><a href="about.html">News</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                    <li class="cart"><a href="cart.html"><i class="icon-shopping-cart"></i> Cart </a></li>
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
                            <p class="bread"><span><a href="index.html">Home</a></span> / <span>News</span></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="row">
                            <c:forEach var="news" items="${newsList}" varStatus="status">
                                <div class="col-md-4"> <!-- Mỗi phần tử tin tức chiếm 1/3 chiều rộng -->
                                    <div class="row">
                                    <div class="about-wrap row-pb-lg"> 
                                        <div>
                                            <img src="${pageContext.request.contextPath}/ImageNews/${news.image}" alt="${news.title}" style="width: 100%; height: 250px;" />
                                        </div>
                                        <h3> <a href="NewsDetailController?id=${news.newsID}">${news.title}</a></h3>
                                    </div></div>
                                </div>

                                <c:if test="${status.index % 3 == 2 || status.last}"> 
                                </div><div class="row">
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>


                    <div class="col-md-4"> <!-- Cột bên phải -->
                        <h3>Top 5 Latest Posts</h3>
                        <c:forEach var="topNews" items="${newsListTop5}">
                            <div class="row">
                                <div class="col-md-2">
 <img src="${pageContext.request.contextPath}/ImageNews/${topNews.image}" alt="${topNews.title}" style="width: 40px;" />                                </div>
                                <div class="col-md-8">
                                    <div class="top-news-item">
                                        <h4> <a href="NewsDetailController?id=${topNews.newsID}">${topNews.title}</a></h4>
                                    </div>
                                </div>
                            </div>
                            <hr />
                        </c:forEach>
                    </div>
                </div>
            </div>
            <nav class="app-pagination">
                <ul class="pagination justify-content-center">
                    <!-- Nút Previous -->
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage - 1}" tabindex="-1" aria-disabled="true">Previous</a>
                    </li>

                    <!-- Lặp qua các số trang -->
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- Nút Next -->
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                    </li>
                </ul>
            </nav>

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

