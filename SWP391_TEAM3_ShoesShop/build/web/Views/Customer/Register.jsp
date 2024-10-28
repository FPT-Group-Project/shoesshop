<%-- 
    Document   : Register
    Created on : Oct 6, 2024, 4:34:14 PM
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
        <link href="../../css/login.css" rel="stylesheet" type="text/css"/>
        <title>Register Form</title>
    </head>
    <body>
        <div id="logreg-forms">
           <form class="form-signin" action="register" method="post">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Register</h1>
                <c:if test="${error!=null }">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                <input name="user" type="text" id="inputUser" class="form-control" placeholder="User name" required="" autofocus="">
                <input name="pass" type="password" id="inputPassword" class="form-control" placeholder="Password" required autofocus="">
                <input name="name" type="text" id="inputName" class="form-control" placeholder="Full name" required="" autofocus="">
                <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
                <input name="phone" type="text" id="inputPhone" class="form-control" placeholder="Phone number" required="" autofocus="">
                <button class="btn btn-info btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
                <a href="login"><i class="fas fa-angle-left"></i> Back</a>
            </form>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--        <script>
            function toggleResetPswd(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle() // display:block or none
                $('#logreg-forms .form-reset').toggle() // display:block or none
            }

            function toggleSignUp(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle(); // display:block or none
                $('#logreg-forms .form-signup').toggle(); // display:block or none
            }

            $(() => {
                // Login Register Form
                $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
                $('#logreg-forms #cancel_reset').click(toggleResetPswd);
                $('#logreg-forms #btn-signup').click(toggleSignUp);
                $('#logreg-forms #cancel_signup').click(toggleSignUp);
            })
        </script>-->
    </body>
</html>
