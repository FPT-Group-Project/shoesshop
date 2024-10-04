<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Brand" %>

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
                                    <li class="active"><a href="/AccoutListController">Back</a></li>



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </nav>
            <h1 class="text-center">Add New Product</h1>
            <!--form add product-->
            <form action="addProduct" method="post" enctype="multipart/form-data" class="form-group">
                <div class="form-row">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" class="form-control" required>
                </div>

                <div class="form-row">
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description" class="form-control" required>
                </div>

                <div class="form-row">
                    <label for="price">Price:</label>
                    <input type="text" id="price" name="price" class="form-control" required>
                </div>

                <div class="form-row">
                    <label for="brandId">Brand:</label>
                    <select id="brandId" name="brandId" class="form-control" required>
                        <option value="">Select a Brand</option>
                        <%
                            List<Brand> brandList = (List<Brand>) request.getAttribute("brandList");
                            if (brandList != null && !brandList.isEmpty()) {
                            for (Brand brand : brandList) {
                        %>
                        <option value="<%= brand.getBrandID() %>"><%= brand.getBrandName() %></option>
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

                <div class="form-row">
                    <label for="avatarP">Product Image:</label>
                    <input type="file" id="avatarP" name="avatarP" class="form-control" required>
                </div>

                <div class="form-row text-center">
                    <input type="submit" value="Add Product" class="btn btn-primary">
                </div>

                <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("successMessage") %>
                </div>
                <% } %>
            </form>

        </div>
    </body>
</html>
