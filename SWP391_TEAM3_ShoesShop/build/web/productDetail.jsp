<%-- 
    Document   : productDetail
    Created on : Sep 26, 2024, 3:58:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${p!=null?p.getProductName():'Not found'}</title>
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
                                <form action="searchProduct" method="get" class="search-wrap">
                                    <div class="form-group">
                                        <input type="search" class="form-control search" name="searchQuery" placeholder="Search" value="${searchQuery}">
                                        <button class="btn btn-primary submit-search text-center" type="submit"><i class="icon-search"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li><a href="home">Home</a></li>
                                    <li class="has-dropdown active">
                                        <a href="men.html">Men</a>
                                        <ul class="dropdown">
                                            <li><a href="product-detail.html">Product Detail</a></li>
                                            <li><a href="cart">Shopping Cart</a></li>
                                            <li><a href="checkout.html">Checkout</a></li>
                                            <li><a href="order-complete.html">Order Complete</a></li>
                                            <li><a href="add-to-wishlist.html">Wishlist</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="women.html">Women</a></li>
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
                            <p class="bread"><span><a href="home">Home</a></span> / <span>Product Details</span></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="colorlib-product">
                <form action="addToCart" method="get" onkeydown="submitByEnterFalse(event)">
                    <c:choose>
                        <c:when test="${p==null}">
                            <h1 style='text-align: center'>Can't find product</h1>
                        </c:when>
                        <c:otherwise>
                            <div class="container">
                                <div class="row row-pb-lg product-detail-wrap">
                                    <div class="col-sm-8">
                                        <div class="owl-carousel">
                                            <div class="item">
                                                <div class="product-entry border">
                                                    <div class="prod-img">
                                                        <img src="ImageProductAvt/${p.getAvatarP()}" class="img-fluid" alt="Avatar of product ${p.getProductName()}">
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="text" id="productId" value="${p.getProductId()}" style="display:none">
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <style>
                                            .custom-product-property-radio-label{
                                                color: white;
                                                display: flex;
                                                width: 100%;
                                                height: 100%;
                                                align-items: center;
                                                justify-content: center;
                                                transition: 0.5s;
                                            }
                                            .custom-product-property-radio-label:hover{
                                                background-color: #797979
                                            }
                                            input.custom-product-property-radio-input:checked + .custom-product-property-radio-label {
                                                background-color: #797979;
                                            }
                                        </style>
                                        <div class="product-desc">
                                            <h3>${p.getProductName()}</h3>
                                            <p class="price">
                                                <span>$${p.getPrice()}</span> 
                                            </p>
                                            <p>
                                                ${p.getDescription()}
                                            </p>
                                            <div class="size-wrap">
                                                <div class="block-26 mb-2">
                                                    <h4>Size</h4>
                                                    <ul>
                                                        <c:forEach items="${sizes}" var="size">
                                                            <li>
                                                                <input class="custom-product-property-radio-input" id="${size.getSize()}" type="radio" name="sizes" value="${size.getSizeId()}" hidden onclick="getProductStock()">
                                                                <label class="custom-product-property-radio-label" for="${size.getSize()}">${size.getSize()}</label>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                                <div class="block-26 mb-4">
                                                    <h4>Color</h4>
                                                    <ul>
                                                        <c:forEach items="${colors}" var="color">
                                                            <li>
                                                                <input class="custom-product-property-radio-input" id="${color.getColor()}" type="radio" name="colors" value="${color.getColorId()}" hidden onclick="getProductStock()">
                                                                <label class="custom-product-property-radio-label" for="${color.getColor()}">${color.getColor()}</label>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="input-group mb-4">
                                                <span class="input-group-btn">
                                                    <button type="button" class="quantity-left-minus btn property-check" onclick="changeProductQuantity(-1)" data-type="minus" data-field="" disabled>
                                                        <i class="icon-minus2"></i>
                                                    </button>
                                                </span>
                                                <input type="text" id="quantity" name="quantity" class="form-control input-number property-check" value="0" min="0" max="1" onblur="checkExceedingQuantity()" disabled>
                                                <span class="input-group-btn ml-1">
                                                    <button type="button" class="quantity-right-plus btn property-check" onclick="changeProductQuantity(1)" data-type="plus" data-field="" disabled>
                                                        <i class="icon-plus2"></i>
                                                    </button>
                                                </span>
                                            </div>
                                            <script>
                                                function getProductStock() {
                                                    //Disable the buttons
                                                    const qe = document.querySelectorAll('.property-check');
                                                    const quantity = document.getElementById("quantity");
                                                    qe.forEach(element => {
                                                        element.disabled = true;
                                                    });
                                                    quantity.value = 0;
                                                    //Check if both of color and size is selected
                                                    const productId = document.getElementById("productId").value;
                                                    const colorId = document.querySelector('[name="colors"]:checked')?.value;
                                                    const sizeId = document.querySelector('[name="sizes"]:checked')?.value;
                                                    if (!colorId || !sizeId) {
                                                        return;
                                                    }
                                                    //xhr setup
                                                    var xhr = new XMLHttpRequest();
                                                    const params = 'productId=' + encodeURIComponent(productId)
                                                            + '&colorId=' + encodeURIComponent(colorId)
                                                            + '&sizeId=' + encodeURIComponent(sizeId);
                                                    xhr.open('post', 'productDetail');
                                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                                    xhr.onreadystatechange = function () {
                                                        if (xhr.readyState === 4 && xhr.status === 200) {
                                                            const stock = Number(xhr.responseText);
                                                            console.log(stock);
                                                            if (!isNaN(stock) && stock > 0) {
                                                                quantity.max = Math.max(stock, 1);
                                                                quantity.value = 1;
                                                                //Enable the buttons
                                                                qe.forEach(element => {
                                                                    element.disabled = false;
                                                                });
                                                            }
                                                        }
                                                    };
                                                    xhr.send(params);
                                                }
                                                function changeProductQuantity(n) {
                                                    const quantity = document.getElementById("quantity");
                                                    const current = Number(quantity.value), min = Number(quantity.min), max = Number(quantity.max);
                                                    const changed = current + n;
                                                    if (changed > min && changed <= max) {
                                                        quantity.value = changed;
                                                        console.log(n);
                                                    }
                                                    if (changed > max) {
                                                        quantity.value = max;
                                                        console.log(0);
                                                    }
                                                }
                                                function checkExceedingQuantity() {
                                                    const quantity = document.getElementById("quantity");
                                                    if (Number.isInteger(parseInt(quantity.value))) {
                                                        quantity.value = 1;
                                                    }
                                                    if (Number(quantity.max) < Number(quantity.value)) {
                                                        quantity.value = quantity.max;
                                                    }
                                                    if (Number(quantity.min) > Number(quantity.value)) {
                                                        quantity.value = quantity.min;
                                                    }
                                                }
                                                function submitByEnterFalse(event) {
                                                    if (event.keyCode === 13) {
                                                        event.preventDefault();
                                                    }
                                                }
                                            </script>
                                            <div class="row">
                                                <div class="col-sm-12 text-center">
                                                    <p class="addtocart"><button type="submit" class="btn btn-primary btn-addtocart" style="display: flex; align-items: center"><i class="icon-shopping-cart"></i> Add to Cart</button></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                </form>



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
