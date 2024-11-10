<%-- 
    Document   : Cart
    Created on : Sep 29, 2024, 2:31:49 AM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    </head>
    <body>


        <div id="page">
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="home">Shoes Shop</a></div>
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
                                        <a href="products?page=1">Product</a>
                                        
                                    </li>
                                    <li class="active"><a href="orderHistory">Order History</a></li>
                                    
                                    <li><a href="contact">Contact</a></li>
                                    <li><a href="ShowNews">News</a></li>
                                    <li class="cart"><a href="cart"><i class="icon-shopping-cart"></i> Cart [${itemCount}]</a></li>
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
                            <p class="bread"><span><a href="home">Home</a></span> / <span>Shopping Cart</span></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="colorlib-product">
                <div class="container">
                    <div class="row row-pb-lg">
                        <div class="col-md-10 offset-md-1">
                            <div class="process-wrap">
                                <div class="process text-center active">
                                    <p><span>01</span></p>
                                    <h3>Shopping Cart</h3>
                                </div>
                                <div class="process text-center">
                                    <p><span>02</span></p>
                                    <h3>Checkout</h3>
                                </div>
                                <div class="process text-center">
                                    <p><span>03</span></p>
                                    <h3>Order Complete</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-pb-lg">
                        <div class="col-md-12">


                            <div class="product-name d-flex">
                                <div class="one-forth text-left px-4">
                                    <span>Product Details</span>
                                </div>
                                <div class="one-eight text-center">
                                    <span>Price</span>
                                </div>
                                <div class="one-eight text-center">
                                    <span>Quantity</span>
                                </div>
                                <div class="one-eight text-center">
                                    <span>Total</span>
                                </div>
                                <div class="one-eight text-center px-4">
                                    <span>Remove</span>
                                </div>
                            </div>
                            <c:set var = "total" value = "0"/>
                            <c:forEach items="${carts}" var="cart">
                                <div class="product-cart d-flex">
                                    <input type="hidden" name="cartId" value="${cart.cartID}">
                                    <div class="one-forth">
                                        <div class="product-img" style="background-image: url(ImageProductAvt/${cart.product.avatarP});">
                                        </div>
                                        <div class="display-tc">
                                            <h3>${cart.product.productName}</h3>
                                        </div>
                                    </div>
                                    <div class="one-eight text-center">
                                        <div class="display-tc">
                                            <span class="price">$${cart.product.price}</span>
                                        </div>
                                    </div>
                                    <div class="one-eight text-center">
                                        <div class="display-tc">
                                            <input type="number" id="quantity" name="quantity" class="form-control input-number text-center" value="${cart.quantity}" min="1" max="${cart.stock.quantity}" onchange="changeQuantity(this, ${cart.stock.quantity})">
                                        </div>
                                    </div>
                                    <div class="one-eight text-center">
                                        <div class="display-tc">
                                            <span class="price">$${cart.product.price * cart.quantity}</span>
                                            <c:set var = "total" value = "${total + (cart.product.price * cart.quantity)}"/>
                                        </div>
                                    </div>
                                    <div class="one-eight text-center">
                                        <div class="display-tc">
                                            <a href="#" class="closed" onclick="deleteCart(this, event)"></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                                                                    <c:set var="finalTotal" value="${total - coupon.discount}" />

                    <script>
                        function changeQuantity(input, max) {
                            let parent = input.parentNode.parentNode.parentNode;
                            let quantity = input.value <= 0 ? 1 : input.value;
                            if (quantity > max) {
                                quantity = max;
                            }
                            let cartId = parent.children[0].value;
                            let price = parent.querySelectorAll('span[class=price]')[0];
                            let total = parent.querySelectorAll('span[class=price]')[1];
                            let oldsubtotal = document.getElementById("subtotal").innerHTML.substring(1);
                            let oldtotal = document.getElementById("total").innerHTML.substring(1);
                            oldtotal -= parseFloat(total.innerHTML.substring(1));
                            oldsubtotal -= parseFloat(total.innerHTML.substring(1));
                            total.innerHTML = "$" + parseFloat(price.innerHTML.substring(1)) * parseInt(quantity);
                            oldtotal += parseFloat(total.innerHTML.substring(1));
                            document.getElementById("subtotal").innerHTML = "$" + oldtotal;
                            oldsubtotal += parseFloat(total.innerHTML.substring(1));
                            document.getElementById("total").innerHTML = "$" + oldsubtotal;
                            fetch("cart", {
                                method: "POST",
                                body: "quantity=" + quantity + "&type=update&cartId=" + cartId,
                                headers: {
                                    "Content-type": "application/x-www-form-urlencoded"
                                }
                            });
                        }
                        function deleteCart(input, event) {
                            event.preventDefault();
                            let parent = input.parentNode.parentNode.parentNode;
                            let cartId = parent.children[0].value;
                            fetch("cart", {
                                method: "POST",
                                body: "type=delete&cartId=" + cartId,
                                headers: {
                                    "Content-type": "application/x-www-form-urlencoded"
                                }
                            });
                            parent.parentNode.removeChild(parent);
                        }
                        function checkout() {
                            window.location.href = "checkout?finalTotal=${finalTotal}"
                        }
                    </script>
                    <div class="row row-pb-lg">
                        <div class="col-md-12">
                            <div class="total-wrap">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <!--                                        <form action="#">
                                                                                    <div class="row form-group">
                                                                                        <div class="col-sm-9">
                                                                                            <input type="text" name="quantity" class="form-control input-number" placeholder="Your Coupon Number...">
                                                                                        </div>
                                                                                        <div class="col-sm-3">
                                                                                            <input type="submit" value="Apply Coupon" class="btn btn-primary">
                                                                                        </div>
                                                                                    </div>
                                                                                </form>-->
                                        <!--                                        <form action="getCoupon" method="get">
                                                                                    <div class="row form-group">
                                                                                        <div class="col-sm-9">
                                                                                            <input type="text" name="quantity" class="form-control input-number" placeholder="Your Coupon Number...">
                                                                                        </div>
                                                                                        <div class="col-sm-3">
                                                                                            <input type="submit" value="Apply Coupon" class="btn btn-primary">
                                                                                        </div>
                                                                                    </div>
                                                                                </form>-->
                                        <form action="getCoupon" method="get">
                                            <label for="codeName">Coupon:</label>
                                            <input type="text" id="codeName" name="codeName" required />
                                            <button type="submit">Apply Coupon</button>
                                        </form>

                                    </div>
                                    <div class="col-sm-4 text-center">
                                                                         
                                        <div class="total">
                                            <div class="sub">
                                                <p><span>Subtotal:</span> <span id="subtotal">$${total}</span></p>
                                                <p><span>Discount:</span> <span>$${coupon.discount}</span></p>
                                            </div>
                                            <div class="grand-total">
                                                <p><span><strong>Total:</strong></span> <span id="total">$${finalTotal}</span></p>
                                            </div>
                                        </div>

                                        <input type="submit" value="Checkout" class="btn btn-primary" onclick="checkout()">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
                            <h2>Related Products</h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 col-lg-3 mb-4 text-center">
                            <div class="product-entry border">
                                <a href="#" class="prod-img">
                                    <img src="images/item-1.jpg" class="img-fluid" alt="Free html5 bootstrap 4 template">
                                </a>
                                <div class="desc">
                                    <h2><a href="#">Women's Boots Shoes Maca</a></h2>
                                    <span class="price">$139.00</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-lg-3 mb-4 text-center">
                            <div class="product-entry border">
                                <a href="#" class="prod-img">
                                    <img src="images/item-2.jpg" class="img-fluid" alt="Free html5 bootstrap 4 template">
                                </a>
                                <div class="desc">
                                    <h2><a href="#">Women's Minam Meaghan</a></h2>
                                    <span class="price">$139.00</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-lg-3 mb-4 text-center">
                            <div class="product-entry border">
                                <a href="#" class="prod-img">
                                    <img src="images/item-3.jpg" class="img-fluid" alt="Free html5 bootstrap 4 template">
                                </a>
                                <div class="desc">
                                    <h2><a href="#">Men's Taja Commissioner</a></h2>
                                    <span class="price">$139.00</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-lg-3 mb-4 text-center">
                            <div class="product-entry border">
                                <a href="#" class="prod-img">
                                    <img src="images/item-4.jpg" class="img-fluid" alt="Free html5 bootstrap 4 template">
                                </a>
                                <div class="desc">
                                    <h2><a href="#">Russ Men's Sneakers</a></h2>
                                    <span class="price">$139.00</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

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


