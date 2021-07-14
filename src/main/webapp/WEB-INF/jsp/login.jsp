<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <style><%@include file="../../resources/css/login.css"%></style>
</head>
<body>
    <div class="d-flex justify-content-center text-center">
    <form>
        <h2 class="display-3 mb-3">Login</h2>
        <p>
            <label for="username" class="sr-only">Email</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Email" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </p>
        <div class="alert alert-danger mt-2" role="alert" id="dangerAlert">All fields are required.</div>
        <button id="submit" class="btn btn-lg btn-primary btn-block" type="submit">
            <span class="spinner-border spinner-border-sm" role="status" id="spinner" aria-hidden="true"></span>
            <span id="login_text">Login</span>
        </button>
        <div class="alert alert-warning mt-3" role="alert">
            You don't have an account?
            <a href="/register">Register</a>
        </div>
    </form>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="../../resources/js/login.js"%></script>
</body>
</html>