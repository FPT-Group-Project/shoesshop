<%-- 
    Document   : newpassword
    Created on : Oct 25, 2024, 8:44:07 AM
    Author     : vh69
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>New Password</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form class="form-signin" action="confirmpass" method="post">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Enter New Password</h1>
                <input name="userName" value="${requestScope.uName}" type="hidden">
                <input oninput="checkNumberCharacter(this)" id="pass1" type="password" class="form-control" name="password" placeholder="${requestScope.check == null ? 'Password' : 'New password'}" required value="${uPass}">
                <label id="text1"></label>
                <input oninput="checkSame(this)" id="pass2" type="password" class="form-control" name="cfpassword" placeholder="${requestScope.check == null ? 'Confirm Password' : 'Confirm new password'}" required value="${uPass}">
                <label id="text"></label>
                <button type="submit" onclick="checkPass()" class="btn btn-success btn-block">Continue</button>
            </form>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
                function changeIcon(obj) {
                    var id = obj.previousSibling.previousSibling.id;

                    var inputP = document.getElementById(id);
                    if (obj.className == 'fa-solid fa-eye-slash') {
                        obj.className = 'fa-solid fa-eye';
                        inputP.type = 'text';
                    } else {
                        obj.className = 'fa-solid fa-eye-slash';
                        inputP.type = 'password';
                    }
                }

                function checkSame(obj) {
                    var text = document.getElementById("text1");
                    if (text.style.display == 'none') {
                        var a = document.getElementById("pass1");
                        if (obj.value !== a.value) {
                            var text = document.getElementById("text");
                            text.style.display = "block";
                            text.textContent = "New password does not match. Please re-enter your new password here.";
                        } else {
                            var text = document.getElementById("text");
                            text.style.display = "none";
                        }
                    }
                }

                function checkNumberCharacter(obj) {
                    var a = document.getElementById("pass1");
                    if (obj.value.length <= 5) {
                        var text = document.getElementById("text1");
                        text.style.display = "block";
                        text.textContent = "This password should have more than 6 characters and be difficult for others to guess.";
                    } else {
                        var text = document.getElementById("text1");
                        text.style.display = "none";
                    }
                }

                function checkPass() {
                    var a = document.getElementById("pass1").value;
                var b = document.getElementById("pass2").value;
                if (a == b && a.length > 5) {
                    document.getElementById("f1").submit();
                } else {
                    document.getElementById("rule").style.color = "red";
                }
            }
        </script>
    </body>
</html>
