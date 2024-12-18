<%-- 
    Document   : forgot.jsp
    Created on : Oct 18, 2024, 8:53:39 AM
    Author     : vh69
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
    href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    rel="stylesheet" id="bootstrap-css">
<script
src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link rel="stylesheet"
              href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
              integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
              crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css" />
        <title>Forgot Password Form</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form class="form-signin" action="forgot" method="post">
                <c:if test="${requestScope.check == null || requestScope.check == 'false'}">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Forgot Password</h1>
                <label for="email">Email</label>
                <input name="email" type="text" id="email" class="form-control" placeholder="Email" required="" autofocus="">
                <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Send email</button> 
                <a href="login"><i class="fas fa-angle-left"></i> Back</a>
                </c:if>
            </form>
            <c:if test="${requestScope.check != null}">
                <c:if test="${requestScope.check == 'true' && !(requestScope.message == 'Sorry, reset code incorrect')}">
                    <form class="form-signin" action="confirmresetcode" method="post">
                        <p style="color: green">${requestScope.mess}</p>
                        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Confirm Reset Code</h1>
                        <input name="email" value="${requestScope.email}" type="hidden">
                        <input type="text" class="form-control" name="resetcode" placeholder="xxxxxx" required="" value="${requestScope.code}">
                        <c:if test="${requestScope.check != null && requestScope.check == 'true'}">
                            <button type="submit" class="btn btn-success btn-block">Confirm Reset Code</button>
                        </c:if>
                    </form>
                </c:if>
                <c:if test="${requestScope.check == 'false'}">
                    <p style="color: red">${requestScope.error}</p>
                </c:if>
                <c:if test="${requestScope.check == 'true' && requestScope.message == 'Sorry, reset code incorrect'}">
                    <p style="color: red">${requestScope.error}</p>
                </c:if>
            </c:if>
        </div>
    </body>
</html>