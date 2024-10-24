<%@ page import="Models.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Models.Account"%>
<%@page import="Models.Size"%>
<%@page import="Models.Color"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Footwear - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>

            .form-control {
                max-width: 100px;
                width: 100%;
                margin-bottom: 1rem;
            }

        </style>
    </head>
    <body>

        <div class="container">
            <!-- Navigation -->
            <nav class="colorlib-nav" role="navigation">
                <div class="top-menu">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-7 col-md-9">
                                <div id="colorlib-logo"><a href="index.html">Footwear</a></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-left menu-1">
                                <ul>
                                    <li class="active"><a href="ProductListController">Back</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>

            <%
                Product product = (Product) request.getAttribute("product");
                List<Size> sizeList = (List<Size>) request.getAttribute("sizeList"); // Danh sách kích thước
                List<Color> colorList = (List<Color>) request.getAttribute("colorList"); // Danh sách màu sắc
            %>

            <div>
                <c:choose>
                    <c:when test="${product != null}">
                        <h2>${product.getProductName()}</h2>
                        <img src="ImageProductAvt/${product.avatarP}" alt="${product.productName}" style="width: 100px; height: 100px;" />
                        <p>Price: $${product.getPrice()}</p>
                        <p>Description: ${product.getDescription()}</p>
                        <c:set var="brandName" value="" />
                        <c:forEach var="brand" items="${brandList}">
                            <c:if test="${brand.getBrandID() == product.getBrandId()}">
                                <c:set var="brandName" value="${brand.getBrandName()}" />
                            </c:if>
                        </c:forEach>
                        <p>Brand: ${brandName}</p>


                        <!-- Form để cập nhật số lượng -->
                        <!-- Form để cập nhật số lượng -->
                        <form action="updateStock" method="post" class="mt-4">
                            <input type="hidden" name="productID" value="${product.getProductId()}" />

                            <!-- Dropdown cho kích thước -->
                            <div class="form-group">
                                <label for="size">Select Size:</label>
                                <select name="sizeID" class="form-control" required>
                                    <c:forEach var="size" items="${sizeList}">
                                        <option value="${size.getSizeId()}">${size.getSize()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Dropdown cho màu sắc -->
                            <div class="form-group">
                                <label for="color">Select Color:</label>
                                <select name="colorID" class="form-control" required>
                                    <c:forEach var="color" items="${colorList}">
                                        <option value="${color.getColorId()}">${color.getColor()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Chọn thêm hay bớt -->
                            <div class="form-group">
                                <label for="quantityChange">Choose to Add or Subtract:</label>
                                <select name="quantityChange" class="form-control" required>
                                    <option value="add">Add</option>
                                    <option value="subtract">Subtract</option>
                                </select>
                            </div>

                            <!-- Nhập số lượng -->
                            <div class="form-group">
                                <label for="quantityAmount">Enter quantity:</label>
                                <input type="number" name="quantityAmount" class="form-control" placeholder="Enter quantity" min="1" required />
                            </div>

                            <!-- Nút submit -->
                            <div class="form-group">
                                <input type="submit" value="Update Quantity" class="btn btn-primary" />
                            </div>
                        </form>

                        <!-- Bảng hiển thị ProductStock -->
                        <h3 class="mt-5">Product Stock Information</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Product Name</th>
                                    <th>Size</th>
                                    <th>Color</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="stock" items="${productStockList}">
                                    <c:if test="${stock.getQuantity() > 0}"> <!-- Chỉ hiển thị nếu quantity > 0 -->
                                        <tr>
                                            <td>${product.getProductName()}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${sizeMap[stock.sizeID] != null}">
                                                        ${sizeMap[stock.sizeID].getSize()}
                                                    </c:when>
                                                    <c:otherwise>
                                                        Không có kích thước
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${colorMap[stock.colorID] != null}">
                                                        ${colorMap[stock.colorID].getColor()}
                                                    </c:when>
                                                    <c:otherwise>
                                                        Không có màu sắc
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${stock.getQuantity()}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>

                    </c:when>
                    <c:otherwise>
                        <p>Product not found.</p>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>


    </body>
</html>