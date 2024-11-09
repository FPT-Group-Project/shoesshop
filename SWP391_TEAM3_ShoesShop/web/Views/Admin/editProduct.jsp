<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Brand" %>
<%@ page import="Models.Product" %>

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
            <h2>Edit Product</h2>
            <form action="updateProduct" method="post" enctype="multipart/form-data" class="form-group">

                <!-- Product ID (hidden) -->
                <div class="form-row">
                <input type="hidden" name="productId" value="${product.productId}">
                </div>z
                <!-- Product Name -->
                <div class="form-row">
                    <label for="productName">Product Name:</label>
                    <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" required>
                </div>

                <!-- Price -->
                <div class="form-row">
                    <label for="productPrice">Price:</label>
                    <input type="number" class="form-control" id="productPrice" name="price" value="${product.price}" required>
                </div>

                <!-- Description -->
                <div class="form-row">
                    <label for="productDescription">Description:</label>
                    <textarea class="form-control" id="productDescription" name="description" rows="4" required>${product.description}</textarea>
                </div>

                <!-- Brand -->
                <div class="form-row">
                    <label for="brandId">Brand:</label>
                    <select id="brandId" name="brandId" class="form-control" required>
                        <option value="">Select a Brand</option>
                        <%
                            List<Brand> brandList = (List<Brand>) request.getAttribute("brandList");
                            Product product = (Product) request.getAttribute("product");
                            int currentBrandId = product != null ? product.getBrandId() : -1;
                            if (brandList != null && !brandList.isEmpty()) {
                                for (Brand brand : brandList) {
                        %>
                        <option value="<%= brand.getBrandID() %>"
                                <%= (brand.getBrandID() == currentBrandId) ? "selected" : "" %> >
                            <%= brand.getBrandName() %>
                        </option>
                        <%
                                }
                            } else {
                        %>
                        <option value="">No brands available</option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <!-- Product Image -->
                <div class="form-row">
                    <label for="productAvatar">Product Image:</label>
                    <input type="file" class="form-control" id="productAvatar" name="avatarP">
                    <!-- Hiển thị ảnh hiện tại -->
                    <p>Current Image: 
                        <img src="${pageContext.request.contextPath}/ImageProductAvt/${product.avatarP}" style="width: 100px;" alt="${product.productName}">
                    </p>
                    <!-- Lưu đường dẫn ảnh cũ nếu không có ảnh mới được tải lên -->
                    <input type="hidden" name="currentAvatarPath" value="${product.avatarP}">
                </div>


                <button type="submit" class="btn btn-primary">Update Product</button>
            </form>



        </div>
    </body>
</html>
