<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng</title>
    <link rel="stylesheet" href="style.css">
    <style>
        * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    padding: 20px;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.title {
    text-align: center;
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
}

.order-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.order-info p {
    font-size: 14px;
}

.status {
    background-color: #28a745;
    color: white;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 12px;
}

.customer-receiver {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.info-block {
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 10px;
    width: 48%;
}

.info-block h3 {
    margin-bottom: 10px;
    font-size: 16px;
    color: #555;
}

.product-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.product-table th, .product-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
}

.product-table th {
    background-color: #f1f1f1;
}

.payment-info {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.payment-method {
    width: 45%;
}

.summary {
    width: 45%;
    font-size: 14px;
}

.summary p {
    display: flex;
    justify-content: space-between;
}

.summary .total {
    color: red;
    font-weight: bold;
    font-size: 18px;
}

.action-buttons {
    text-align: center;
}

.btn {
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    margin: 5px;
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}

.close-btn {
    background-color: #6c757d;
}

.close-btn:hover {
    background-color: #5a6268;
}

    </style>
</head>

<body>
    <div class="container">
        <h1 class="title">Chi tiết đơn hàng</h1>

        <!-- Thông tin đơn hàng -->
        <div class="order-info">
            <div>
                <p>Đơn hàng: <a href="#">${orderDetails[0].orderID.orderID}</a></p>
                <p>${orderDetails[0].orderID.orderDate} </p>
            </div>
             <span class="status">
                <c:choose>
                    <c:when test="${orderDetails[0].orderID.statusID == 1}">Chờ xử lý</c:when>
                    <c:when test="${orderDetails[0].orderID.statusID == 2}">Đang giao</c:when>
                    <c:when test="${orderDetails[0].orderID.statusID == 3}">Đã nhận được hàng</c:when>
                    <c:when test="${orderDetails[0].orderID.statusID == 4}">Đã hủy</c:when>
                    <c:otherwise>Không xác định</c:otherwise>
                </c:choose>
            </span>
        </div>

        <!-- Thông tin khách hàng và người nhận -->
        <div class="customer-receiver">
            <div class="info-block">
                <h3>KHÁCH HÀNG</h3>
                <p>${customer.name}</p>
                <p>${customer.phone}</p>
            </div>
            <div class="info-block">
                <h3>NGƯỜI NHẬN</h3>
                <p>${receiver.name}</p>
                <p>${receiver.phone}</p>
                <p>${receiver.address}</p>
            </div>
        </div>

        <!-- Bảng thông tin sản phẩm -->
        <table class="product-table">
            <thead>
                <tr>
                    <th>Product Name</th>
                     <th>Color</th>
                   <th>Size</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderDetail" items="${orderDetails}">
                    <tr>
                        <td>${orderDetail.productID.productName}</td>
                        <td>${orderDetail.color}</td>
                        <td>${orderDetail.size}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.unitPrice}đ</td>
                        <td>${orderDetail.quantity * orderDetail.unitPrice}đ</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Thông tin thanh toán -->
        <div class="payment-info">
         
           <div class="summary">
                <c:set var="subtotal" value="0" />
                <c:forEach var="orderDetail" items="${orderDetails}">
                    <c:set var="subtotal" value="${subtotal + (orderDetail.quantity * orderDetail.unitPrice)}" />
                </c:forEach>

                <c:set var="discount" value="0" /> <!-- Giả định có một số khuyến mãi -->
                <c:set var="shippingFee" value="0" /> <!-- Giả định phí vận chuyển miễn phí -->
                <c:set var="coupon" value="0" /> <!-- Giả định có mã giảm giá -->
               

                <c:set var="total" value="${subtotal  - shippingFee - coupon}" />

                <p>Subtotal: <span>${subtotal}đ</span></p>
                
                <p>Ship: <span>${shippingFee == 0 ? 'Miễn phí' : shippingFee + 'đ'}</span></p>
                <p>Coupon: <span>-${coupon}đ</span></p>
               
                <hr>
                <p><strong>Total:</strong> <span class="total">${total}đ</span></p>
            </div>
        </div>

        <!-- Các nút hành động -->
        <div class="action-buttons">
            
           <button class="btn close-btn" onclick="window.location.href='orderHistory'">Đóng</button>

        </div>
    </div>
</body>
</html>
