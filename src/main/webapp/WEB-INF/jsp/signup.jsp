<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Register Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <style><%@include file="../../resources/css/signup.css"%></style>

</head>
<body>
    <div class="container">
        <form>
            <h2 class="display-3">REGISTER</h2>
            <p>
                <label for="name" class="sr-only">Name</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Name" required="" autofocus="">
            </p>
            <p>
                <label for="surname" class="sr-only">Surname</label>
                <input type="text" id="surname" name="surname" class="form-control" placeholder="Surname" required="">
            </p>
            <p>
                <label for="email" class="sr-only">Username</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="Email" required="">
            </p>
            <p>
                <label for="password" class="sr-only">Password
                </label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
            </p>
            <p>
                <label for="phoneNumber" class="sr-only">National Identity</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone Number" required="">
            </p>
            <p>
                <label for="birthDate" class="sr-only">Date of Birth</label>
                <input type="date" id="birthDate" name="birthDate" class="form-control" required="">
            </p>

            <div class="alert alert-warning" role="alert">
                You already have an account?
                <a href="/login">Login</a>
            </div>
            <div class="alert alert-danger" role="alert" id="errors"></div>
            <div class="alert alert-success" role="alert" id="success"></div>
            <button class="btn btn-lg btn-primary btn-block" type="button" id="add_user">Register</button>
        </form>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="../../resources/js/signup.js"%></script>
</body>
</html>