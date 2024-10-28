<%-- 
    Document   : productDetail
    Created on : Sep 26, 2024, 3:58:15 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Feedback" %>

<!DOCTYPE html>
<html>
    <head>
        <title>${p!=null?p.getProductName():'Not found'}</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }

            h2 {
                color: #333;
            }

            .feedback-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            .feedback-form {
                display: flex;
                flex-direction: column;
            }

            .feedback-form label {
                margin-bottom: 5px;
                font-weight: bold;
            }

            .feedback-form select,
            .feedback-form textarea,
            .feedback-form input[type="submit"] {
                margin-bottom: 15px;
                padding: 10px;
                border-radius: 4px;
                border: 1px solid #ccc;
                font-size: 16px;
            }

            .feedback-form select:focus,
            .feedback-form textarea:focus,
            .feedback-form input[type="submit"]:hover {
                border-color: #007bff;
                outline: none;
            }

            .feedback-form input[type="submit"] {
                background-color: #007bff;
                color: white;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .feedback-form input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .feedback-list-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .feedback-list {
                list-style-type: none;
                padding: 0;
            }

            .feedback-list li {
                border-bottom: 1px solid #eee;
                padding: 10px 0;
            }

            .feedback-list li:last-child {
                border-bottom: none;
            }

            .feedback-list strong {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
        </style>
        <style>
            .stars {
                display: flex;
                flex-direction: row-reverse;
                justify-content: flex-end;
            }

            .stars input[type="radio"] {
                display: none;
            }

            .stars label {
                font-size: 30px;
                color: #ccc;
                cursor: pointer;
            }

            .stars input[type="radio"]:checked ~ label {
                color: gold;
            }

            .stars label:hover,
            .stars label:hover ~ label {
                color: gold;
            }

            .pagination {
                display: flex; /* Sử dụng flexbox để căn giữa */
                justify-content: center; /* Căn giữa các nút */
                margin: 20px 0; /* Khoảng cách trên và dưới */
            }

            .pagination a, .pagination span {
                padding: 10px 20px; /* Tăng kích thước nút */
                margin: 0 10px; /* Khoảng cách giữa các nút */
                border: 1px solid #007bff; /* Đường viền cho nút */
                color: #007bff; /* Màu chữ */
                text-decoration: none; /* Bỏ gạch chân */
                border-radius: 5px; /* Bo góc cho nút */
                transition: background-color 0.3s, color 0.3s; /* Hiệu ứng chuyển màu */
            }

            .pagination a:hover {
                background-color: #007bff; /* Màu nền khi hover */
                color: white; /* Màu chữ khi hover */
            }

            .pagination .active {
                background-color: #007bff; /* Màu nền cho nút đang được chọn */
                color: white; /* Màu chữ cho nút đang được chọn */
                font-weight: bold; /* Đậm chữ cho nút đang được chọn */
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
                                    <li class="cart"><a href="cart"><i class="icon-shopping-cart"></i> Cart </a></li>
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
                <form action="addToCart" method="Post">
                    <c:choose>
                        <c:when test="${p==null}">

                        </c:when>
                        <c:otherwise>
                            <div class="container">
                                <div class="row row-pb-lg product-detail-wrap">
                                    <div class="col-sm-8">
                                        <div class="owl-carousel">
                                            <div class="item">
                                                <div class="product-entry border">
                                                    <a href="#" class="prod-img">
                                                        <img src="ImageProductAvt/${p.getAvatarP()}" class="img-fluid" alt="Free html5 bootstrap 4 template">
                                                    </a>
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
                                                background-color: #90ccbc;
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
                                            <input type="hidden" name="productId" value="${p.getProductId()}"/>

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
                                                        if (!Number.isInteger(Number(quantity.value)) || Number(quantity.value) <= 0) {
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

                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12 text-center">
                                                    <p class="addtocart"><button disabled type="submit" class="btn btn-primary btn-addtocart property-check" style="display: flex; align-items: center"><i class="icon-shopping-cart"></i> Add to Cart</button></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>

    </form>

    <div>

                                <style>/* Container for filter section */
.filter-rating {
    background-color: #f4f4f4; /* Màu nền xám sáng */
    padding: 20px; /* Khoảng cách bên trong */
    border-radius: 8px; /* Bo góc nhẹ */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15); /* Đổ bóng nhẹ */
    margin-bottom: 20px; /* Khoảng cách phía dưới */
    display: flex; /* Sử dụng flexbox để căn chỉnh */
    flex-wrap: wrap; /* Cho phép các nút xuống dòng */
}

.filter-rating h4 {
    width: 100%; /* Chiếm toàn bộ chiều rộng */
    margin: 0 0 15px; /* Khoảng cách cho tiêu đề */
    font-size: 24px; /* Kích thước chữ tiêu đề */
    color: #333; /* Màu chữ tối */
    text-align: left; /* Căn trái cho tiêu đề */
}

.filter-rating button {
    background-color: #007bff; /* Màu xanh dương cho nút */
    color: #ffffff; /* Màu chữ trắng */
    border: none; /* Không có viền */
    border-radius: 5px; /* Bo góc cho nút */
    padding: 10px 15px; /* Khoảng cách bên trong nút */
    margin: 5px; /* Khoảng cách giữa các nút */
    cursor: pointer; /* Con trỏ chuột khi di chuột lên nút */
    font-size: 16px; /* Kích thước chữ cho nút */
    transition: background-color 0.3s, transform 0.2s; /* Hiệu ứng chuyển màu nền và biến đổi */
}

.filter-rating button:hover {
    background-color: #0056b3; /* Màu nền khi hover */
    transform: scale(1.05); /* Hiệu ứng phóng to nhẹ khi hover */
}

.filter-rating button:active {
    transform: scale(0.95); /* Hiệu ứng thu nhỏ khi nhấn */
}

</style>


<h2>Feedback Form</h2>

<div class="feedback-container">
    <form action="SubmitFeedbackServlet?productId=${param.id}" method="post" class="feedback-form" onsubmit="return validateForm()">
        <input type="hidden" id="accountId" name="accountId" value="${sessionScope.accountId}">

        <div class="form-group rating-group">
            <label for="rating">Rating:</label><br>
            <div class="stars">
                <input type="radio" id="star5" name="rating" value="5" required />
                <label for="star5" title="Excellent">&#9733;</label>
                <input type="radio" id="star4" name="rating" value="4" required />
                <label for="star4" title="Very Good">&#9733;</label>
                <input type="radio" id="star3" name="rating" value="3" required />
                <label for="star3" title="Good">&#9733;</label>
                <input type="radio" id="star2" name="rating" value="2" required />
                <label for="star2" title="Fair">&#9733;</label>
                <input type="radio" id="star1" name="rating" value="1" required />
                <label for="star1" title="Poor">&#9733;</label>
            </div>
        </div>

        <div class="form-group">
            <label for="comment">Comment:</label>
            <textarea id="comment" name="comment" rows="4" cols="50" placeholder="Write your feedback here..." required></textarea>
        </div>

        <div class="form-group">
            <input type="submit" value="Submit Feedback" class="submit-button">
        </div>
    </form>

    <div class="filter-rating">
        <h4>Filter by Rating:</h4>
        <button onclick="filterComments(5)">5 Stars</button>
        <button onclick="filterComments(4)">4 Stars</button>
        <button onclick="filterComments(3)">3 Stars</button>
        <button onclick="filterComments(2)">2 Stars</button>
        <button onclick="filterComments(1)">1 Star</button>
        <button onclick="filterComments(0)">All</button>
    </div>

    <script>
        function filterComments(stars) {
            const reviews = document.querySelectorAll('.review');

            reviews.forEach(review => {
                // Tìm số sao trong mỗi bình luận
                const reviewStars = review.dataset.rating;

                // Hiện hoặc ẩn bình luận dựa trên đánh giá
                if (stars === 0 || parseInt(reviewStars) === stars) {
                    review.style.display = 'block'; // Hiển thị bình luận
                } else {
                    review.style.display = 'none'; // Ẩn bình luận
                }
            });
        }

        function validateForm() {
            // Kiểm tra nếu không có đánh giá được chọn
            const ratingElements = document.getElementsByName('rating');
            let ratingSelected = false;

            for (let i = 0; i < ratingElements.length; i++) {
                if (ratingElements[i].checked) {
                    ratingSelected = true;
                    break;
                }
            }

            // Lấy nội dung bình luận
            const comment = document.getElementById('comment').value.trim();

            // Hiển thị cảnh báo nếu không có đánh giá và không có bình luận
            if (!ratingSelected && !comment) {
                alert("Please select a rating and enter a comment.");
                return false; // Ngăn gửi form
            } else if (!ratingSelected) {
                alert("Please select a rating.");
                return false; // Ngăn gửi form
            } else if (!comment) {
                alert("Please enter a comment.");
                return false; // Ngăn gửi form
            }

            return true; // Cho phép gửi form
        }
    </script>

    <a href="FeedbackList?productId=1"></a>
</div>

<div class="feedback-list-container">
    <div class="row">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-md-12 pills">
                    <div class="bd-example bd-example-tabs">
                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                            <li class="nav-item ">
                                <a class="nav-link active" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-expanded="true">Review</a>
                            </li>
                        </ul>

                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane border fade show active" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
                                <div class="row">
                                    <div class="col-md-8">
                                        <h3 class="head">${totalComments} Reviews</h3>

                                        <c:if test="${not empty feedbackList}">
                                            <c:forEach var="feedback" items="${feedbackList}">
                                                <div class="review" data-rating="${feedback.getRating()}">
                                                    <div class="user-img" style="background-image: url(https://th.bing.com/th/id/OIP.dRraM8FPURhQzbIoyQbgOwHaEK?pid=ImgDet&w=474&h=266&rs=1)"></div>
                                                    <div class="desc">
                                                        <h4>
                                                            <span class="text-left">
                                                                <strong>${feedback.getUsername()}</strong><br>
                                                            </span>
                                                            <span class="text-right">${feedback.getFeedbackDate()}</span>
                                                        </h4>
                                                        <p class="star">
                                                            <span>
                                                                <c:forEach var="i" begin="1" end="${feedback.getRating()}">
                                                                    <i class="icon-star-full"></i>
                                                                </c:forEach>
                                                                <c:forEach var="i" begin="1" end="${5 - feedback.getRating()}">
                                                                    <i class="icon-star-empty"></i>
                                                                </c:forEach>
                                                            </span>
                                                        </p>
                                                        <p>${feedback.getComment()}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty feedbackList}">
                                            <p>Không có phản hồi nào.</p>
                                        </c:if>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="rating-wrap">
                                            <h3 class="head">Give a Review</h3>
                                            <div class="wrap">
                                                <p class="star">
                                                    <span>
                                                        <c:forEach var="i" begin="1" end="5">
                                                            <i class="icon-star-full"></i>
                                                        </c:forEach>
                                                        (<fmt:formatNumber value="${totalRating5 * 100.0 / (totalRating1 + totalRating2 + totalRating3 + totalRating4 + totalRating5)}" minFractionDigits="2" maxFractionDigits="2"/>%)
                                                    </span>
                                                    <span>${totalRating5} Reviews</span>
                                                </p>
                                                <p class="star">
                                                    <span>
                                                        <c:forEach var="i" begin="1" end="4">
                                                            <i class="icon-star-full"></i>
                                                        </c:forEach>
                                                        <i class="icon-star-empty"></i>
                                                        (<fmt:formatNumber value="${totalRating4 * 100.0 / (totalRating1 + totalRating2 + totalRating3 + totalRating4 + totalRating5)}" minFractionDigits="2" maxFractionDigits="2"/>%)
                                                    </span>
                                                    <span>${totalRating4} Reviews</span>
                                                </p>
                                                <p class="star">
                                                    <span>
                                                        <c:forEach var="i" begin="1" end="3">
                                                            <i class="icon-star-full"></i>
                                                        </c:forEach>
                                                        <c:forEach var="i" begin="1" end="2">
                                                            <i class="icon-star-empty"></i>
                                                        </c:forEach>
                                                        (<fmt:formatNumber value="${totalRating3 * 100.0 / (totalRating1 + totalRating2 + totalRating3 + totalRating4 + totalRating5)}" minFractionDigits="2" maxFractionDigits="2"/>%)
                                                    </span>
                                                    <span>${totalRating3} Reviews</span>
                                                </p>
                                                <p class="star">
                                                    <span>
                                                        <c:forEach var="i" begin="1" end="2">
                                                            <i class="icon-star-full"></i>
                                                        </c:forEach>
                                                        <c:forEach var="i" begin="1" end="3">
                                                            <i class="icon-star-empty"></i>
                                                        </c:forEach>
                                                        (<fmt:formatNumber value="${totalRating2 * 100.0 / (totalRating1 + totalRating2 + totalRating3 + totalRating4 + totalRating5)}" minFractionDigits="2" maxFractionDigits="2"/>%)
                                                    </span>
                                                    <span>${totalRating2} Reviews</span>
                                                </p>
                                                <p class="star">
                                                    <span>
                                                        <i class="icon-star-full"></i>
                                                        <c:forEach var="i" begin="1" end="4">
                                                            <i class="icon-star-empty"></i>
                                                        </c:forEach>
                                                        (<fmt:formatNumber value="${totalRating1 * 100.0 / (totalRating1 + totalRating2 + totalRating3 + totalRating4 + totalRating5)}" minFractionDigits="2" maxFractionDigits="2"/>%)
                                                    </span>
                                                    <span>${totalRating1} Reviews</span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
                

                <!-- Phân trang -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="productDetail?id=${param.id}&page=${currentPage - 1}">Trước</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <span class="active">${i}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="productDetail?id=${param.id}&page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <a href="productDetail?id=${param.id}&page=${currentPage + 1}">Tiếp theo</a>
                    </c:if>
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

