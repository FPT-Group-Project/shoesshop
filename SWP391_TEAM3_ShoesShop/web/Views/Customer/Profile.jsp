<%-- 
    Document   : Profile
    Created on : Oct 21, 2024, 8:31:15 PM
    Author     : vh69
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
                                        <input oninput="searchByName(this)" name="txt" type="search" class="form-control search" placeholder="Search">
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
                                    <li><a href="women.html">Women</a></li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact">Contact</a></li>
                                    <c:if test="${sessionScope.acc!=null}">
                                        <li class="logout">
                                            <a href="logout">Logout</a>
                                        </li>
                                        <li class="profile">
                                            <a href="profile?id=${account.getAccountID()}">Profile</a>
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

            <div class="container rounded bg-white mt-5 mb-5">
                <form action="profile?id=${account.getAccountID()}" method="post" id="formUpdate" onsubmit="return ValidateProfileForm()">
                    <div class="row">
                        <div class="col-md-9 border-right">
                            <div class="p-3 py-5">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="text-right">Profile Settings</h4>
                                </div>

                                <div class="row mt-3">
                                    <div class="col-md-12">
                                        <label class="labels">User Name</label>
                                        <input type="text" class="form-control"  value="${account.getUserName()}" name="user" id="uname">
                                    </div>
                                    <div class="col-md-12">
                                        <label class="labels">Full name</label>
                                        <input type="text" class="form-control" value="${account.getFullName()}" name="name" id="ufullname">
                                    </div>
                                    <div class="col-md-12">
                                        <label class="labels">Mobile Number</label>
                                        <input type="text" class="form-control"  value="${account.getPhoneNumber()}" name="phone" id="uphone">
                                    </div>
                                    <div class="col-md-12">
                                        <label class="labels">Email</label>
                                        <input type="text" class="form-control"  value="${account.getEmail()}" name="email" id="uemail">
                                    </div>
                                </div>
                                <div class="mt-5 text-center">
                                    <button class="btn btn-primary profile-button" type="submit" onclick="confirmSave()" name="update" value="profile">Save Profile</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="p-3 py-5">
                                <div class="d-flex justify-content-between align-items-center experience">
                                    <span>Password</span>
                                    <button class="border px-3 p-1 add-experience" onclick="openPasswordModal()" type="button">
                                        <i class="fa fa-edit"></i>&nbsp;Change Password
                                    </button>
                                </div>
                                <br>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Nội dung cửa sổ chứa màn hình chèn phủ -->
        <div class="overlay" id="modalWrapper" style="display: none;">
            <div class="col-md-5 border-right" style="background-color: white; max-width: 30%;">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Change Password</h4>
                    </div>

                    <form action="profile?id=${account.getAccountID()}" method="post" id="formUpdate" onsubmit="return savePassword();">
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels">Enter old password</label>
                                <input type="password" class="form-control" name="oldpass" >
                            </div>
                            <p id="passwordMismatchOld" style="color: red; display: none;">Password incorrect. Please re-enter!</p>
                            <p id="passwordMismatchNew" style="color: red; display: none;">New password must not be the same as the old one. Please re-enter!</p>
                            <div class="col-md-12">
                                <label class="labels">Enter new Password</label>
                                <input type="password" class="form-control" name="newpass">
                            </div>
                            <p id="passwordMismatch" style="color: red; display: none;">Password does not match. Please re-enter!</p>
                            <div class="col-md-12">
                                <label class="labels">Re-Enter new Password</label>
                                <input type="password" class="form-control" name="repass">
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <button class="btn btn-primary profile-button" type="submit" onclick="savePassword()" name="update" value="password">Save Password</button>
                            <button class="btn btn-secondary profile-button" type="button" onclick="closePasswordModal()">Cancel</button>
                        </div>
                        <div class="mt-5 text-center">
                            <a href="forgot">Forget Password?</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--div để chứa thông báo -->
        <div id="successMessage" class="alert alert-success" style="display: none;">
            Update profile successfully!
        </div>

        <script>
            var passAcc = '<%= request.getAttribute("passAcc") %>';
        </script>

        <script>
            function confirmSave() {
                var result = confirm("Are you sure you want to update profile?");
                if (result) {
                    document.getElementById("formUpdate1").submit();
                    return true;
                } else {
                    return false;
                }
            }
            function openPasswordModal() {
                document.getElementById('modalWrapper').style.display = 'flex';
                document.body.classList.add('modal-open');
            }

            function closePasswordModal() {
                document.getElementById('modalWrapper').style.display = 'none';
                document.body.classList.remove('modal-open');
            }
            function savePassword() {
                var newPassword = document.getElementsByName('newpass')[0].value;
                var oldpass = document.getElementsByName('oldpass')[0].value;
                var reEnteredPassword = document.getElementsByName('repass')[0].value;
                if (oldpass === passAcc) {
                        if (newPassword === reEnteredPassword) {
                            if (newPassword === oldpass) {
                                var passwordMismatchNew = document.getElementById("passwordMismatchNew");
                                passwordMismatchNew.style.display = "block";
                                return false;
                            }
                            else {
                                var result = confirm("Are you sure you want to update password?");
                                if (result) {
                                    document.getElementById("formUpdate").submit();
                                    return true;
                               }
                            }
                        } else {
                            var passwordMismatchMessage = document.getElementById("passwordMismatch");
                            passwordMismatchMessage.style.display = "block";
                            return false;
                        }
                } else {
                    var passwordMismatchOld = document.getElementById("passwordMismatchOld");
                    passwordMismatchOld.style.display = "block";
                    return false;
                }
            }
        </script>
        <style>
            /* CSS để cấu trúc giao diện */
/*            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }*/

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            .row {
                display: flex;
                flex-wrap: wrap;
            }

            .col-md-3, .col-md-9 {
                flex-basis: 0;
                flex-grow: 1;
                max-width: 100%;
            }

            .border-right {
                border-right: 1px solid #ccc;
            }

            .d-flex {
                display: flex;
            }

            .align-items-center {
                align-items: center;
            }

            .text-center {
                text-align: center;
            }

            .py-5 {
                padding-top: 3rem;
                padding-bottom: 3rem;
            }

            .mt-5 {
                margin-top: 3rem;
            }

            /* CSS để tạo cửa sổ */
            .overlay {
                display: flex;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;

            }
            body.modal-open {
                overflow: hidden;
            }


            /* CSS để tạo màn hình che phủ */


        </style>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="js/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <script type="text/javascript">
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
            }
        </script>
        <script>
            function ValidateProfileForm() {
                var userName = document.getElementById("uname").value;
                var userFName = document.getElementById("ufullname").value;
                var phoneNumber = document.getElementById("uphone").value;
                var email = document.getElementById("uemail").value;
                // Kiểm tra  chứa ký tự đặc biệt
                var specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
                if (specialCharRegex.test(userFName)) {
                    alert("FullName cannot contain special characters!");
                    return false;
                }
                if (userName.trim() === "" || userName === "") {
                    alert("Please input username before update !!!");
                    return false;
                }
                if (userFName.trim() === "" || userFName === "") {
                    alert("Please input Fullname before update !!!");
                    return false;
                }
                if ((phoneNumber) === "" || phoneNumber.trim() === "") {
                    alert("Please input numberphone before update !!!");
                    return false;
                }
                if ((email) === "" || email.trim() === "") {
                    alert("Please input email before update !!!");
                    return false;
                }

                // Kiểm tra số điện thoại là số nguyên dương
                // Kiểm tra số điện thoại là số nguyên dương và có đúng 10 chữ số
                var phoneNumberRegex = /^[0-9]\d{9}$/;
                if (!phoneNumberRegex.test(phoneNumber)) {
                    alert("Mobile number must be a positive integer with exactly 10 digits!");
                    return false;
                }
                //check email
                var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(email)) {
                    alert("Please enter a valid email address!");
                    return false;
                }



            }

        </script>

    </body>
</html>
