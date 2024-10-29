<%-- 
    Document   : showUsedCoupons
    Created on : Oct 29, 2024, 3:04:06 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Coupons</h1>
        <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
            color: #333;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:nth-child(odd) {
            background-color: #ffffff;
        }
    </style>
        <table>
            <thead>
                <th>ID</th>
                <th>Name</th>
                <th>Discount</th>
                <th>Type</th>
                <th>Quantity</th>
            </thead>
            <c:forEach items="${list}" var="u">
                <tr>
                    <td>${u.getCodeId()}</td>
                    <td>${u.getCodeName()}</td>
                    <td>${u.getDiscount()}</td>
                    <td>${u.getCouponType()}</td>
                    <td>${u.getQuantity()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
